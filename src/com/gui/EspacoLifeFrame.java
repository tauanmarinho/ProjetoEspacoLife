package com.gui;

import com.daoImplementation.PersonDaoImpl;
import com.entities.Person;
import com.xmlFile.DatabaseInformation;
import com.xmlFile.WriteXMLFile;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.net.URL;

public class EspacoLifeFrame implements Initializable {

    public PasswordField passwordTextField;
    public TextField userTextField;
    public TextField urlTextField;
    public TextField portTextField;
    public TextField databaseTextField;
    public Label mainMessage;
    public Label nameClient;
    public Label day;
    public ListView<String> listDayQuery;
    public ListView<TitledPane> userListReg;
    public ListView<String> timeListView;

    private final int TIME_START_MORNING = 7;
    private final int TIME_START_AFTERNOON = 13;
    private final int TIME_FINISH_MORNING = 12;
    private final int TIME_FINISH_AFTERNOON = 21;

    DatabaseInformation databaseInformation;
    WriteXMLFile write;

    String months[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"};


    public void updateUserList(){
        PersonDaoImpl pdi = new PersonDaoImpl();
        for (Person eachPerson : pdi.selectAll())
        {
            TitledPane userTitledPane = new TitledPane();
            userTitledPane.textProperty().set(eachPerson.getFirstName() + " " + eachPerson.getLastName());
            userListReg.getItems().add(userTitledPane);
            System.out.println(eachPerson.getFirstName());
        }

    }

    public void generateTime()
    {
        for (int time = TIME_START_MORNING; time < TIME_FINISH_MORNING; time++)
        {
            timeListView.getItems().add("" + time + ":00");
            timeListView.getItems().add("" + time + ":30");
        }

        timeListView.getItems().add("12:00 ----------------------------------------------- Almoço -----------------------------------------------");

        for (int time = TIME_START_AFTERNOON; time < TIME_FINISH_AFTERNOON; time++)
        {
            timeListView.getItems().add("" + time + ":00");
            timeListView.getItems().add("" + time + ":30");
        }


    }

    public void updateDatabase()
    {
        write = new WriteXMLFile();
        databaseInformation = new DatabaseInformation();
        write.parseXml(databaseInformation);

        userTextField.textProperty().set(databaseInformation.getUser());
        passwordTextField.textProperty().set(databaseInformation.getPassword());
        urlTextField.textProperty().set(databaseInformation.getUrl());
        portTextField.textProperty().set(databaseInformation.getPort());
        databaseTextField.textProperty().set(databaseInformation.getDatabase());
    }

    private void updateMain(){
        Date dNow = new Date();
        GregorianCalendar gcalendar = new GregorianCalendar();
        System.out.print("Time: ");
        System.out.print(gcalendar.get(Calendar.HOUR) + ":");
        System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gcalendar.get(Calendar.SECOND));

        SimpleDateFormat ft =
                new SimpleDateFormat ("a");

        if (ft.format(dNow).equals("PM") && gcalendar.get(Calendar.HOUR) < 7)
        {
            mainMessage.textProperty().set("Boa tarde, ");
        } else if (ft.format(dNow).equals("AM")){
            mainMessage.textProperty().set("Bom dia, ");
        } else
        {
            mainMessage.textProperty().set("Boa noite, ");
        }

        nameClient.textProperty().set("Aline");
        ft = new SimpleDateFormat ("E");

        day.textProperty().set("" + ft.format(dNow) + " - " + gcalendar.get(Calendar.DAY_OF_MONTH) + ", " + months[gcalendar.get(Calendar.MONTH)] +
                " de " + gcalendar.get(Calendar.YEAR));



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateDatabase();
        updateMain();
        updateUserList();
        generateTime();
    }

    public void saveDatabase(ActionEvent actionEvent) throws IOException {
        databaseInformation.setUser(userTextField.getText());
        databaseInformation.setPassword(passwordTextField.getText());
        databaseInformation.setUrl(urlTextField.getText());
        databaseInformation.setPort(portTextField.getText());
        databaseInformation.setDatabase(databaseTextField.getText());
        System.out.println("Button save was pressed");
        write.createXml(databaseInformation);
        AlertFrame alert = new AlertFrame();
        alert.DisplayAlert("Salvo no Banco de dados");
    }

    public void query(ActionEvent actionEvent) {
        System.out.println("Query button was pressed");
        //ScheduleQuery.main(args);
    }

    public void ContractGeneration(ActionEvent actionEvent)
    {
        System.out.println("Search user button was pressed");
    }

    public void SearchUser(ActionEvent actionEvent)
    {
        System.out.println("Contract Generation button was pressed");
    }

    public void physicalAssessment(ActionEvent actionEvent) {
    }
}
