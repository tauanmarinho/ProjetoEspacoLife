package com.dao;

import com.entities.Schedule;

import java.util.List;

public interface ScheduleDao {

    void createScheduleTable();

    void insert(Schedule person);

    Schedule selectScheduleById(int id);

    List<Schedule> selectAll();

    void delete(int id);

    void update(Schedule person, int id);
}
