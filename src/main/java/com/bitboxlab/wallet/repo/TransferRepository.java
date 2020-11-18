package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, String> {
}
