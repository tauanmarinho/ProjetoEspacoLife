package com.gui;

import com.dao.PersonDao;
import com.daoImplementation.PersonDaoImpl;
import com.entities.Person;
import com.xmlFile.DatabaseInformation;
import com.xmlFile.WriteXMLFile;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPersonFrame {

    public AddPersonFrame() {
    }

    public static void start() throws Exception
    {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        Stage databaseStage = new Stage();
        databaseStage.setTitle("Adicionar pacientes");
        WriteXMLFile write = new WriteXMLFile();
        DatabaseInformation databaseInformation = new DatabaseInformation();
        write.parseXml(databaseInformation);

        TextField textField = new TextField("Nome");
        TextField textField2 = new TextField("Sobrenome");
        TextField textField3 = new TextField("Históricos médicos");
        TextField textField4 = new TextField("Dores");
        TextField textField5 = new TextField("Cirurgias");
        TextField textField6 = new TextField("Anotações");
        Button button1 = new Button("Salvar");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20 , 20, 20));

        layout.getChildren().addAll(textField, textField2, textField3, textField4, textField5, textField6, button1);
        Scene scene = new Scene(layout, 400, 300);

        button1.setOnAction(event -> {
            Person person = new Person();
            person.setFirstName(textField.getText());
            person.setLastName(textField2.getText());
            person.setMedicines(textField3.getText());
            person.setPain(textField4.getText());
            person.setSurgery(textField5.getText());
            person.setTimeLine(textField6.getText());

            PersonDaoImpl pdi = new PersonDaoImpl();
            pdi.insert(person);

            System.out.println("Button OK was pressed");
            databaseStage.close();
            //AlertFrame.DisplayAlert("Paciente Salvo");
        });

        databaseStage.setScene(scene);
        databaseStage.show();
    }
}
