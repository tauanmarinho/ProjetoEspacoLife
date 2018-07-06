package com.company;

import com.daoImplementation.PersonDaoImpl;

public class Main {

    public static void main(String[] args) {

        PersonDaoImpl pdi = new PersonDaoImpl();
        pdi.createPersonTable();

    }
}