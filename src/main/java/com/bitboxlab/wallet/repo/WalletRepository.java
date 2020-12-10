package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    /**
     * Fetch public crypto wallets of user account
     * @param user Details of the user whose wallets will be fetched
     * @return List of crypto wallets
     */
    List<Wallet> findByUserAndPrivateWalletFalse(User user);

    /**
     * Fetch crypto wallets of user account
     * @param user Details of the user whose wallets will be fetched
     * @return List of crypto wallets
     */
    List<Wallet> findByUser(User user);

}
