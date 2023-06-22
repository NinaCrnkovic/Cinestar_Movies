/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;
import hr.algebra.dal.Repository;
import hr.algebra.model.MovieGenre;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
/**
 *
 * @author Nina
 */
public class MovieGenreRepository implements Repository<MovieGenre> {
    private static final String ID_MOVIE_GENRE = "IDMovieGenre";
    private static final String MOVIE_ID = "MovieID";
    private static final String GENRE_ID = "GenreID";

    private static final String CREATE_MOVIE_GENRE = "{ CALL CreateMovieGenre (?,?) }";
    private static final String UPDATE_MOVIE_GENRE = "{ CALL UpdateMovieGenre (?,?,?,?) }";
    private static final String DELETE_MOVIE_GENRE = "{ CALL DeleteMovieGenre (?,?) }";
    private static final String SELECT_MOVIE_GENRES = "{ CALL SelectMovieGenres }";

    @Override
    public int create(MovieGenre movieGenre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE_GENRE)) {

            stmt.setInt(MOVIE_ID, movieGenre.getMovieId());
            stmt.setInt(GENRE_ID, movieGenre.getGenreId());

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_GENRE);
        }
    }

    @Override
    public void update(int id, MovieGenre movieGenre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_GENRE)) {

            stmt.setInt(ID_MOVIE_GENRE, id);
            stmt.setInt(MOVIE_ID, movieGenre.getMovieId());
            stmt.setInt(GENRE_ID, movieGenre.getGenreId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
          // Implementacija nije potrebna za spojnu tablicu
        throw new UnsupportedOperationException("Select operation is not supported for MovieGenre.");
    
    }
    
    public void delete(int movieId,int genreId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE_GENRE)) {

            stmt.setInt(MOVIE_ID, movieId);
            stmt.setInt(GENRE_ID, genreId);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<MovieGenre> select(int id) throws Exception {
        // Implementacija nije potrebna za spojnu tablicu
        throw new UnsupportedOperationException("Select operation is not supported for MovieGenre.");
    }

 
    public List<MovieGenre> selectAll() throws Exception {
        List<MovieGenre> movieGenres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE_GENRES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movieGenres.add(new MovieGenre(
                    
                        rs.getInt(MOVIE_ID),
                        rs.getInt(GENRE_ID)));
            }
        }
        return movieGenres;
    }
}
