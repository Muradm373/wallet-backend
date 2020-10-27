package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(User user);
}
