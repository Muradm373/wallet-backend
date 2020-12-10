package com.bitboxlab.wallet.repo;

import com.bitboxlab.wallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Get user details by email
     * @param email Email of target user
     * @return User details of searched email
     */
    User findByEmail(String email);

    /**
     * Get user information by its identification number
     * @param id Identification number by which user will be searched
     * @return Details of searched user
     */
    @Override
    Optional<User> findById(Long id);

    /**
     * Search user by name/surname/email/username containing query
     * @param query A target string which will check the matching cases
     * @param email A target email for matching cases
     * @param surname A target surname string for matching cases
     * @return List of users which have matching properties
     */
    Optional<ArrayList<User>> findAllByNameContainingOrEmailContainingOrSurnameContaining(String query, String email, String surname);
}
