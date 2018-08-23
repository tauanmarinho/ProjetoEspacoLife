package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InitGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EspacoLifeFrame.fxml"));
        primaryStage.setTitle("Espaço Life");
        primaryStage.setScene(new Scene(root, 1235, 800));
        primaryStage.show();
    }
}
