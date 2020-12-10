package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.BankWallet;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankWalletRepository extends JpaRepository<BankWallet, Long> {
    /**
     * Fetch bank wallets of user account
     * @param user Details of the user whose wallets will be fetched
     * @return List of bank wallets
     */
    List<BankWallet> findByUser(User user);
}