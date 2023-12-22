package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class loginController {


    @FXML
    private Button log;
    @FXML
    private TextField logins;
    @FXML
    private PasswordField passwords;

    @FXML
    public void logout(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;


        try {

            if (event.getSource() == log) {
                String filename = "library.user.txt";
                Map<String, String> users = new HashMap<>();

                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    String line;
                    String username = null;
                    String passwords = null;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Login:")) {
                            username = line.substring("Login:".length()).trim();
                        } else if (line.startsWith("Password:")) {
                            passwords = line.substring("Password:".length()).trim();
                            if (username != null && passwords != null) {
                                users.put(username, passwords);
                                username = null;
                                passwords = null;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении файла: " + e.getMessage());
                    return;
                }

                String inputUsername = logins.getText();
                String inputPassword = passwords.getText();

                String passwordForUsername = users.get(inputUsername);
                if (passwordForUsername != null && passwordForUsername.equals(inputPassword)) {

                    System.out.println("Successful");
                    stage = (Stage) log.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("library.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password!");
                    alert.showAndWait();
                }

            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button back;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        try {
            if (event.getSource() == back) {
                stage = (Stage) back.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
