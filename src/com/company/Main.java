package com.company;

import com.dao.PersonDao;
import com.dao.ScheduleDao;
import com.daoImplementation.PersonDaoImpl;
import com.daoImplementation.ScheduleDaoImpl;
import com.entities.Person;
import com.entities.Schedule;
import com.gui.DatabaseFrame;
import com.gui.EspacoLifeFrame;
import com.gui.InitGui;
import com.xmlFile.DatabaseInformation;
import com.xmlFile.WriteXMLFile;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //DatabaseFrame.showDatabaseFrame(args);
        //EspacoLifeFrame.showEspacoLife(args);
        InitGui.main(args);
        //WriteXMLFile write = new WriteXMLFile();
        //DatabaseInformation databaseInformation = new DatabaseInformation();
        //write.parseXml(databaseInformation);

        ScheduleDao sdi = new ScheduleDaoImpl();
        sdi.createScheduleTable();

        PersonDaoImpl pdi = new PersonDaoImpl();
        pdi.createPersonTable();

        //pdi.update(person,5);
       // pdi.insert(person);
        //pdi.delete(1);
       //person =  pdi.selectPersonById(2);

       //System.out.println(person.toString());
       //System.out.println(persons.toString());

        /*List<Person> persons = pdi.selectAll();
       for (Person person1 : persons)
       {
           System.out.println(person1.toString());
       }*/



    }
}