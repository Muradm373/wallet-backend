package com.bitboxlab.wallet.controllers;

import java.util.List;
import java.util.stream.Collectors;
import com.bitboxlab.wallet.models.ProfilePic;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.message.ResponseFile;
import com.bitboxlab.wallet.models.message.ResponseMessage;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@Controller
public class ProfilePicController {

    @Autowired
    private ImageStorageService storageService;
    @Autowired
    UserRepository userRepository;

    /**
     * Uploading profile picture which will be assigned to authenticated user
     * @param authentication Authentication token of the user
     * @param file Fetched profile picture
     * @return Status code of request
     */
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(Authentication authentication, @RequestParam("file") MultipartFile file) {
        String message = "";
        String email = authentication.getName();
        try {
            ProfilePic picture = storageService.store(file, email);
            User user = userRepository.findByEmail(authentication.getName());
            user.setAvatarUrl(picture.getId());
            userRepository.save(user);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Get all files which were uploaded
     * @return Status code of request
     */
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getEmail());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    /**
     * Fetch profile picture by identification string
     * @param id Identification string of the picture
     * @return Status code of request
     */
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        ProfilePic fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }


    /**
     * Remove profile picture of authenticated user
     * @param authentication  Authentication token of the user
     * @return Status code of request
     */
    @DeleteMapping("/profilepic")
    public ResponseEntity<ResponseMessage> removeProfilePic(Authentication authentication) {
        String message = "";
        String email = authentication.getName();
        try {
            User user = userRepository.findByEmail(authentication.getName());
            user.setAvatarUrl("");
            userRepository.save(user);

            message = "Deleted the file successfully: ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not delete the file"   ;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
