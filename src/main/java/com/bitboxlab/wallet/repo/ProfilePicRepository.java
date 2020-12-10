package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.ProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface ProfilePicRepository extends JpaRepository<ProfilePic, String> {
    /**
     * Get list of profile pictures by the email of the user
     * @param email Email of target user
     * @return List of profile picture details
     */
    List<ProfilePic> findAllByEmail(String email);
}
