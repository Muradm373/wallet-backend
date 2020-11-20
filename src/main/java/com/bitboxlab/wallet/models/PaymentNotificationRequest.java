package com.bitboxlab.wallet.models;

import java.util.List;

public class PaymentNotificationRequest {
    List<String> users;
    Transfer transfer;

    public PaymentNotificationRequest(List<String> users, Transfer transfer) {
        this.users = users;
        this.transfer = transfer;
    }

    public PaymentNotificationRequest() {
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
}
