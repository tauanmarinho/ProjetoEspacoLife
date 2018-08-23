package com.gui;

import com.daoImplementation.PersonDaoImpl;
import com.entities.Person;
import com.sun.javafx.scene.control.skin.LabeledImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertFrame {

    Stage alertStage;

    public AlertFrame(){}

    public void DisplayAlert(String Message) throws IOException {
        alertStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AlertFrame.fxml"));
        alertStage.setScene(new Scene(root, 247, 133));
        alertStage.setTitle("Alerta");

        /*Label label = new Label(Message);
        Button button1 = new Button("Ok");

        StackPane layout = new StackPane();
        layout.setPadding(new Insets(20, 20 , 20, 20));

        layout.getChildren().addAll(label, button1);
        Scene scene = new Scene(layout, 200, 150);

        button1.setOnAction(event -> {
            System.out.println("Button OK was pressed");
            alertStage.close();
        });


        alertStage.setScene(scene);*/
        alertStage.show();

    }

    public void okButton(ActionEvent actionEvent) {
        System.out.println("Button OK was pressed");
    }
}
