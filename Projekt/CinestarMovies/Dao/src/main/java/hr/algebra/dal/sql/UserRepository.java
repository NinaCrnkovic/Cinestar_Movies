/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.User;

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
public class UserRepository implements Repository<User> {

    private static final String ID_USER = "IDUser";
    private static final String USERNAME = "Username";
    private static final String PWD_HASH = "PwdHash";
    private static final String PWD_SALT = "PwdSalt";
    private static final String ACCOUNT_TYPE_ID = "AccountTypeID";

    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?,?) }";
    private static final String UPDATE_USER = "{ CALL UpdateUser (?,?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?) }";
    private static final String SELECT_USER = "{ CALL SelectUser (?) }";
    private static final String SELECT_USERS = "{ CALL SelectUsers }";

    @Override
    public int create(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PWD_HASH, user.getPassword());
            stmt.setString(PWD_SALT, ""); // Empty salt for now, to be generated during password hashing
            stmt.setInt(ACCOUNT_TYPE_ID, user.getAccountTypeId());
            stmt.registerOutParameter(ID_USER, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void createMany(List<User> users) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            for (User user : users) {

                stmt.setString(USERNAME, user.getUsername());
                stmt.setString(PWD_HASH, user.getPassword());
                stmt.setString(PWD_SALT, ""); // Empty salt for now, to be generated during password hashing
                stmt.setInt(ACCOUNT_TYPE_ID, user.getAccountTypeId());
                stmt.registerOutParameter(ID_USER, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_USER)) {

            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PWD_HASH, user.getPassword());
            stmt.setString(PWD_SALT, ""); // Empty salt for now, to be generated during password hashing
            stmt.setInt(ACCOUNT_TYPE_ID, user.getAccountTypeId());
            stmt.registerOutParameter(ID_USER, Types.INTEGER);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            stmt.setInt(ID_USER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<User> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER)) {
            stmt.setInt(ID_USER, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString(USERNAME),
                            rs.getString(PWD_HASH),
                            rs.getInt(ACCOUNT_TYPE_ID)
                    );
                    user.setId(rs.getInt(ID_USER));
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> selectAll() throws Exception {
        List<User> users = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USERS); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getString(USERNAME),
                        rs.getString(PWD_HASH),
                        rs.getInt(ACCOUNT_TYPE_ID)
                );
                user.setId(rs.getInt(ID_USER));
                users.add(user);
            }
        }
        return users;
    }

    

}
