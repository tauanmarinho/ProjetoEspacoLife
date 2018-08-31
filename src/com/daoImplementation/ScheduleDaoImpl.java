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
                    "sundayStart varchar(110), sundayEnd varchar(110), mondayStart varchar(110), mondayEnd varchar(110), tuesdayStart varchar(110), tuesdayEnd varchar(110)," +
                    " wednesdayStart varchar(110), wednesdayEnd varchar(110), thursdayStart varchar(110), thursdayEnd varchar(110), " +
                    "fridayStart varchar(110), fridayEnd varchar(110), saturdayStart varchar(110), saturdayEnd varchar(110), name varchar(110))");
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
            preparedStatement = connection.prepareStatement("INSERT INTO schedule (sundayStart, sundayEnd, " +
                    "mondayStart, mondayEnd, tuesdayStart, tuesdayEnd, wednesdayStart, wednesdayEnd, " +
                    "thursdayStart, thursdayEnd, fridayStart, fridayEnd, saturdayStart, saturdayEnd, name )" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, schedule.getSundayStart());
            preparedStatement.setString(2, schedule.getSundayEnd());
            preparedStatement.setString(3, schedule.getMondayStart());
            preparedStatement.setString(4, schedule.getMondayEnd());
            preparedStatement.setString(5, schedule.getTuesdayStart());
            preparedStatement.setString(6, schedule.getTuesdayEnd());
            preparedStatement.setString(7, schedule.getWednesdayStart());
            preparedStatement.setString(8, schedule.getWednesdayEnd());
            preparedStatement.setString(9, schedule.getThursdayStart());
            preparedStatement.setString(10, schedule.getThursdayEnd());
            preparedStatement.setString(11, schedule.getFridayStart());
            preparedStatement.setString(12, schedule.getFridayEnd());
            preparedStatement.setString(13, schedule.getSaturdayStart());
            preparedStatement.setString(14, schedule.getSaturdayEnd());
            preparedStatement.setString(15, schedule.getName());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO schedule (sundayStart, sundayEnd, " +
                    "mondayStart, mondayEnd, tuesdayStart, tuesdayEnd, wednesdayStart, wednesdayEnd, " +
                    "thursdayStart, thursdayEnd, fridayStart, fridayEnd, saturdayStart, saturdayEnd, name )" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
                schedule.setSundayStart(resultSet.getString("sundayStart"));
                schedule.setSundayEnd(resultSet.getString("sundayEnd"));
                schedule.setMondayStart(resultSet.getString("mondayStart"));
                schedule.setMondayEnd(resultSet.getString("mondayEnd"));
                schedule.setTuesdayStart(resultSet.getString("tuesdayStart"));
                schedule.setTuesdayEnd(resultSet.getString("tuesdayEnd"));
                schedule.setWednesdayStart(resultSet.getString("wednesdayStart"));
                schedule.setWednesdayEnd(resultSet.getString("wednesdayEnd"));
                schedule.setThursdayStart(resultSet.getString("thursdayStart"));
                schedule.setThursdayEnd(resultSet.getString("thursdayEnd"));
                schedule.setFridayStart(resultSet.getString("fridayStart"));
                schedule.setFridayEnd(resultSet.getString("fridayENd"));
                schedule.setSaturdayStart(resultSet.getString("saturdayEnd"));
                schedule.setSaturdayEnd(resultSet.getString("saturdayEnd"));
                schedule.setName(resultSet.getString("name"));
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
                schedule.setSundayStart(resultSet.getString("sundayStart"));
                schedule.setSundayEnd(resultSet.getString("sundayEnd"));
                schedule.setMondayStart(resultSet.getString("mondayStart"));
                schedule.setMondayEnd(resultSet.getString("mondayEnd"));
                schedule.setTuesdayStart(resultSet.getString("tuesdayStart"));
                schedule.setTuesdayEnd(resultSet.getString("tuesdayEnd"));
                schedule.setWednesdayStart(resultSet.getString("wednesdayStart"));
                schedule.setWednesdayEnd(resultSet.getString("wednesdayEnd"));
                schedule.setThursdayStart(resultSet.getString("thursdayStart"));
                schedule.setThursdayEnd(resultSet.getString("thursdayEnd"));
                schedule.setFridayStart(resultSet.getString("fridayStart"));
                schedule.setFridayEnd(resultSet.getString("fridayENd"));
                schedule.setSaturdayStart(resultSet.getString("saturdayEnd"));
                schedule.setSaturdayEnd(resultSet.getString("saturdayEnd"));
                schedule.setName(resultSet.getString("name"));

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
                    "sundayStart = ?, sundayEnd = ?, mondayStart = ?, mondayEnd = ?, tuesdayStart = ?, tuesdayEnd = ?" +
                    ", wednesdayStart = ?, wednesdayEnd = ?, thursdayStart = ?, thursdayEnd = ?, fridayStart = ?, fridayEnd = ?, " +
                    "saturdayStart = ?, saturdayEnd = ?, name = ? WHERE ID = ?");

            preparedStatement.setString(1, schedule.getSundayStart());
            preparedStatement.setString(2, schedule.getSundayEnd());
            preparedStatement.setString(3, schedule.getMondayStart());
            preparedStatement.setString(4, schedule.getMondayEnd());
            preparedStatement.setString(5, schedule.getTuesdayStart());
            preparedStatement.setString(6, schedule.getTuesdayEnd());
            preparedStatement.setString(7, schedule.getWednesdayStart());
            preparedStatement.setString(8, schedule.getWednesdayEnd());
            preparedStatement.setString(9, schedule.getThursdayStart());
            preparedStatement.setString(10, schedule.getThursdayEnd());
            preparedStatement.setString(11, schedule.getFridayStart());
            preparedStatement.setString(12, schedule.getFridayEnd());
            preparedStatement.setString(13, schedule.getSaturdayStart());
            preparedStatement.setString(14, schedule.getSaturdayEnd());
            preparedStatement.setString(15, schedule.getName());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE schedule SET " +
                    "sundayStart = ?, sundayEnd = ?, mondayStart = ?, mondayEnd = ?, tuesdayStart = ?, tuesdayEnd = ?" +
                    ", wednesdayStart = ?, wednesdayEnd = ?, thursdayStart = ?, thursdayEnd = ?, fridayStart = ?, fridayEnd = ?, " +
                    "saturdayStart = ?, saturdayEnd = ?, name = ? WHERE ID = ?");
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
