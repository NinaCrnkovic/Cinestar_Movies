/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Person;
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
public class PersonRepository implements Repository<Person> {

    private static final String ID_PERSON = "IDPerson";
    private static final String NAME = "Name";

    private static final String CREATE_PERSON = "{ CALL CreatePerson (?,?) }";
    private static final String UPDATE_PERSON = "{ CALL UpdatePerson (?,?) }";
    private static final String DELETE_PERSON = "{ CALL DeletePerson (?) }";
    private static final String SELECT_PERSON = "{ CALL SelectPerson (?) }";
    private static final String SELECT_PERSONS = "{ CALL SelectPersons }";

    @Override
    public int create(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {

            stmt.setString(NAME, person.getName());
            stmt.registerOutParameter(ID_PERSON, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_PERSON);
        }
    }

// 
//    public void createManny(List<Person> persons) throws Exception {
//        DataSource dataSource = DataSourceSingleton.getInstance();
//        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON)) {
//
//            for (Person person : persons) {
//                stmt.setString(NAME, person.getName());
//                stmt.registerOutParameter(ID_PERSON, Types.INTEGER);
//
//                stmt.executeUpdate();
//            }
//        }
//    }

    @Override
    public void update(int id, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_PERSON)) {

            stmt.setString(NAME, person.getName());
            stmt.setInt(ID_PERSON, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_PERSON)) {

            stmt.setInt(ID_PERSON, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Person> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PERSON)) {

            stmt.setInt(ID_PERSON, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(NAME)));
                }
            }
        }
        return Optional.empty();
    }

//
//    public List<Person> selectAll() throws Exception {
//        List<Person> persons = new ArrayList<>();
//        DataSource dataSource = DataSourceSingleton.getInstance();
//        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PERSONS); ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                persons.add(new Person(
//                        rs.getInt(ID_PERSON),
//                        rs.getString(NAME)));
//            }
//        }
//        return persons;
//    }

  

}

