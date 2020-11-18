package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.ProfilePic;
import com.bitboxlab.wallet.models.Transfer;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.message.ResponseFile;
import com.bitboxlab.wallet.models.message.ResponseMessage;
import com.bitboxlab.wallet.services.ImageStorageService;
import com.bitboxlab.wallet.services.TransferStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Controller
public class TransferController {
    @Autowired
    private TransferStorageService transferStorageService;

    @PostMapping("/create-transfer")
    public ResponseEntity<ResponseMessage> createTransfer(Authentication authentication,@RequestBody Transfer transferDetails) {
        String message = "";
        try {
            Transfer transfer = transferStorageService.store(transferDetails);

            message = transfer.getId();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not create transfer!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

//    @GetMapping("/files")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length,
//                    dbFile.getEmail());
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }

    @GetMapping("/transfers/{id}")
    public ResponseEntity<String> getTransfer(@PathVariable String id) {
        Transfer transfer = transferStorageService.getTransfer(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; transfer=\"" + transfer.getId() + "\"")
                .body(transfer.getTransferDetails());
    }
}
