package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.PaymentNotification;
import com.bitboxlab.wallet.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<PaymentNotification, Long> {
    /**
     * Fetch list of notifications by user
     * @param user Authenticated user details
     * @param pageable To split request into pages
     * @return List of money request notifications
     */
    List<PaymentNotification> findAllByUser(User user, Pageable pageable);
}
