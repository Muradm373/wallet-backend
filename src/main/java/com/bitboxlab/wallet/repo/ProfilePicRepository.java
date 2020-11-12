package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.ProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface ProfilePicRepository extends JpaRepository<ProfilePic, String> {
    List<ProfilePic> findAllByEmail(String email);
}
