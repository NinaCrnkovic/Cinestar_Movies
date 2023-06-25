/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.SQLRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class SqlRepository implements SQLRepository {

    private static final String CLEAR_DATABASE = "{ CALL clearTables }";


    @Override
    public void clearDatabase() {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
            stmt.execute(CLEAR_DATABASE);
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, "Failed to clear the database", ex);
        }
    }

}
