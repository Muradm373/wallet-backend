package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.ProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePicRepository extends JpaRepository<ProfilePic, String> {
}
