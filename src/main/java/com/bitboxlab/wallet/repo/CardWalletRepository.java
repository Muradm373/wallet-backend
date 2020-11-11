package com.bitboxlab.wallet.repo;


import com.bitboxlab.wallet.models.CardWallet;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardWalletRepository extends JpaRepository<CardWallet, Long> {
    List<CardWallet> findByUser(User user);
}