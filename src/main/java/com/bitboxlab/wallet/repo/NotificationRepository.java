package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.PaymentNotification;
import com.bitboxlab.wallet.models.ProfilePic;
import com.bitboxlab.wallet.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<PaymentNotification, Long> {
    List<PaymentNotification> findAllByUser(User user, Pageable pageable);
}
