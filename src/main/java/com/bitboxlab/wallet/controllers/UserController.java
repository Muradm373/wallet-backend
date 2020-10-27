package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
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

@RestController
public class UserController {

    @Autowired
    UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);

        return new ResponseEntity<>("Account registered", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<User>> searchForUser(@RequestParam(value="user") String user){
        return new ResponseEntity<>(repository.findAllByNameContainingOrEmailContainingOrSurnameContaining(user, user, user), HttpStatus.OK);
    }


}
