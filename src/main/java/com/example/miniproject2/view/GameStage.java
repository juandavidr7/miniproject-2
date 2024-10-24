package com.example.miniproject2.view;

import com.example.miniproject2.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class GameStage extends Stage {

    private GameController gameController;

    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproject2/fxml/game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/miniproject2/styles/styleGameStage.css").toExternalForm());
        setScene(scene);
        setTitle("SUDOKU");
        setWidth(920);
        setHeight(700);
        setResizable(false);
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/miniproject2/images/favicon.png"))));

        setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            deletedInstance();
        });
        show();
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    public static GameStage getInstance() throws IOException {
        GameStage.GameStageHolder.INSTANCE =
                GameStage.GameStageHolder.INSTANCE != null ? GameStage.GameStageHolder.INSTANCE : new GameStage();
        return GameStage.GameStageHolder.INSTANCE;
    }

    public static void deletedInstance() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("¿Seguro que desea cerrar la ventana?");
        alert.setContentText("Perderá el progreso actual.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            GameStage.GameStageHolder.INSTANCE.close();
            GameStage.GameStageHolder.INSTANCE = null;
        }
    }

    public static void closeInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }

    public GameController getGameController() {
        return gameController;
    }
}


