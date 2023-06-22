/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Nina
 */
public final class User {
        private int id;
        private String username;
        private String password;
        private AccountType accountType;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, AccountType accountType) {
        this(username, password);
        this.accountType = accountType;
    }

    public User(int id, String username, String password, AccountType accountType) {
        this(username, password, accountType);
        this.id = id;
  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
    
    
}
