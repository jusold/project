package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    protected void onStartButtonClick() throws IOException {
        LibraryController.changeScene();
    }
}