package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import com.bitboxlab.wallet.repo.ContactRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.repo.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    UserRepository repository;



    @CrossOrigin
    @PostMapping("/wallet")
    public ResponseEntity<Wallet> addContactToUser(Authentication authentication, @RequestBody Wallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);

        walletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/wallet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wallet>> getWalletsOfUser(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());
        List<Wallet> contacts = walletRepository.findByUser(user);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/wallet/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wallet> editWallet(Authentication authentication, @RequestBody Wallet wallet){
        walletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/wallet/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteWallet(Authentication authentication, @RequestBody Wallet wallet){
        walletRepository.delete(wallet);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
