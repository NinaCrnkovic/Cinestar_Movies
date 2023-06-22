/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Nina
 */
public final class Role {

    private int id;
    private String role;

    public Role(int id, String role) {
        this(role);
        this.id = id;
     
    }

    public Role(String role) {
        this.role = role;
    }
    
}
