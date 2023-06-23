/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.MoviePersonRole;
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
public class MoviePersonRoleRepository implements Repository<MoviePersonRole> {

    private static final String ID = "ID";
    private static final String MOVIE_ID = "MovieID";
    private static final String PERSON_ID = "PersonID";
    private static final String ROLE_ID = "RoleID";

    private static final String CREATE_MOVIE_PERSON_ROLE = "{ CALL CreateMoviePersonRole (?,?,?,?) }";
    private static final String UPDATE_MOVIE_PERSON_ROLE = "{ CALL UpdateMoviePersonRole (?,?,?,?) }";
    private static final String DELETE_MOVIE_PERSON_ROLE = "{ CALL DeleteMoviePersonRole (?) }";
    private static final String SELECT_MOVIE_PERSON_ROLE = "{ CALL SelectMoviePersonRole (?) }";
    private static final String SELECT_MOVIE_PERSON_ROLES = "{ CALL SelectMoviePersonRoles }";

    @Override
    public int create(MoviePersonRole moviePersonRole) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON_ROLE)) {

            stmt.setInt(MOVIE_ID, moviePersonRole.getMovieId());
            stmt.setInt(PERSON_ID, moviePersonRole.getPersonId());
            stmt.setInt(ROLE_ID, moviePersonRole.getRoleId());
            stmt.registerOutParameter(ID, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID);
        }
    }

     @Override
    public void createManny(List<MoviePersonRole> moviePersonRoles) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON_ROLE)) {

            for (MoviePersonRole moviePersonRole : moviePersonRoles) {
                stmt.setInt(MOVIE_ID, moviePersonRole.getMovieId());
                stmt.setInt(PERSON_ID, moviePersonRole.getPersonId());
                stmt.setInt(ROLE_ID, moviePersonRole.getRoleId());
                stmt.registerOutParameter(ID, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, MoviePersonRole moviePersonRole) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_PERSON_ROLE)) {

            stmt.setInt(MOVIE_ID, moviePersonRole.getMovieId());
            stmt.setInt(PERSON_ID, moviePersonRole.getPersonId());
            stmt.setInt(ROLE_ID, moviePersonRole.getRoleId());
            stmt.setInt(ID, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE_PERSON_ROLE)) {

            stmt.setInt(ID, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<MoviePersonRole> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE_PERSON_ROLE)) {

            stmt.setInt(ID, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new MoviePersonRole(
                            rs.getInt(ID),
                            rs.getInt(MOVIE_ID),
                            rs.getInt(PERSON_ID),
                            rs.getInt(ROLE_ID)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<MoviePersonRole> selectAll() throws Exception {
        List<MoviePersonRole> moviePersonRoles = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE_PERSON_ROLES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                moviePersonRoles.add(new MoviePersonRole(
                        rs.getInt(ID),
                        rs.getInt(MOVIE_ID),
                        rs.getInt(PERSON_ID),
                        rs.getInt(ROLE_ID)));
            }
        }
        return moviePersonRoles;
    }

   
}
