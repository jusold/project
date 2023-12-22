package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class welcomController {

    @FXML
    private Button logout;
    @FXML
    private Button signUp;


    @FXML
    public void logAct(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        try {
            if (event.getSource() == logout) {
                stage = (Stage) logout.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sign(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        try {
            if (event.getSource() == signUp) {
                stage = (Stage) signUp.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("sign.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
