package com.bitboxlab.wallet.services;

import com.bitboxlab.wallet.models.Transfer;
import com.bitboxlab.wallet.repo.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;

@Service
public class TransferStorageService {
    @Autowired
    private TransferRepository transferRepository;

    /**
     * Writing request money transfer details into the database
     * @param transferDetails Transfer details of money request which will be written into the database
     * @return Transfer details model containing details of a money request
     */
    public Transfer store(Transfer transferDetails) {
        return transferRepository.save(transferDetails);
    }


    /**
     * Get money transfer request by associated identification number
     * @param id Identification number of the transfer
     * @return Transfer which is associated with the requested identification number
     */
    public Transfer getTransfer(String id) {
        return transferRepository.findById(id).get();
    }

    /**
     * Get all transfers that are in the database
     * @return List of money transfer requests
     */
    public Stream<Transfer> getAllTrasfers() {
        return transferRepository.findAll().stream();
    }
}