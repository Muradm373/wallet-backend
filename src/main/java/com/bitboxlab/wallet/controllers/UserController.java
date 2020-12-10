package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import com.bitboxlab.wallet.repo.ContactRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.utils.JwtUtil;
import io.fusionauth.jwks.domain.JSONWebKey;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Create user account
     * @param user
     * @return status code for registration
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user)
    {
        if(repository.findByEmail(user.getEmail()) !=null)
        {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
        return new ResponseEntity<>("Account registered", HttpStatus.OK);
    }

    /**
     * Get user information details by his authentication token
     * @param authentication
     * @return User information details
     */
    @PostMapping("/info")
    public ResponseEntity<User> getUserByAuth(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Search users by their username/email/name
     * @param user
     * @return List of users
     */
    @GetMapping("/search")
    public ResponseEntity<ArrayList<User>> searchForUser(@RequestParam(value="user") String user){
        return new ResponseEntity<>(repository.findAllByNameContainingOrEmailContainingOrSurnameContaining(user, user, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUser(Authentication authentication, @RequestBody User userUpdated){
        User user = repository.findByEmail(authentication.getName());
        user.setEmail(userUpdated.getEmail());
        user.setName(userUpdated.getName());
        user.setSurname(userUpdated.getSurname());
        user.setAddress(userUpdated.getAddress());
        user.setContact(userUpdated.getContact());
        user.setPrivateAccount(userUpdated.isPrivateAccount());
        repository.save(user);

        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        User user = repository.findById(id).orElse(null);
        if(user != null) {
            repository.delete(user);
            return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("Wallet not found", HttpStatus.BAD_REQUEST);
    }


}
