package com.bitboxlab.wallet.models;

public class TransferResponse {
    Transfer transfer;
    User user;

    public TransferResponse(Transfer transfer, User user) {
        this.transfer = transfer;
        this.user = user;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
