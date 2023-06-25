/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Nina
 */
public final class FavoriteMovie {

    
    private int favoriteMovieId;
    private int movieId;
    private int userId;

    public FavoriteMovie() {
    }

    public FavoriteMovie(int movieId, int userId) {
        this.movieId = movieId;
        this.userId = userId;
    }
    
    public FavoriteMovie(int favoriteMovieId, int movieId, int userId) {
        this(movieId, userId);
        this.favoriteMovieId = favoriteMovieId;
     
    }

    public int getFavoriteMovieId() {
        return favoriteMovieId;
    }

    public void setFavoriteMovieId(int favoriteMovieId) {
        this.favoriteMovieId = favoriteMovieId;
    }
    
    
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

 
       
       
}
