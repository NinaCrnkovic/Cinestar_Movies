/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.FavoriteMovie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.util.Optional;

/**
 *
 * @author Nina
 */
public class FavoriteMovieRepository implements Repository<FavoriteMovie> {

    private static final String ID_FAVORITE_MOVIE = "ID";
    private static final String USER_ID = "UserID";
    private static final String MOVIE_ID = "MovieID";

// Stored Procedure Calls
    private static final String CREATE_FAVORITE_MOVIE = "{ CALL CreateFavoriteMovie (?,?,?) }";
    private static final String UPDATE_FAVORITE_MOVIE = "{ CALL UpdateFavoriteMovie (?,?,?) }";
    private static final String DELETE_FAVORITE_MOVIE = "{ CALL DeleteFavoriteMovie (?) }";
    private static final String SELECT_FAVORITE_MOVIE = "{ CALL SelectFavoriteMovie (?) }";
    private static final String SELECT_FAVORITE_MOVIES = "{ CALL SelectFavoriteMovies }";

    @Override
    public int create(FavoriteMovie favoriteMovie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_FAVORITE_MOVIE)) {

            stmt.setInt(USER_ID, favoriteMovie.getUserId());
            stmt.setInt(MOVIE_ID, favoriteMovie.getMovieId());
            stmt.registerOutParameter(ID_FAVORITE_MOVIE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_FAVORITE_MOVIE);
        }
    }

    @Override
    public void createManny(List<FavoriteMovie> favoriteMovies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_FAVORITE_MOVIE)) {

            for (FavoriteMovie favoriteMovie : favoriteMovies) {
                stmt.setInt(USER_ID, favoriteMovie.getUserId());
                stmt.setInt(MOVIE_ID, favoriteMovie.getMovieId());
                stmt.registerOutParameter(ID_FAVORITE_MOVIE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, FavoriteMovie favoriteMovie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_FAVORITE_MOVIE)) {

            stmt.setInt(USER_ID, favoriteMovie.getUserId());
            stmt.setInt(MOVIE_ID, favoriteMovie.getMovieId());
            stmt.setInt(ID_FAVORITE_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_FAVORITE_MOVIE)) {

            stmt.setInt(ID_FAVORITE_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<FavoriteMovie> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_FAVORITE_MOVIE)) {

            stmt.setInt(USER_ID, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new FavoriteMovie(
                            rs.getInt(ID_FAVORITE_MOVIE),
                            rs.getInt(MOVIE_ID),
                            rs.getInt(USER_ID)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<FavoriteMovie> selectAll() throws Exception {
        List<FavoriteMovie> favoriteMovies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_FAVORITE_MOVIES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                favoriteMovies.add(new FavoriteMovie(
                        rs.getInt(ID_FAVORITE_MOVIE),
                        rs.getInt(MOVIE_ID),
                            rs.getInt(USER_ID)));
            }
        }
        return favoriteMovies;
    }

    

}
