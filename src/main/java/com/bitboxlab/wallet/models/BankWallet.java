package com.bitboxlab.wallet.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BankWallets")
public class BankWallet implements Serializable {
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
    @Column(name="bank_name")
    private String bankName;

    @NotNull
    @Column(name="bank_address")
    private String bankAddress;

    @NotNull
    @Column(name="iban")
    private String iban;

    @NotNull
    @Column(name="account_holder_name")
    private String accountHolderName;

    @NotNull
    @Column(name="account_number")
    private String accountNumber;

    @NotNull
    @Column(name="private")
    private boolean privateWallet;

    public BankWallet() {
    }

    public BankWallet(String name, String bankName, String bankAddress, String iban, String accountHolderName, String accountNumber, boolean privateWallet) {
        this.name = name;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.iban = iban;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.privateWallet = privateWallet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return bankAddress;
    }

    public void setAddress(String address) {
        this.bankAddress = address;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
