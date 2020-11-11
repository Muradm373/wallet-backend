package com.bitboxlab.wallet.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CardWallets")
public class CardWallet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="email", nullable=false)
    private User user;


    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="valid_month")
    private String validMonth;

    @NotNull
    @Column(name="valid_year")
    private String validYear;

    @NotNull
    @Column(name="cvv")
    private String cvv;

    @NotNull
    @Column(name="account_holder_name")
    private String accountHolderName;

    @NotNull
    @Column(name="card_number")
    private String cardNumber;

    @NotNull
    @Column(name="address")
    private String address;

    @NotNull
    @Column(name="private")
    private boolean privateWallet;

    public CardWallet() {
    }

    public CardWallet(String name, String address, String cvv, String accountHolderName, String cardNumber, boolean privateWallet,
                      String validMonth, String validYear) {
        this.name = name;
        this.address = address;
        this.cvv = cvv;
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
        this.privateWallet = privateWallet;
        this.validMonth = validMonth;
        this.validYear = validYear;
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

    public String getValidMonth() {
        return validMonth;
    }

    public void setValidMonth(String validMonth) {
        this.validMonth = validMonth;
    }

    public String getValidYear() {
        return validYear;
    }

    public void setValidYear(String validYear) {
        this.validYear = validYear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
