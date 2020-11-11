package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.BankWallet;
import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
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
public class BankWalletController {
    @Autowired
    BankWalletRepository bankWalletRepository;
    @Autowired
    UserRepository repository;

    @PostMapping("/wallet_bank")
    public ResponseEntity<BankWallet> addWalletToUser(Authentication authentication, @RequestBody BankWallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);

        bankWalletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.OK);

    }

    @RequestMapping(value = "/wallet_bank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BankWallet>> getWalletsOfUser(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());
        List<BankWallet> contacts = bankWalletRepository.findByUser(user);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/wallet_bank", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankWallet> editWallet(Authentication authentication, @RequestBody BankWallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);
        bankWalletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/wallets_bank/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteWallet(@PathVariable Long id){
        BankWallet wallet = bankWalletRepository.findById(id).orElse(null);
        if(wallet!=null) {
            bankWalletRepository.delete(wallet);
            return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("Wallet not found", HttpStatus.BAD_REQUEST);
    }
}
