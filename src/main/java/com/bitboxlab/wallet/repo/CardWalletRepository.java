package com.bitboxlab.wallet.repo;


import com.bitboxlab.wallet.models.CardWallet;
import com.bitboxlab.wallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardWalletRepository extends JpaRepository<CardWallet, Long> {
    /**
     * Fetch credit card wallets of user account
     * @param user Details of the user whose credit card wallets will be fetched
     * @return List of credit card wallets
     */
    List<CardWallet> findByUser(User user);
}