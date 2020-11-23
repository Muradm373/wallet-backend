package com.bitboxlab.wallet.controllers;


import com.bitboxlab.wallet.models.PaymentNotification;
import com.bitboxlab.wallet.models.PaymentNotificationRequest;
import com.bitboxlab.wallet.models.Transfer;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.message.ResponseMessage;
import com.bitboxlab.wallet.repo.NotificationRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.services.TransferStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
public class NotificationWebsocketController {
    @Autowired UserRepository userRepository;
    @Autowired NotificationRepository notificationRepository;
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private TransferStorageService transferStorageService;

    @MessageMapping("/notify-request")
    public void createNotification(@Payload PaymentNotificationRequest notification) {
        String message = "";
        try {
            Transfer transfer = transferStorageService.store(notification.getTransfer());
            for (String userEmail: notification.getUsers()) {
                User user = userRepository.findByEmail(userEmail);
                PaymentNotification paymentNotification = new PaymentNotification(transfer, user, LocalDateTime.now());
                notificationRepository.save(paymentNotification);



                messagingTemplate.convertAndSendToUser(
                        paymentNotification.getUser().getEmail(),"/queue/messages",
                        paymentNotification);
            }

        } catch (Exception e) {
            message = "Could not create transfer!";
        }

        System.out.println(message);
    }
}
