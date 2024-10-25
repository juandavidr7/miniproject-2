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

/**
 * The {@code GameStage} class represents the main game window for the Sudoku application.
 * It extends {@code Stage} and manages the visual aspects and lifecycle of the game interface.
 */
public class GameStage extends Stage {

    private GameController gameController;

    /**
     * Constructs a new {@code GameStage} instance, initializes the user interface
     * by loading the FXML layout, and sets the stage properties.
     *
     * @throws IOException If an error occurs during the loading of the FXML file.
     */
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

    /**
     * Holder class for implementing the Singleton pattern for {@code GameStage}.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    /**
     * Retrieves the singleton instance of {@code GameStage}. If it doesn't exist,
     * a new instance will be created.
     *
     * @return The singleton instance of {@code GameStage}.
     * @throws IOException If an error occurs during the loading of the FXML file.
     */
    public static GameStage getInstance() throws IOException {
        GameStage.GameStageHolder.INSTANCE =
                GameStage.GameStageHolder.INSTANCE != null ? GameStage.GameStageHolder.INSTANCE : new GameStage();
        return GameStage.GameStageHolder.INSTANCE;
    }

    /**
     * Prompts the user for confirmation before closing the game stage.
     * If confirmed, the instance will be closed and set to null.
     */
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

    /**
     * Closes the current instance of {@code GameStage} and sets it to null.
     */
    public static void closeInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }

    /**
     * Retrieves the {@code GameController} associated with this game stage.
     *
     * @return The {@code GameController} instance.
     */
    public GameController getGameController() {
        return gameController;
    }
}
