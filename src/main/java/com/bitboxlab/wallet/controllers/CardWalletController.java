package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.*;
import com.bitboxlab.wallet.repo.CardWalletRepository;
import com.bitboxlab.wallet.repo.ContactRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.repo.BankWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CardWalletController {
    @Autowired
    CardWalletRepository cardWalletRepository;
    @Autowired
    UserRepository repository;

    @PostMapping("/wallet_card")
    public ResponseEntity<CardWallet> addWalletToUser(Authentication authentication, @RequestBody CardWallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);

        cardWalletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.OK);

    }

    @RequestMapping(value = "/wallet_card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardWallet>> getWalletsOfUser(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());
        List<CardWallet> contacts = cardWalletRepository.findByUser(user);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/wallet_card", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardWallet> editWallet(Authentication authentication, @RequestBody CardWallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);
        cardWalletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/wallet_card/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteWallet(@PathVariable Long id){
        CardWallet wallet = cardWalletRepository.findById(id).orElse(null);
        if(wallet!=null) {
            cardWalletRepository.delete(wallet);
            return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("Wallet not found", HttpStatus.BAD_REQUEST);
    }
}
