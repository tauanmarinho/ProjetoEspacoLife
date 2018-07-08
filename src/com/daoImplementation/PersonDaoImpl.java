package com.daoImplementation;

import com.dao.PersonDao;
import com.entities.Person;
import com.util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    @Override
    public void createPersonTable() {
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS person (id int primary key unique auto_increment," +
                    "first_name varchar(55), last_name varchar(55), timeLine varchar(200), surgery varchar(55), " +
                    "medicines varchar(55), pain varchar(55))");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null)
            {
                try {
                    connection.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insert(Person person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO person (first_name, last_name, timeLine, " +
                    "surgery, medicines, pain )" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getMedicines());
            preparedStatement.setString(4, person.getPain());
            preparedStatement.setString(5, person.getSurgery());
            preparedStatement.setString(6, person.getTimeLine());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO person (first_name, last_name, timeLine, " +
                            "surgery, medicines, pain )" +
                            "VALUES (?, ?, ?, ?, ?, ?)");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public Person selectPersonById(int id) {
        Person person = new Person();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setMedicines(resultSet.getString("medicines"));
                person.setPain(resultSet.getString("pain"));
                person.setSurgery(resultSet.getString("surgery"));
                person.setTimeLine(resultSet.getString("timeLine"));;
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return person;
    }

    @Override
    public List<Person> selectAll() {
        List<Person> persons = new ArrayList<Person>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person");

            while(resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setMedicines(resultSet.getString("medicines"));
                person.setPain(resultSet.getString("pain"));
                person.setSurgery(resultSet.getString("surgery"));
                person.setTimeLine(resultSet.getString("timeLine"));

                persons.add(person);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
        return persons;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM person WHERE id = ?");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Person person, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE person SET " +
                    "first_name = ?, last_name = ?" +
                    ", medicines = ?, pain = ?, surgery = ?, timeLine = ? WHERE ID = ?");

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getMedicines());
            preparedStatement.setString(4, person.getPain());
            preparedStatement.setString(5, person.getSurgery());
            preparedStatement.setString(6, person.getTimeLine());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE person SET first_name = ?, last_name = ?" +
                    ", medicines = ?, pain = ?, surgery = ?, timeLine = ? WHERE id = ?");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
