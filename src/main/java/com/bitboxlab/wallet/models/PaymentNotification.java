package com.bitboxlab.wallet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Notifications")
public class PaymentNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="transferId", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Transfer transfer;

    @Column(name="seen")
    Boolean seen = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="email", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public PaymentNotification(Transfer transfer, User user) {
        this.transfer = transfer;
        this.user = user;
    }

    public PaymentNotification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
