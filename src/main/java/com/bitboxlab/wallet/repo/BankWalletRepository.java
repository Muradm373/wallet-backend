package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.BankWallet;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankWalletRepository extends JpaRepository<BankWallet, Long> {
    List<BankWallet> findByUser(User user);
}