package com.example.miniproject2.controller;

import com.example.miniproject2.view.GameStage;
import com.example.miniproject2.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private ImageView titleImage;
    @FXML
    private Button playButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button instructionsButton;
    @FXML
    private Button quitButton;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/miniproject2/images/title.png"));
        titleImage.setImage(image);
    }

    public void handlePlay(ActionEvent event) throws IOException {
        WelcomeStage.closeInstance();
        GameStage.getInstance();
    }

    public void handleSettings(){}

    public void handleInstructions(){}

    public void handleQuit(ActionEvent event){
        WelcomeStage.deletedInstance();
    }
}
