/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.AccountType;
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
public class AccountTypeRepository implements Repository<AccountType> {

    private static final String ID_ACCOUNT_TYPE = "ID";
    private static final String TYPE = "Type";

    private static final String CREATE_ACCOUNT_TYPE = "{ CALL CreateAccountType (?,?) }";
    private static final String UPDATE_ACCOUNT_TYPE = "{ CALL UpdateAccountType (?,?) }";
    private static final String DELETE_ACCOUNT_TYPE = "{ CALL DeleteAccountType (?) }";
    private static final String SELECT_ACCOUNT_TYPE = "{ CALL SelectAccountType (?) }";
    private static final String SELECT_ACCOUNT_TYPES = "{ CALL SelectAccountTypes }";

    @Override
    public int create(AccountType accountType) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ACCOUNT_TYPE)) {

            stmt.setString(TYPE, accountType.getType());
            stmt.registerOutParameter(ID_ACCOUNT_TYPE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_ACCOUNT_TYPE);
        }
    }

    @Override
    public void createManny(List<AccountType> accountTypes) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ACCOUNT_TYPE)) {

            for (AccountType accountType : accountTypes) {
                stmt.setString(TYPE, accountType.getType());
                stmt.registerOutParameter(ID_ACCOUNT_TYPE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, AccountType accountType) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ACCOUNT_TYPE)) {

            stmt.setString(TYPE, accountType.getType());
            stmt.setInt(ID_ACCOUNT_TYPE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ACCOUNT_TYPE)) {

            stmt.setInt(ID_ACCOUNT_TYPE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<AccountType> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACCOUNT_TYPE)) {

            stmt.setInt(ID_ACCOUNT_TYPE, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new AccountType(
                            rs.getInt(ID_ACCOUNT_TYPE),
                            rs.getString(TYPE)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<AccountType> selectAll() throws Exception {
        List<AccountType> accountTypes = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACCOUNT_TYPES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                accountTypes.add(new AccountType(
                        rs.getInt(ID_ACCOUNT_TYPE),
                        rs.getString(TYPE)));
            }
        }
        return accountTypes;
    }

   
}
