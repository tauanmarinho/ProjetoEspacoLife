package com.daoImplementation;

import com.dao.ScheduleDao;
import com.entities.Schedule;
import com.util.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {

    @Override
    public void createScheduleTable() {
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionConfiguration.getConnectionSchedule();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS schedule (id int primary key unique auto_increment," +
                    "sunday varchar(110), monday varchar(110), tuesday varchar(110), wednesday varchar(110), thursday varchar(110), " +
                    "friday varchar(110), saturday varchar(110), name varchar(110))");
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
    public void insert(Schedule schedule) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnectionSchedule();
            preparedStatement = connection.prepareStatement("INSERT INTO schedule (sunday, monday, tuesday, wednesday, " +
                    "thursday, friday, saturday, name )" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, schedule.getSunday());
            preparedStatement.setString(2, schedule.getMonday());
            preparedStatement.setString(3, schedule.getTuesday());
            preparedStatement.setString(4, schedule.getWednesday());
            preparedStatement.setString(5, schedule.getThursday());
            preparedStatement.setString(6, schedule.getFriday());
            preparedStatement.setString(7, schedule.getSaturday());
            preparedStatement.setString(8, schedule.getName());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO schedule (sunday, monday, tuesday, wednesday, " +
                    "thursday, friday, saturday, name )" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
    public Schedule selectScheduleById(int id) {
        Schedule schedule = new Schedule();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnectionSchedule();
            preparedStatement = connection.prepareStatement("SELECT * FROM schedule WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                schedule.setId(resultSet.getInt("id"));
                schedule.setSunday(resultSet.getString("sunday"));
                schedule.setMonday(resultSet.getString("monday"));
                schedule.setTuesday(resultSet.getString("tuesday"));
                schedule.setWednesday(resultSet.getString("wednesday"));
                schedule.setThursday(resultSet.getString("thursday"));
                schedule.setFriday(resultSet.getString("friday"));
                schedule.setSaturday(resultSet.getString("saturday"));;
                schedule.setName(resultSet.getString("name"));;
            }
            System.out.println("SELECT * FROM schedule WHERE ID = ?");
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
        return schedule;
    }

    @Override
    public List<Schedule> selectAll() {
        List<Schedule> schedules = new ArrayList<Schedule>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnectionSchedule();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM schedule");

            while(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getInt("id"));
                schedule.setSunday(resultSet.getString("sunday"));
                schedule.setMonday(resultSet.getString("monday"));
                schedule.setTuesday(resultSet.getString("tuesday"));
                schedule.setWednesday(resultSet.getString("wednesday"));
                schedule.setThursday(resultSet.getString("thursday"));
                schedule.setFriday(resultSet.getString("friday"));
                schedule.setSaturday(resultSet.getString("saturday"));
                schedule.setSaturday(resultSet.getString("name"));

                schedules.add(schedule);
            }
            System.out.println("SELECT * FROM schedule");
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
        return schedules;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnectionSchedule();
            preparedStatement = connection.prepareStatement("DELETE FROM schedule WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM schedule WHERE id = ?");
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
    public void update(Schedule schedule, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnectionSchedule();
            preparedStatement = connection.prepareStatement("UPDATE schedule SET " +
                    "sunday = ?, monday = ?, tuesday = ?" +
                    ", wednesday = ?, thursday = ?, friday = ?, saturday = ?, name = ? WHERE ID = ?");

            preparedStatement.setString(1, schedule.getSunday());
            preparedStatement.setString(2, schedule.getMonday());
            preparedStatement.setString(3, schedule.getTuesday());
            preparedStatement.setString(4, schedule.getWednesday());
            preparedStatement.setString(5, schedule.getThursday());
            preparedStatement.setString(6, schedule.getFriday());
            preparedStatement.setString(7, schedule.getSaturday());
            preparedStatement.setString(8, schedule.getName());
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE schedule SET \" +\n" +
                    "                    \"sunday = ?, monday = ?, tuesday = ?\" +\n" +
                    "                    \", wednesday = ?, thursday = ?, friday = ?, saturday = ?, name = ? WHERE ID = ?");
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
