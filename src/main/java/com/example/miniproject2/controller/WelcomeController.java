package com.example.miniproject2.controller;

import com.example.miniproject2.view.GameStage;
import com.example.miniproject2.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button closeButton;

    @FXML
    private ImageView titleImage;

    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResourceAsStream("/com/example/miniproject2/images/title.png"));

        titleImage.setImage(image);
    }

    @FXML
    void setOnCloseRequest(ActionEvent event) {
        WelcomeStage.deletedInstance();
    }
    @FXML
    void onStartPlay(ActionEvent event) throws IOException {
        WelcomeStage.closeInstance();
        GameStage.getInstance();
    }


    @FXML
    void OnShowInstruccion(ActionEvent event) {
        Stage instructionStage = new Stage();
        instructionStage.setTitle("Instrucciones");

        // Configure the instruction stage as a modal window
        instructionStage.initModality(Modality.WINDOW_MODAL);
        instructionStage.initStyle(StageStyle.UNDECORATED);


        // Create title label for instructions
        Label titleLabel = new Label("INSTRUCCIONES");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text

        // Create labels for each instruction
        Label instructionLabel1 = new Label("1. Adivina la palabra secreta ingresando una letra a la vez.");
        Label instructionLabel2 = new Label("2. Usa las pistas, pero solo tres veces.");
        Label instructionLabel3 = new Label("3. Cada error eclipsa el sol un poco más.");
        Label instructionLabel4 = new Label("4. Tienes cinco intentos antes de que el sol se eclipse por completo.");
        Label instructionLabel5 = new Label("5. Adivina todas las letras antes del eclipse para ganar.");
        Label instructionLabel6 = new Label("6. Si no adivinas antes del eclipse total, pierdes.");

        // Set style for instruction labels
        instructionLabel1.setStyle("-fx-text-fill: white;");
        instructionLabel2.setStyle("-fx-text-fill: white;");
        instructionLabel3.setStyle("-fx-text-fill: white;");
        instructionLabel4.setStyle("-fx-text-fill: white;");
        instructionLabel5.setStyle("-fx-text-fill: white;");
        instructionLabel6.setStyle("-fx-text-fill: white;");

        // Create close button for the instruction stage
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> instructionStage.close());
        closeButton.setStyle(
                "-fx-background-color: red;" +         // Red background
                        "-fx-text-fill: white;" +              // White text
                        "-fx-font-weight: bold;" +             // Bold font
                        "-fx-font-size: 14px;" +               // Font size
                        "-fx-border-color: white;" +           // White border
                        "-fx-border-width: 2px;" +             // Border thickness
                        "-fx-border-radius: 10px;" +           // Rounded borders
                        "-fx-background-radius: 10px;" +       // Rounded background
                        "-fx-padding: 5px 10px;"               // Internal padding
        );

        // Create layout for instructions and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, instructionLabel1, instructionLabel2, instructionLabel3, instructionLabel4, instructionLabel5, instructionLabel6, closeButton);
        layout.setStyle("-fx-background-color: #FF8A00;"); // Dark background to highlight text
        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the instruction stage
        Scene scene = new Scene(layout, 400, 300);
        instructionStage.setScene(scene);

        // Show the instruction stage
        instructionStage.showAndWait();
    }

    @FXML
    void OnShowCredits(ActionEvent event) {
        Stage creditsStage = new Stage();
        creditsStage.setTitle("Créditos");

        creditsStage.initModality(Modality.WINDOW_MODAL);
        creditsStage.initStyle(StageStyle.UNDECORATED);

        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        creditsStage.initOwner(primaryStage);

        // Create title label for credits
        Label titleLabel = new Label("CRÉDITOS");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text

        // Create labels for credits information
        Label creditLabel1 = new Label("Desarrollado por: Juan David Rincón");
        Label creditLabel2 = new Label("Código: 202342032");
        Label creditLabel3 = new Label("Correo: juan.rincon.lopez@correounivalle.edu.co");
        Label creditLabel4 = new Label("Materia: Programación Orientada a Eventos - 2024-2");

        // Set style for credit labels
        creditLabel1.setStyle("-fx-text-fill: white;");
        creditLabel2.setStyle("-fx-text-fill: white;");
        creditLabel3.setStyle("-fx-text-fill: white;");
        creditLabel4.setStyle("-fx-text-fill: white;");

        // Create close button for the credits stage
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> creditsStage.close());
        closeButton.setStyle(
                "-fx-background-color: red;" +         // Red background
                        "-fx-text-fill: white;" +              // White text
                        "-fx-font-weight: bold;" +             // Bold font
                        "-fx-font-size: 14px;" +               // Font size
                        "-fx-border-color: white;" +           // White border
                        "-fx-border-width: 2px;" +             // Border thickness
                        "-fx-border-radius: 10px;" +           // Rounded borders
                        "-fx-background-radius: 10px;" +       // Rounded background
                        "-fx-padding: 5px 10px;"               // Internal padding
        );

        // Create layout for credits and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, creditLabel1, creditLabel2, creditLabel3, creditLabel4, closeButton);
        layout.setStyle("-fx-background-color: #FF8A00;"); // Dark background to highlight text
        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the credits stage
        Scene scene = new Scene(layout, 350, 250);
        creditsStage.setScene(scene);

        // Show the credits stage
        creditsStage.showAndWait();

    }
}
