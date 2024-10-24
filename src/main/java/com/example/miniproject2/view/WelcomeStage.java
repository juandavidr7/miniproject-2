package com.example.miniproject2.view;

import com.example.miniproject2.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
    private WelcomeController welcomeController;

    public WelcomeStage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproject2/fxml/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/miniproject2/styles/styleWelcomeStage.css").toExternalForm());
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/miniproject2/images/favicon.png"))));
        setScene(scene);
        setTitle("SUDOKU");
        setResizable(false);
        setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            deletedInstance();
        });
        show();
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    public static WelcomeStage getInstance() throws IOException {
        WelcomeStage.WelcomeStageHolder.INSTANCE =
                WelcomeStage.WelcomeStageHolder.INSTANCE != null ? WelcomeStage.WelcomeStageHolder.INSTANCE : new WelcomeStage();
        return WelcomeStage.WelcomeStageHolder.INSTANCE;
    }

    public static void deletedInstance() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("¿Seguro que desea cerrar la ventana?");
        alert.setContentText("Perderá el progreso actual.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            WelcomeStageHolder.INSTANCE.close();
            WelcomeStageHolder.INSTANCE = null;
        }
    }

    public static void closeInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }

    public WelcomeController getWelcomeController() {
        return welcomeController;
    }
}
