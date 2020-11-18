package com.bitboxlab.wallet.services;

import com.bitboxlab.wallet.models.ProfilePic;
import com.bitboxlab.wallet.models.Transfer;
import com.bitboxlab.wallet.repo.ProfilePicRepository;
import com.bitboxlab.wallet.repo.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class TransferStorageService {
    @Autowired
    private TransferRepository transferRepository;

    public Transfer store(Transfer transferDetails) throws IOException {
        return transferRepository.save(transferDetails);
    }

    public Transfer getTransfer(String id) {
        return transferRepository.findById(id).get();
    }

    public Stream<Transfer> getAllFiles() {
        return transferRepository.findAll().stream();
    }
}