package com.bitboxlab.wallet.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Wallets")
public class Wallet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="email", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="address")
    private String address;

    @NotNull
    @Column(name="private")
    private boolean privateWallet;

    public Wallet() {
    }

    public Wallet(String name, String address) {
        this.name = name;
        this.address = address;
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

    public boolean isPrivateWallet() {
        return privateWallet;
    }

    public void setPrivateWallet(boolean privateWallet) {
        this.privateWallet = privateWallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
