package com.company;

import com.daoImplementation.PersonDaoImpl;
import com.entities.Person;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        PersonDaoImpl pdi = new PersonDaoImpl();
       // pdi.createPersonTable();

        Person person = new Person("Aline", "Castilho", "Nenhum registro anterior",
                "Nenhum", "Nenhum", "Nunhum");
        pdi.update(person,4);
        //pdi.insert(person);
        //pdi.delete(1);
       //person =  pdi.selectPersonById(2);

       //System.out.println(person.toString());
       //System.out.println(persons.toString());

        List<Person> persons = pdi.selectAll();
       for (Person person1 : persons)
       {
           System.out.println(person1.toString());
       }



    }
}