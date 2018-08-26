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
                    "first_name varchar(55), last_name varchar(55), telephony varchar(15), cellphone varchar(15), " +
                    "address varchar(100), addressNumber varchar(5), profession varchar(30), dateOfBirth varchar(10), " +
                    "pilatesIndication varchar(60), activities varchar(100), painActivity varchar(100), medicines varchar(55), " +
                    "smoke varchar(20), disease varchar(55), surgery varchar(55), accident varchar(100), pain varchar(100), " +
                    "goal varchar(100) , timeLine varchar(200)) ");
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
            preparedStatement = connection.prepareStatement("INSERT INTO person (first_name, last_name, telephony, cellphone, " +
                    "address, addressNumber, profession, dateOfBirth, pilatesIndication, activities, painActivity, medicines," +
                    "smoke, disease, surgery, accident, pain, goal, timeLine )" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getTelephony());
            preparedStatement.setString(4, person.getCellphone());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setString(6, person.getAddressNumber());
            preparedStatement.setString(7, person.getProfession());
            preparedStatement.setString(8, person.getDateOfBirth());
            preparedStatement.setString(9, person.getPilatesIndication());
            preparedStatement.setString(10, person.getActivities());
            preparedStatement.setString(11, person.getPainActivity());
            preparedStatement.setString(12, person.getMedicines());
            preparedStatement.setString(13, person.getSmoke());
            preparedStatement.setString(14, person.getDisease());
            preparedStatement.setString(15, person.getSurgery());
            preparedStatement.setString(16, person.getAccident());
            preparedStatement.setString(17, person.getPain());
            preparedStatement.setString(18, person.getGoal());
            preparedStatement.setString(19, person.getTimeLine());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO person (first_name, last_name, telephony, cellphone, " +
                    "address, addressNumber, profession, dateOfBirth, pilatesIndication, activities, painActivity, medicines," +
                    "smoke, disease, surgery, accident, pain, goal, timeLine )" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
                person.setTelephony(resultSet.getString("telephony"));
                person.setCellphone(resultSet.getString("cellphone"));
                person.setAddress(resultSet.getString("address"));
                person.setAddressNumber(resultSet.getString("addressNumber"));
                person.setProfession(resultSet.getString("profession"));
                person.setDateOfBirth(resultSet.getString("dateOfBirth"));
                person.setPilatesIndication(resultSet.getString("pilatesIndication"));
                person.setActivities(resultSet.getString("activities"));
                person.setPainActivity(resultSet.getString("painActivity"));
                person.setMedicines(resultSet.getString("medicines"));
                person.setSmoke(resultSet.getString("smoke"));
                person.setDisease(resultSet.getString("disease"));
                person.setSurgery(resultSet.getString("surgery"));
                person.setAccident(resultSet.getString("accident"));
                person.setPain(resultSet.getString("pain"));
                person.setGoal(resultSet.getString("goal"));
                person.setTimeLine(resultSet.getString("timeLine"));
            }
            System.out.println("SELECT * FROM person WHERE ID = ?");
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
                person.setTelephony(resultSet.getString("telephony"));
                person.setCellphone(resultSet.getString("cellphone"));
                person.setAddress(resultSet.getString("address"));
                person.setAddressNumber(resultSet.getString("addressNumber"));
                person.setProfession(resultSet.getString("profession"));
                person.setDateOfBirth(resultSet.getString("dateOfBirth"));
                person.setPilatesIndication(resultSet.getString("pilatesIndication"));
                person.setActivities(resultSet.getString("activities"));
                person.setPainActivity(resultSet.getString("painActivity"));
                person.setMedicines(resultSet.getString("medicines"));
                person.setSmoke(resultSet.getString("smoke"));
                person.setDisease(resultSet.getString("disease"));
                person.setSurgery(resultSet.getString("surgery"));
                person.setAccident(resultSet.getString("accident"));
                person.setPain(resultSet.getString("pain"));
                person.setGoal(resultSet.getString("goal"));
                person.setTimeLine(resultSet.getString("timeLine"));

                persons.add(person);
            }
            System.out.println("SELECT * FROM person");
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
                    "first_name = ?, last_name = ?, telephony = ?, cellphone = ?, address = ?, addressNumber = ?, profession = ?, " +
                    "dateOfBirth = ?, pilatesIndication = ?, activities = ?, painActivity = ?, medicines = ?, smoke = ?, disease = ?, " +
                    "surgery = ?, accident = ?, pain = ?, goal = ?, timeLine = ? WHERE ID = ?");

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getTelephony());
            preparedStatement.setString(4, person.getCellphone());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setString(6, person.getAddressNumber());
            preparedStatement.setString(7, person.getProfession());
            preparedStatement.setString(8, person.getDateOfBirth());
            preparedStatement.setString(9, person.getPilatesIndication());
            preparedStatement.setString(10, person.getActivities());
            preparedStatement.setString(11, person.getPainActivity());
            preparedStatement.setString(12, person.getMedicines());
            preparedStatement.setString(13, person.getSmoke());
            preparedStatement.setString(14, person.getDisease());
            preparedStatement.setString(15, person.getSurgery());
            preparedStatement.setString(16, person.getAccident());
            preparedStatement.setString(17, person.getPain());
            preparedStatement.setString(18, person.getGoal());
            preparedStatement.setString(19, person.getTimeLine());
            preparedStatement.setInt(20, id);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE person SET " +
                    "first_name = ?, last_name = ?, telephony = ?, cellphone = ?, address = ?, addressNumber = ?, profession = ?, " +
                    "dateOfBirth = ?, pilatesIndication = ?, activities = ?, painActivity = ?, medicines = ?, smoke = ?, disease = ?, " +
                    "surgery = ?, accident = ?, pain = ?, goal = ?, timeLine = ? WHERE ID = ?");
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
