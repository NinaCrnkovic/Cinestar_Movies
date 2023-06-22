/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;

/**
 *
 * @author Nina
 */
public final class User {

    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;

    private int id;
    private String username;
    private String password;
    private int accountTypeId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setAccountType(2);

        
    }

    public User(String username, String password, int accountTypeId) {
        this(username, password);
        this.accountTypeId = accountTypeId;
    }

    public User(int id, String username, String password, int accountTypeId) {
        this(username, password, accountTypeId);
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

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountType(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }
    
    public void hashPassword() throws NoSuchAlgorithmException {
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        this.password = hashedPassword;
    }

    private String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        messageDigest.update(salt.getBytes());
        byte[] hashedPassword = messageDigest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

}
