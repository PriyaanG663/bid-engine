package com.auction.identity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // This was missing!
    private Double balance;

    // 1. Default constructor (Required by JPA)
    public UserAccount() {}

    // 2. The constructor your Service is trying to use
    public UserAccount(String username, String password, Double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; } // Fixes the "cannot find symbol" error
    public void setPassword(String password) { this.password = password; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}