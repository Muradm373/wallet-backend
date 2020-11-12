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

    public ProfilePic store(MultipartFile file, String email) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ProfilePic FileDB = new ProfilePic(fileName, file.getContentType(), file.getBytes(), email);

        return fileDBRepository.save(FileDB);
    }

    public ProfilePic getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<ProfilePic> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
