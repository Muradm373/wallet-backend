package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.*;
import com.bitboxlab.wallet.models.message.ResponseMessage;
import com.bitboxlab.wallet.repo.NotificationRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.services.TransferStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@Controller
public class TransferController {
    @Autowired
    private TransferStorageService transferStorageService;
    @Autowired
    UserRepository repository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired private SimpMessagingTemplate messagingTemplate;


    /**
     * Creating money transfer request
     * @param authentication Authentication token of the user
     * @param transferDetails Details of the requested transfer
     * @return Status code of request
     */
    @PostMapping("/create-transfer")
    public ResponseEntity<ResponseMessage> createTransfer(Authentication authentication,@RequestBody Transfer transferDetails) {
        String message;
        try {
            Transfer transfer = transferStorageService.store(transferDetails);

            message = transfer.getId();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not create transfer!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Creating push notification for money transfer request
     * @param notification Details of fetched notification
     * @return Status code of request
     */
    @PostMapping("/create-notification")
    public ResponseEntity<ResponseMessage> createNotification(@RequestBody PaymentNotificationRequest notification) {
        String message;
        try {
            Transfer transfer = transferStorageService.store(notification.getTransfer());
            for (String userEmail: notification.getUsers()) {
                User user = repository.findByEmail(userEmail);
                PaymentNotification paymentNotification = new PaymentNotification(transfer, user, LocalDateTime.now());
                notificationRepository.save(paymentNotification);

                messagingTemplate.convertAndSendToUser(
                        paymentNotification.getUser().getEmail(),"/queue/messages",
                        paymentNotification);
            }

            message = transfer.getId();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not create transfer!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    /**
     * Get transfer details by identification number of transaction
     * @param id Identification number of requested transfer
     * @return Details of the requested transfer
     */
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
