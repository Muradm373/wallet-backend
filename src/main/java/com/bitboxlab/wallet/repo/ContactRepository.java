package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    /**
     * Get list of contacts of the user
     * @param user User details
     * @return List of contacts
     */
    List<Contact> findByUser(User user);
}
