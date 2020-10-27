package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    ArrayList<User> findAllByEmail(String auth);
    ArrayList<User> findAllByEmailContainsAndEmail(String email, String auth);
    ArrayList<User> findAllByNameContainingOrEmailContainingOrSurnameContaining(String query, String email, String pass);
}
