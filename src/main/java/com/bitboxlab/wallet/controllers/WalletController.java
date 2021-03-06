package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.models.Wallet;
import com.bitboxlab.wallet.repo.UserRepository;
import com.bitboxlab.wallet.repo.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class WalletController {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    UserRepository repository;


    /**
     * Adding wallet for cryptocurrency transfers
     * @param authentication Authentication token of the user
     * @param wallet Wallet information
     * @return Status code for request
     */
    @PostMapping("/wallet")
    public ResponseEntity<Wallet> addCryptoWallet(Authentication authentication, @RequestBody Wallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);

        walletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    /**
     * Fetch wallets of authenticated user
     * @param authentication  Authentication token of the user
     * @return List of user's wallets
     */
    @RequestMapping(value = "/wallet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wallet>> getWalletsOfUser(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());
        List<Wallet> contacts = walletRepository.findByUser(user);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    /**
     * Get public wallets of the searched user by his email
     * @param email Email of the authenticated user
     * @return List of wallets
     */
    @RequestMapping(value = "/wallet-search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wallet>> getWalletsOfUser(@RequestParam(value="email") String email){
        User user = repository.findByEmail(email);
        List<Wallet> wallets = null;
        if(!user.isPrivateAccount())
            wallets = walletRepository.findByUserAndPrivateWalletFalse(user);

        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    /**
     * Change crypto wallet information
     * @param authentication  Authentication token of the user
     * @param wallet Wallet information details
     * @return Status code of request
     */
    @RequestMapping(value = "/wallet", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wallet> editWallet(Authentication authentication, @RequestBody Wallet wallet){
        User user = repository.findByEmail(authentication.getName());
        wallet.setUser(user);
        walletRepository.save(wallet);

        return new ResponseEntity<>(wallet, HttpStatus.NO_CONTENT);
    }

    /**
     * Delete wallet of user by id
     * @param id User id
     * @return Status code of the request
     */
    @RequestMapping(value = "/wallets/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteWallet(@PathVariable Long id){
        Wallet wallet = walletRepository.findById(id).orElse(null);
        if(wallet!=null) {
            walletRepository.delete(wallet);
            return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("Wallet not found", HttpStatus.BAD_REQUEST);
    }
}
