package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.PaymentNotification;
import com.bitboxlab.wallet.models.PaymentNotificationRequest;
import com.bitboxlab.wallet.models.Transfer;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.message.ResponseMessage;
import com.bitboxlab.wallet.repo.NotificationRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class PaymentNotificationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public ResponseEntity<List<PaymentNotification>> getNotifications(Authentication authentication, @RequestParam(value="page") int page) {
        Pageable pageWithFiveNotifications = PageRequest.of(page, 5,  Sort.by("id").descending());
        try {
            User user = userRepository.findByEmail(authentication.getName());

            List<PaymentNotification> paymentNotifications = notificationRepository.findAllByUser(user, pageWithFiveNotifications);
            return ResponseEntity.status(HttpStatus.OK).body(paymentNotifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<PaymentNotification> getNotificationById(Authentication authentication, @PathVariable Long id) {
        try {
            PaymentNotification paymentNotification = notificationRepository.findById(id).get();

            return ResponseEntity.status(HttpStatus.OK).body(paymentNotification);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> setNotificationToSeen(Authentication authentication, @RequestParam(value="id") Long id) {
        try {
            PaymentNotification paymentNotification = notificationRepository.findById(id).get();

            paymentNotification.setSeen(true);
            notificationRepository.save(paymentNotification);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(false);
        }
    }
}
