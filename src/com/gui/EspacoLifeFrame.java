package com.gui;

import com.daoImplementation.PersonDaoImpl;
import com.daoImplementation.ScheduleDaoImpl;
import com.entities.InformationDate;
import com.entities.InformationTime;
import com.entities.Person;
import com.entities.Schedule;
import com.xmlFile.DatabaseInformation;
import com.xmlFile.WriteXMLFile;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javax.xml.soap.Text;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.net.URL;

import static jdk.nashorn.internal.runtime.JSType.toInteger;

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
    public TextField name;
    public TextField lastName;
    public TextField telephony;
    public TextField cellphone;
    public TextField address;
    public TextField addressNumber;
    public TextField profession;
    public TextField pilatesIndication;
    public TextField dateOfBirth;
    public CheckBox mondayClickedButton;
    public TextField startMonday;
    public TextField endMonday;
    public TextField startTuesday;
    public CheckBox tuesdayClickedButton;
    public TextField endTuesday;
    public TextField startWednesday;
    public CheckBox wednesdayClickedButton;
    public TextField endWednesday;
    public TextField startThursday;
    public CheckBox thursdayClickedButton;
    public TextField endThursday;
    public TextField startFriday;
    public CheckBox fridayClickedButton;
    public TextField endFriday;
    public CheckBox activitiesYesButton;
    public CheckBox activitiesNoButton;
    public TextField frequencyActivities;
    public TextField eachDisease;
    public CheckBox diseaseYesButton;
    public CheckBox diseaseNoButton;
    public CheckBox surgeryNoButton;
    public TextField eachSurgery;
    public CheckBox surgeryYesButton;
    public TextField eachPain;
    public CheckBox painNoButton;
    public CheckBox painYesButton;
    public TextField eachMedicine;
    public CheckBox medicineNoButton;
    public CheckBox medicineYesButton;
    public CheckBox smokeNoButton;
    public CheckBox smokeYesButton;
    public TextField eachSmokeTime;
    public TextField movimentPain;
    public TextField goal;
    public TextField obs;
    private TitledPane userTitledPane;
    private PersonDaoImpl pdi;
    private int sizeUserNow;

    DatabaseInformation databaseInformation;
    WriteXMLFile write;

    String months[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"};


    public void createUserList(){
        pdi = new PersonDaoImpl();
        for (Person eachPerson : pdi.selectAll())
        {
            userTitledPane = new TitledPane();
            userTitledPane.textProperty().set(eachPerson.getFirstName() + " " + eachPerson.getLastName());
            userListReg.getItems().add(userTitledPane);
            System.out.println(eachPerson.getFirstName() + " " + eachPerson.getLastName());
        }
        sizeUserNow = pdi.selectAll().size();
    }

    public void updateUserList()
    {
        if (sizeUserNow == pdi.selectAll().size()){
            System.out.println("Igual Atual: " + sizeUserNow + " Anterior: " + pdi.selectAll().size());
        }
        else
        {
            System.out.println("Diferente");
            List <Person> persons = pdi.selectAll();
            for (int i = sizeUserNow; i < pdi.selectAll().size(); i++)
            {
                userTitledPane = new TitledPane();
                userTitledPane.textProperty().set(persons.get(i).getFirstName()+ " " + persons.get(i).getLastName());
                userListReg.getItems().add(userTitledPane);
                System.out.println(persons.get(i).getFirstName()+ " " + persons.get(i).getLastName());
            }
        }
        sizeUserNow = pdi.selectAll().size();
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
        createMainSchedule();
        updateDatabase();
        updateMain();
        createUserList();
        resetWeekButtons();
        resetYesAndNoAll();
    }

    //Aba Principal
    public void createMainSchedule()
    {
        int indexTimeTotal =  2 * (TIME_FINISH_MORNING - TIME_START_MORNING) + (TIME_FINISH_AFTERNOON - TIME_START_AFTERNOON);
        SimpleDateFormat ft = new SimpleDateFormat ("E");
        Date dNow = new Date();
        System.out.println(ft.format(dNow));
        System.out.println(ft.format(indexTimeTotal));
        generateTime();
        ScheduleDaoImpl sdi = new ScheduleDaoImpl();
        //List <Schedule> eachSchedule = sdi.selectAll();
        int indexTime;
        /*for (Schedule eachSchedule : sdi.selectAll())
        {
            indexTime = toInteger(eachSchedule.getFriday()) - 7;
            timeListView.getItems().set(indexTime, eachSchedule.getFriday() + "-" + eachSchedule.getName());
        }*/




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

    //Aba Banco de dados
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


    //Aba cadastro
    public void saveDataPerson(ActionEvent actionEvent) {
        Person person = new Person();
        PersonDaoImpl pdi = new PersonDaoImpl();

        Schedule schedule = new Schedule();
        ScheduleDaoImpl sdi = new ScheduleDaoImpl();

        person.setFirstName(name.getText());
        person.setLastName(lastName.getText());
        person.setTelephony(telephony.getText());
        person.setCellphone(cellphone.getText());
        person.setAddress(address.getText());
        person.setAddressNumber(addressNumber.getText());
        person.setProfession(profession.getText());
        person.setPilatesIndication(pilatesIndication.getText());
        person.setDateOfBirth(dateOfBirth.getText());
        person.setTimeLine(obs.getText());

        if(surgeryYesButton.isSelected())
        {
            person.setSurgery("Sim. " + eachSurgery.getText());
        }
        else
        {
            person.setSurgery("Não");
        }

        if(painYesButton.isSelected())
        {
            person.setPain("Sim. " + eachPain.getText() + ". Movimentos e restrições: " + movimentPain.getText());
        }
        else
        {
            person.setPain("Não");
        }

        if(medicineYesButton.isSelected())
        {
            person.setMedicines("Sim. " + eachMedicine.getText());
        }
        else
        {
            person.setMedicines("Não");
        }


        if(diseaseYesButton.isSelected())
        {
            person.setDisease("Sim. " + eachDisease.getText());
        }
        else
        {
            person.setMedicines("Não");
        }

        if(activitiesYesButton.isSelected())
        {
            person.setActivities("Sim. " + frequencyActivities.getText());
        }
        else
        {
            person.setActivities("Não");
        }

        if(smokeYesButton.isSelected())
        {
            person.setSmoke("Sim. " + eachSmokeTime.getText());
        }
        else
        {
            person.setSmoke("Não");
        }

        person.setGoal(goal.getText());

        schedule.setName(name.getText() + " " + lastName.getText());
        if (mondayClickedButton.isSelected()){
            schedule.setMonday(startMonday.getText() + ":" + endMonday.getText());
        }
        if (tuesdayClickedButton.isSelected()){
            schedule.setTuesday(startTuesday.getText() + ":" + endTuesday.getText());
        }
        if (wednesdayClickedButton.isSelected()){
            schedule.setWednesday(startWednesday.getText() + ":" + endWednesday.getText());
        }
        if (thursdayClickedButton.isSelected()){
            schedule.setThursday(startThursday.getText() + ":" + endThursday.getText());
        }
        if (fridayClickedButton.isSelected()){
            schedule.setFriday(startFriday.getText() + ":" + endFriday.getText());
        }






        schedule.toString();
        sdi.insert(schedule);
        pdi.insert(person);
        resetWeekButtons();
        resetYesAndNoAll();
    }

    public void userTabShow(Event event) {
        System.out.println("User tab");
        updateUserList();
    }

    public void mondayClicked(ActionEvent actionEvent) {
        if (mondayClickedButton.isSelected()){
            System.out.println("Monday Selected");
            startMonday.setEditable(true);
            startMonday.setOpacity(1);
            endMonday.setEditable(true);
            endMonday.setOpacity(1);
        }
        else
        {
            System.out.println("Monday not Selected");
            resetButtonWeek(startMonday, endMonday);
        }
    }

    public void tuesdayClicked(ActionEvent actionEvent) {
        if (tuesdayClickedButton.isSelected()){
            System.out.println("Tuesday Selected");
            startTuesday.setEditable(true);
            startTuesday.setOpacity(1);
            endTuesday.setEditable(true);
            endTuesday.setOpacity(1);
        }
        else
        {
            System.out.println("Tuesday not Selected");
            resetButtonWeek(startTuesday, endTuesday);
        }
    }

    public void wednesdayClicked(ActionEvent actionEvent) {
        if (wednesdayClickedButton.isSelected()){
            System.out.println("Wednesday Selected");
            startWednesday.setEditable(true);
            startWednesday.setOpacity(1);
            endWednesday.setEditable(true);
            endWednesday.setOpacity(1);
        }
        else
        {
            System.out.println("Wednesday not Selected");
            resetButtonWeek(startWednesday, endWednesday);
        }
    }

    public void thursdayClicked(ActionEvent actionEvent) {
        if (thursdayClickedButton.isSelected()){
            System.out.println("Thursday Selected");
            startThursday.setEditable(true);
            startThursday.setOpacity(1);
            endThursday.setEditable(true);
            endThursday.setOpacity(1);
        }
        else
        {
            System.out.println("Thursday not Selected");
            resetButtonWeek(startThursday, endThursday);
        }
    }

    public void fridayClicked(ActionEvent actionEvent) {
        if (fridayClickedButton.isSelected()){
            System.out.println("Friday Selected");
            startFriday.setEditable(true);
            startFriday.setOpacity(1);
            endFriday.setEditable(true);
            endFriday.setOpacity(1);
        }
        else
        {
            System.out.println("Friday not Selected");
            resetButtonWeek(startFriday, endFriday);
        }
    }

    public void resetButtonWeek(TextField startDayWeek, TextField endDayWeek){

        startDayWeek.setEditable(false);
        startDayWeek.setOpacity(0.5);
        startDayWeek.setText("");
        endDayWeek.setEditable(false);
        endDayWeek.setOpacity(0.5);
        endDayWeek.setText("");

    }

    public void resetWeekButtons(){
        resetButtonWeek(startMonday, endMonday);
        mondayClickedButton.setSelected(false);

        resetButtonWeek(startTuesday, endTuesday);
        tuesdayClickedButton.setSelected(false);

        resetButtonWeek(startWednesday, endWednesday);
        wednesdayClickedButton.setSelected(false);

        resetButtonWeek(startThursday, endThursday);
        thursdayClickedButton.setSelected(false);

        resetButtonWeek(startFriday, endFriday);
        fridayClickedButton.setSelected(false);
    }

    public void buttonYes(CheckBox checkBox, TextField textField){
        checkBox.setSelected(false);
        textField.setEditable(true);
        textField.setOpacity(1);
    }

    public void buttonNo(CheckBox checkBox, TextField textField){
        checkBox.setSelected(false);
        textField.setText("");
        textField.setEditable(false);
        textField.setOpacity(0.5);
    }

    public void resetYesAndNoAll(){
        resetYesAndNo(activitiesYesButton, activitiesNoButton, frequencyActivities);
        resetYesAndNo(diseaseYesButton, diseaseNoButton, eachDisease);
        resetYesAndNo(surgeryYesButton,surgeryNoButton,eachSurgery);
        resetYesAndNo(painYesButton,painNoButton,eachPain);
        movimentPain.setEditable(false);
        resetYesAndNo(medicineYesButton, medicineNoButton, eachMedicine);
        resetYesAndNo(smokeYesButton, smokeNoButton, eachSmokeTime);
    }

    public void resetYesAndNo(CheckBox yes, CheckBox no, TextField text){
        buttonNo(yes,text);
        no.setSelected(false);
    }

    public void activitiesYes(ActionEvent actionEvent) {
        if(activitiesYesButton.isSelected()){
            buttonYes(activitiesNoButton, frequencyActivities);
        }
    }

    public void activitiesNo(ActionEvent actionEvent) {
        if(activitiesNoButton.isSelected()){
            buttonNo(activitiesYesButton, frequencyActivities);
        }
    }

    public void diseaseNo(ActionEvent actionEvent) {
        if(diseaseNoButton.isSelected()){
            buttonNo(diseaseYesButton, eachDisease);
        }
    }

    public void diseaseYes(ActionEvent actionEvent) {
        if(diseaseYesButton.isSelected()){
            buttonYes(diseaseNoButton, eachDisease);
        }
    }

    public void surgeryNo(ActionEvent actionEvent) {
        if(surgeryNoButton.isSelected()){
            buttonNo(surgeryYesButton, eachSurgery);
        }
    }

    public void surgeryYes(ActionEvent actionEvent) {
        if(surgeryYesButton.isSelected()){
            buttonYes(surgeryNoButton, eachSurgery);
        }
    }

    public void painNo(ActionEvent actionEvent) {
        if(painNoButton.isSelected()){
            buttonNo(painYesButton, eachPain);
            movimentPain.setEditable(false);
        }
    }

    public void painYes(ActionEvent actionEvent) {
        if(painYesButton.isSelected()){
            buttonYes(painNoButton, eachPain);
            movimentPain.setEditable(true);
        }
    }

    public void medicineNo(ActionEvent actionEvent) {
        if(medicineNoButton.isSelected()){
            buttonNo(medicineYesButton, eachMedicine);
        }
    }

    public void medicineYes(ActionEvent actionEvent) {
        if(medicineYesButton.isSelected()){
            buttonYes(medicineNoButton, eachMedicine);
        }
    }

    public void smokeNo(ActionEvent actionEvent) {
        if(smokeNoButton.isSelected()){
            buttonNo(smokeYesButton, eachSmokeTime);
        }
    }

    public void smokeYes(ActionEvent actionEvent) {
        if(smokeYesButton.isSelected()){
            buttonYes(smokeNoButton, eachSmokeTime);
        }
    }

    public void clearAll(ActionEvent actionEvent) {
        resetYesAndNoAll();
        resetWeekButtons();
    }

    public void validateSchedule(ActionEvent actionEvent) {
        System.out.println("Validate was pressed");


        InformationTime informationTimeStartMonday = separeteTime(startMonday);
        InformationTime informationTimeEndMonday = separeteTime(endMonday);

        startMonday.setText(informationTimeStartMonday.getHour().get(0) + informationTimeStartMonday.getHour().get(1)
                + ":" + informationTimeStartMonday.getMin().get(0) + informationTimeStartMonday.getMin().get(1));

        endMonday.setText(informationTimeEndMonday.getHour().get(0) + informationTimeEndMonday.getHour().get(1)
                + ":" + informationTimeEndMonday.getMin().get(0) + informationTimeEndMonday.getMin().get(1));

        InformationTime informationTimeStartTuesday = separeteTime(startTuesday);
        InformationTime informationTimeEndTuesday = separeteTime(endTuesday);

        startTuesday.setText(informationTimeStartTuesday.getHour().get(0) + informationTimeStartTuesday.getHour().get(1)
                + ":" + informationTimeStartTuesday.getMin().get(0) + informationTimeStartTuesday.getMin().get(1));

        endTuesday.setText(informationTimeEndTuesday.getHour().get(0) + informationTimeEndTuesday.getHour().get(1)
                + ":" + informationTimeEndTuesday.getMin().get(0) + informationTimeEndTuesday.getMin().get(1));

        InformationTime informationTimeStartWednesday = separeteTime(startWednesday);
        InformationTime informationTimeEndWednesday = separeteTime(endWednesday);

        startWednesday.setText(informationTimeStartWednesday.getHour().get(0) + informationTimeStartWednesday.getHour().get(1)
                + ":" + informationTimeStartWednesday.getMin().get(0) + informationTimeStartWednesday.getMin().get(1));

        endWednesday.setText(informationTimeEndWednesday.getHour().get(0) + informationTimeEndWednesday.getHour().get(1)
                + ":" + informationTimeEndWednesday.getMin().get(0) + informationTimeEndWednesday.getMin().get(1));

        InformationTime informationTimeStartThursday = separeteTime(startThursday);
        InformationTime informationTimeEndThursday = separeteTime(endThursday);

        startThursday.setText(informationTimeStartThursday.getHour().get(0) + informationTimeStartThursday.getHour().get(1)
                + ":" + informationTimeStartThursday.getMin().get(0) + informationTimeStartThursday.getMin().get(1));

        endThursday.setText(informationTimeEndThursday.getHour().get(0) + informationTimeEndThursday.getHour().get(1)
                + ":" + informationTimeEndThursday.getMin().get(0) + informationTimeEndThursday.getMin().get(1));

        InformationTime informationTimeStartFriday = separeteTime(startFriday);
        InformationTime informationTimeEndFriday = separeteTime(endFriday);

        startFriday.setText(informationTimeStartFriday.getHour().get(0) + informationTimeStartFriday.getHour().get(1)
                + ":" + informationTimeStartFriday.getMin().get(0) + informationTimeStartFriday.getMin().get(1));

        endFriday.setText(informationTimeEndFriday.getHour().get(0) + informationTimeEndFriday.getHour().get(1)
                + ":" + informationTimeEndFriday.getMin().get(0) + informationTimeEndFriday.getMin().get(1));


    }

    public InformationTime separeteTime(TextField time){
        boolean flag = false;

        InformationTime timeInfo = new InformationTime();

        System.out.println(time.getText());
        for (int i = 0; i < time.getText().length(); i++)
        {
            if (!flag && !(String.valueOf(time.getText().charAt(i))).equals(":"))
            {
                timeInfo.getHour().add(String.valueOf(time.getText().charAt(i)));
            }

            if ((String.valueOf(time.getText().charAt(i))).equals(":") && !flag)
            {
                flag = true;
            }
            if (!(String.valueOf(time.getText().charAt(i))).equals(":") && flag)
            {

                timeInfo.getMin().add(String.valueOf(time.getText().charAt(i)));
            }
        }

        System.out.println(timeInfo.getHour() + ":" + timeInfo.getMin());

        timeInfo.setHour(validateTimeHour(sizeTimeDetection(timeInfo.getHour(), true)));

        timeInfo.setMin(validateTimeMin(sizeTimeDetection(timeInfo.getMin(), false)));

        System.out.println(timeInfo.getHour() + ":" + timeInfo.getMin());

        return timeInfo;
    }

    public List<String> sizeTimeDetection(List<String> time, boolean hourFlag){

        if (time.size() == 2)
        {
            System.out.println("OK - Size = 2");
        }
        else if (time.size() > 2)
        {
            System.out.println("not ok - removing the firsts numbers");
            for (int i = time.size() - 1; i > 1; i--)
            {
                time.remove(i);
            }
        }
        else
        {
            System.out.println("Size < 2");
            if (time.size() == 0){
                time.add("0");
                time.add("0");
            }
            else if (time.size() == 1)
            {
                if(hourFlag)
                {
                    String dec = time.get(0);
                    time.remove(0);
                    time.add("0");
                    time.add(dec);
                }
                else
                {
                    time.add("0");
                }
            }
        }
        return time;
    }

    public boolean validationTimeDetection(List<String> time, boolean hourFlag)
    {
        int timeNumber;
        if (hourFlag){
            timeNumber = Integer.parseInt(time.get(0)) * 10 + Integer.parseInt(time.get(1));
        }
        else
        {
            timeNumber = Integer.parseInt(time.get(0)) * 10 + Integer.parseInt(time.get(1));
        }

        System.out.println("Time: " + timeNumber);

        if ((timeNumber <= 24 && timeNumber >= 0) && (hourFlag == true))
        {
            System.out.println("Valided");
            return true;
        }
        else if ((timeNumber < 60 && timeNumber >= 0) && (hourFlag == false))
        {
            System.out.println("Valided");
            return true;
        } else
        {
            System.out.println("Not Valided");
            return false;
        }
    }

    public List<String> validateTimeHour(List<String> hour)
    {
        if (!(validationTimeDetection(hour, true))){
            if (Integer.parseInt(hour.get(0)) * 10 + Integer.parseInt(hour.get(1)) > 12)
            {
                hour.remove(1);
                hour.remove(0);
                hour.add("2");
                hour.add("3");
            }
            else
            {
                hour.remove(1);
                hour.remove(0);
                hour.add("0");
                hour.add("0");
            }

        }
        return hour;
    }

    public List<String> validateTimeMin(List<String> min)
    {
        if(!(validationTimeDetection(min, false)))
        {
            if (Integer.parseInt(min.get(0)) * 10 + Integer.parseInt(min.get(1)) > 12)
            {
                min.remove(1);
                min.remove(0);
                min.add("5");
                min.add("9");
            }
            else
            {
                min.remove(1);
                min.remove(0);
                min.add("0");
                min.add("0");
            }
        }
        return min;
    }
}
