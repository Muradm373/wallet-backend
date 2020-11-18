package com.bitboxlab.wallet.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    String transactionType;
    String youSend;
    String theyReceive;
    String cardnumber;
    String name;
    String address;
    String userId;
    String email;
    String username;

    public Transfer(String id, String transactionType, String youSend, String theyReceive, String cardnumber, String name, String address, String userId, String email, String username) {
        this.id = id;
        this.transactionType = transactionType;
        this.youSend = youSend;
        this.theyReceive = theyReceive;
        this.cardnumber = cardnumber;
        this.name = name;
        this.address = address;
        this.userId = userId;
        this.email = email;
        this.username = username;
    }

    public Transfer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getYouSend() {
        return youSend;
    }

    public void setYouSend(String youSend) {
        this.youSend = youSend;
    }

    public String getTheyReceive() {
        return theyReceive;
    }

    public void setTheyReceive(String theyReceive) {
        this.theyReceive = theyReceive;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
