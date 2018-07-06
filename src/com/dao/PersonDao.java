package com.dao;

import com.entities.Person;

import java.util.List;

public interface PersonDao {

    void createPersonTable();

    void insert(Person person);

    Person selectPersonById(int id);

    List<Person> selectAll();

    void delete(int id);

    void update(Person person, int id);

}
