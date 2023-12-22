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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SignController {

    @FXML
    private TextField Firstname;
    @FXML
    private TextField Lastname;
    @FXML
    private TextField logon;
    @FXML
    private PasswordField password;
    @FXML
    private TextField emails;
    @FXML
    private Button signup;

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        String first = Firstname.getText();
        String last = Lastname.getText();
        String emaill = emails.getText();
        String loginss = logon.getText();
        String pass = password.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        try {
            if (event.getSource() == signup) {


                if (Firstname.getText().isEmpty() && Lastname.getText().isEmpty() && logon.getText().isEmpty() && emails.getText().isEmpty() && password.getText().isEmpty()) {
                    alert.setContentText("Fill in the fields!");
                    alert.showAndWait();
                } else if (!Firstname.getText().isEmpty() && !Lastname.getText().isEmpty() && logon.getText().isEmpty() && !password.getText().isEmpty() && emails.getText().isEmpty()) {
                    alert.setContentText("Email are empty!");
                    alert.showAndWait();
                } else if (!Firstname.getText().isEmpty() && !Lastname.getText().isEmpty() && logon.getText().isEmpty() && password.getText().isEmpty() && !emails.getText().isEmpty()) {
                    alert.setContentText("Password are empty!");
                    alert.showAndWait();
                } else if (Firstname.getText().isEmpty() && !Lastname.getText().isEmpty() && !logon.getText().isEmpty() && !password.getText().isEmpty() && !emails.getText().isEmpty()) {
                    alert.setContentText("First Name are empty!");
                    alert.showAndWait();
                } else if (!Firstname.getText().isEmpty() && Lastname.getText().isEmpty() && !logon.getText().isEmpty() && !password.getText().isEmpty() && !emails.getText().isEmpty()) {
                    alert.setContentText("Last Name are empty!");
                    alert.showAndWait();
                } else if (!Firstname.getText().isEmpty() && !Lastname.getText().isEmpty() && logon.getText().isEmpty() && !password.getText().isEmpty() && !emails.getText().isEmpty()) {
                    alert.setContentText("Login are empty!");
                    alert.showAndWait();
                } else {
                    addData(first, last, loginss, pass, emaill);


                    stage = (Stage) signup.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();


                    System.out.println("SignUp Successful");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addData(String first, String last, String login, String password, String email) throws IOException {

        try (RandomAccessFile file = new RandomAccessFile("library.user.txt", "rw")) {

            file.seek(file.length());
            file.writeBytes("First Name:" + first + "\n");
            file.writeBytes("Last Name:" + last + "\n");
            file.writeBytes("Login: " + login + "\n");
            file.writeBytes("Email: " + email + "\n");
            file.writeBytes("Password:" + password + "\n");

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
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
