package com.gui;

import com.xmlFile.DatabaseInformation;
import com.xmlFile.WriteXMLFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import static javafx.application.Application.launch;

public class DatabaseFrame extends Application {

    private Button button1;

    public DatabaseFrame() {

    }

    public static void showDatabaseFrame(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage databaseStage) throws Exception
    {
        databaseStage.setTitle("Database Information");
        WriteXMLFile write = new WriteXMLFile();
        DatabaseInformation databaseInformation = new DatabaseInformation();
        write.parseXml(databaseInformation);

        TextField textField = new TextField(databaseInformation.getUser());
        TextField textField2 = new TextField(databaseInformation.getPassword());
        TextField textField3 = new TextField(databaseInformation.getUrl());
        TextField textField4 = new TextField(databaseInformation.getPort());
        TextField textField5 = new TextField(databaseInformation.getDatabase());
        button1 = new Button("Ok");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20 , 20, 20));

        layout.getChildren().addAll(textField, textField2, textField3, textField4, textField5, button1);
        Scene scene = new Scene(layout, 400, 300);

        button1.setOnAction(event -> {
            databaseInformation.setUser(textField.getText());
            databaseInformation.setPassword(textField2.getText());
            databaseInformation.setUrl(textField3.getText());
            databaseInformation.setPort(textField4.getText());
            databaseInformation.setDatabase(textField5.getText());
            System.out.println("Button OK was pressed");
            write.createXml(databaseInformation);
        });

        databaseStage.setScene(scene);
        databaseStage.show();


    }
}
