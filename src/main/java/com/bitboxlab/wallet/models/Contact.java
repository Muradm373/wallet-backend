package com.bitboxlab.wallet.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="email", nullable=false)
    private User user;

    @Column(name="name")
    private String name;


    @Column(name="wallet")
    private String wallet;

    @Column(name="address")
    private String address;

    public Contact() {
    }

    public Contact(String name, String address, User user) {
        this.name = name;
        this.address = address;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

}
