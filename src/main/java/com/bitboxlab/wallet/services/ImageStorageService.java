package com.bitboxlab.wallet.services;

import com.bitboxlab.wallet.models.ProfilePic;
import com.bitboxlab.wallet.repo.ProfilePicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ImageStorageService {
    @Autowired
    private ProfilePicRepository fileDBRepository;


    /**
     * Writing profile picture file into the database and assigning a user to it
     * @param file File which will be written into the database
     * @param email Email of the user to whom it will be assigned
     * @return Profile pic model containing details of a profile picture
     * @throws IOException File read write error
     */
    public ProfilePic store(MultipartFile file, String email) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ProfilePic FileDB = new ProfilePic(fileName, file.getContentType(), file.getBytes(), email);

        return fileDBRepository.save(FileDB);
    }

    /**
     * Fetch profile picture file from data base by its identification number
     * @param id Identification number
     * @return File that matches given identification number
     */
    public ProfilePic getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    /**
     * Fetch all files that were uploaded to database
     * @return Stream of uploaded files
     */
    public Stream<ProfilePic> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
