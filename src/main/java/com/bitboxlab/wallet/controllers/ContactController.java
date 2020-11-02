package com.bitboxlab.wallet.controllers;

import com.bitboxlab.wallet.models.Contact;
import com.bitboxlab.wallet.models.User;
import com.bitboxlab.wallet.repo.ContactRepository;
import com.bitboxlab.wallet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository repository;



    @CrossOrigin
    @PostMapping("/contact")
    public ResponseEntity<String> addContactToUser(Authentication authentication, @RequestBody Contact contact){
        User user = repository.findByEmail(authentication.getName());
        contact.setUser(user);

        contactRepository.save(contact);

        return new ResponseEntity<>("Contact added", HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/contact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getContactsOfUser(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());
        List<Contact> contacts = contactRepository.findByUser(user);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/contact/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> editContact(Authentication authentication, @RequestBody Contact contact){
        contactRepository.save(contact);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteContact(Authentication authentication, @RequestBody Contact contact){
        contactRepository.delete(contact);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
