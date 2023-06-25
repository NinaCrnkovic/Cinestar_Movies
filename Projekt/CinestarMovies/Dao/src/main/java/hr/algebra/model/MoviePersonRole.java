/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Nina
 */
public final class MoviePersonRole {
    private int moviePersonRoleId;
    private int movieId;
    private int personId;
    private int roleId;

    public MoviePersonRole() {
    }

    
    
    public MoviePersonRole(int movieId, int personId, int roleId) {
        this.movieId = movieId;
        this.personId = personId;
        this.roleId = roleId;
    }

    public MoviePersonRole(int moviePersonRoleId, int movieId, int personId, int roleId) {
        this(movieId, personId, roleId);
        this.moviePersonRoleId = moviePersonRoleId;
 
    }

    public int getMoviePersonRoleId() {
        return moviePersonRoleId;
    }

    public void setMoviePersonRoleId(int moviePersonRoleId) {
        this.moviePersonRoleId = moviePersonRoleId;
    }
    

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    
    
}
