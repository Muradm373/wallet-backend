package com.bitboxlab.wallet.controllers;


import com.bitboxlab.wallet.models.PaymentNotification;
import com.bitboxlab.wallet.models.PaymentNotificationRequest;
import com.bitboxlab.wallet.models.Transfer;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.repo.NotificationRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.services.TransferStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class NotificationWebsocketController {
    @Autowired UserRepository userRepository;
    @Autowired NotificationRepository notificationRepository;
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private TransferStorageService transferStorageService;

    /**
     * Request for notifying a user about outgoing notification to a notification center web socket
     * @param notification Details of requested money transfer
     */
    @MessageMapping("/notify-request")
    public String createNotification(@Payload PaymentNotificationRequest notification) {
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

        return message;
    }
}
