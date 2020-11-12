package com.bitboxlab.wallet.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="password")
    private String password;


    @NotNull
    @Column(name="surname")
    private String surname;

    @NotNull
    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address = "";

    @Column(name="contact")
    private String contact = "";

    @Column(name="private")
    private boolean privateAccount = false;

    @Column(name="avatar")
    private String avatarUrl = "";

    public User() {
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrivateAccount() {
        return privateAccount;
    }

    public void setPrivateAccount(boolean privateAccount) {
        this.privateAccount = privateAccount;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
