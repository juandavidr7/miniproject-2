package com.example.miniproject2.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeController {

    @FXML
    private ImageView titleImage;

    // Método que se ejecuta después de cargar la interfaz
    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResourceAsStream("/com/example/miniproject2/images/title.png"));

        titleImage.setImage(image);
    }
}
