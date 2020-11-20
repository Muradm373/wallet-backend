package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    ArrayList<User> findAllByEmail(String auth);
    ArrayList<User> findAllByEmailContainsAndEmail(String email, String auth);
    Optional<User> findById(Long id);
    ArrayList<User> findAllByNameContainingOrEmailContainingOrSurnameContaining(String query, String email, String pass);
}
