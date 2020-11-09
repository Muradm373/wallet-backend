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

@CrossOrigin
@RestController
public class ContactController {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository repository;

    @PostMapping("/contact")
    public ResponseEntity<String> addContactToUser(Authentication authentication, @RequestBody Contact contact){
        User user = repository.findByEmail(authentication.getName());
        contact.setUser(user);
        contactRepository.save(contact);
        return new ResponseEntity<>("Contact added", HttpStatus.CREATED);

    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getContactsOfUser(Authentication authentication){
        User user = repository.findByEmail(authentication.getName());
        List<Contact> contacts = contactRepository.findByUser(user);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/contact", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> editContact(Authentication authentication, @RequestBody Contact contact){
        User user = repository.findByEmail(authentication.getName());
        contact.setUser(user);
        contactRepository.save(contact);
        return new ResponseEntity<>(contact, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteContact(@PathVariable Long id){
        Contact contact = contactRepository.findById(id).orElse(null);
        if(contact!=null) {
            contactRepository.delete(contact);
            return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Contact not found", HttpStatus.BAD_REQUEST);

    }
}
