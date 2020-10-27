package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUser(User user);
}
