package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.*;
import com.bitboxlab.wallet.models.message.ResponseFile;
import com.bitboxlab.wallet.models.message.ResponseMessage;
import com.bitboxlab.wallet.repo.NotificationRepository;
import com.bitboxlab.wallet.repo.UserRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Controller
public class TransferController {
    @Autowired
    private TransferStorageService transferStorageService;
    @Autowired
    UserRepository repository;
    @Autowired
    NotificationRepository notificationRepository;

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

    @PostMapping("/create-notification")
    public ResponseEntity<ResponseMessage> createNotification(@RequestBody PaymentNotificationRequest notification) {
        String message = "";
        try {
            Transfer transfer = transferStorageService.store(notification.getTransfer());
            for (String userEmail: notification.getUsers()) {
                User user = repository.findByEmail(userEmail);
                PaymentNotification paymentNotification = new PaymentNotification(transfer, user, LocalDateTime.now());
                notificationRepository.save(paymentNotification);
            }

            message = transfer.getId();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not create transfer!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/transfers/{id}")
    public ResponseEntity<TransferResponse> getTransfer(@PathVariable String id) {
        Transfer transfer = transferStorageService.getTransfer(id);
        User user = repository.findById(transfer.getUserId()).get();
        TransferResponse transferResponse = new TransferResponse(transfer, user);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; transfer=\"" + transfer.getId() + "\"")
                .body(transferResponse);
    }
}
