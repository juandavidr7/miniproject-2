package com.example.miniproject2.controller;

import com.example.miniproject2.model.Board;
import com.example.miniproject2.model.Game;
import com.example.miniproject2.view.GameStage;
import com.example.miniproject2.view.WelcomeStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Controller class for managing the game logic and UI interactions.
 * Implements ITimer and IFields interfaces to handle timer functionality
 * and field-related operations respectively.
 */
public class GameController implements ITimer, IFields {

    @FXML
    private GridPane boardGrid; // Main grid for displaying the Sudoku board
    @FXML
    private Label timerLabel; // Label for displaying the timer
    @FXML
    private Button button1, button2, button3, button4, button5, button6; // Number buttons
    @FXML
    private Button buttonStartTime; // Button to start the timer
    @FXML
    private VBox messagesBox; // VBox for displaying messages to the user
    @FXML
    private Label helpsLabel; // Label for displaying remaining helps
    @FXML
    private ScrollPane scrollPane; // ScrollPane for the messages box
    @FXML
    private GridPane mainGridPane; // Main grid for layout management
    @FXML
    private GridPane boardGrid1, boardGrid2, boardGrid3, boardGrid4, boardGrid5, boardGrid6; // Sub-grids for Sudoku blocks
    @FXML
    private Button helpButton; // Button to request help
    @FXML
    private Button restartButton; // Button to restart the game

    private Label winLabel; // Label to display win messages

    private GridPane[][] gridPanes; // Array of grid panes for managing the board layout
    private final String[][] messages = { // Messages for various game states
            {"¡Excelente! Esa es la pieza que faltaba.", "¡Así se hace! Cada vez más cerca.", "¡Correcto! Continúa con esa lógica.", "¡Bien visto! Ese número encaja perfecto.", "¡Buen movimiento! Vas por buen camino.", "¡Acertaste! Sientes cómo todo encaja, ¿verdad?", "¡Perfecto! Tu razonamiento es impecable.", "¡Correcto! Sigues sumando puntos a tu favor.", "¡Buen ojo! Eso es justo lo que necesitaba.", "¡Impecable! Sigue así, estás en racha.", "El numero ingresado es correcto :D", "Genial, parece que vas por buen camino...", "Excelente elección"},
            {"¡Vaya! Ese número no encaja, pero sigue intentando.", "No es el número correcto, prueba otra opción.", "Inténtalo de nuevo, ese número no es correcto.", "Ese número no va ahí, ¡pero no te desanimes!", "Ups, no es el número adecuado, ¡sigue buscando!", "Un pequeño error, ¡lo resolverás en un momento!", "Ese número no es el correcto, sigue concentrado.", "¡Casi! Pero ese no es el número correcto.", "No es el número correcto, pero estás cerca.", "Esa no es la solución, pero lo lograrás en breve.", "No, no, no, ese número no pertenece aquí", "Lo lamento, el número es incorrecto", "Creo que puedes hacerlo mejor"},
            {"Aquí tienes una pista, úsala sabiamente.", "¡Ayuda activada! Esta podría ser la clave.", "Pista obtenida. ¡Adelante, resuelve el enigma!", "Esta ayuda te acerca un poco más a la victoria.", "Aquí tienes un pequeño empujón, sigue adelante.", "¡Pista revelada! Aprovecha este momento.", "Una ayuda más... úsala con cuidado.", "Te damos una pista, ¡todo depende de ti ahora!", "Esta pista podría hacer la diferencia.", "¡Una ayuda más para continuar tu racha ganadora!", "Aquí tienes, una ayudita...", "Y que tal si haces esto...", "Sabía que no podías hacerlo solo"},
            {"Las ayudas se agotaron, es hora de confiar en tu habilidad.", "Ya no quedan más pistas, el resto es tuyo.", "Sin más ayudas, ¡pero puedes lograrlo solo!", "No más ayudas disponibles, ahora tu lógica es tu mejor aliada.", "Las pistas se acabaron, pero la solución está en tus manos.", "Ayudas agotadas, ¡confía en ti mismo!", "Se acabaron las pistas, es el momento de resolverlo.", "Las ayudas llegaron a su fin, ahora es todo tuyo.", "Sin más ayudas... pero ya tienes lo que necesitas.", "No más pistas disponibles, ¡pero confío en que lo lograrás!", "Lo lamento amigo, no te quedan más ayudas", "Ahora tendrás que continuar sin mi", "¿Más ayudas? Pero si ya te he dado tres!!"},
            {"Espectacular!! Has ganado", "Felicidades, has vencido el juego", "Lo hiciste increible, ¿qué tal otra ronda?"}
    };
    private int secondsPassed = 0; // Timer counter
    private Timeline timeline; // Timeline for managing the timer
    private TextField activeTextField = null; // Currently active TextField for user input
    private Game game = new Game(); // Game model instance
    private ArrayList<ArrayList<TextField>> textFields = new ArrayList<>(); // List to store TextFields
    private int helpsLeft = 3; // Remaining helps for the player

    /**
     * Initializes the controller. Sets up the game board and starts the timer.
     */
    @FXML
    public void initialize() {
        for (int i = 0; i < 6; i++) {
            textFields.add(new ArrayList<>());
        }
        gridPanes = new GridPane[][]{
                {boardGrid1, boardGrid2},
                {boardGrid3, boardGrid4},
                {boardGrid5, boardGrid6}
        };
        scrollPane.setFitToWidth(true); // Ensures the scroll pane fits the width
        messagesBox.setFillWidth(true); // Ensures the messages box fills the width
        createTextFields(); // Creates the text fields for the Sudoku grid
        startTimer(); // Starts the timer
        setButtonEvents(); // Sets up button click events
        winLabel = new Label(); // Initializes the win label
    }

    /**
     * Starts the game timer.
     */
    public void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsPassed++; // Increments the timer
            updateLabel(); // Updates the displayed timer
        }));

        timeline.setCycleCount(Timeline.INDEFINITE); // Sets the timeline to run indefinitely
        timeline.play(); // Starts the timeline
    }

    /**
     * Updates the timer label with the elapsed time.
     */
    public void updateLabel() {
        timerLabel.setText("Tiempo: " + secondsPassed + "s");
    }

    /**
     * Stops the game timer.
     */
    public void stopTimer() {
        timeline.stop(); // Stops the timeline
    }

    /**
     * Resets the game timer to zero.
     */
    public void resetTimer() {
        stopTimer(); // Stops the timer
        secondsPassed = 0; // Resets the counter
        updateLabel(); // Updates the label
    }

    /**
     * Creates and initializes the text fields for the Sudoku grid.
     */
    @Override
    public void createTextFields() {
        boardGrid.getChildren().clear(); // Clears the existing grid
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 2; blockCol++) {
                for (int row = 0; row < 2; row++) {
                    for (int col = 0; col < 3; col++) {
                        int i = blockRow * 2 + row; // Calculate the correct row in the main grid
                        int j = blockCol * 3 + col; // Calculate the correct column in the main grid

                        TextField txt = new TextField(); // Create a new TextField
                        txt.setPrefWidth(100);
                        txt.setPrefHeight(90);
                        txt.setStyle("-fx-font-size: 25px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-color: transparent;" +
                                "-fx-border-color: white;" +
                                "-fx-border-width: 1px;" +
                                "-fx-text-alignment: center;");

                        Integer individualValue = game.getActualBoard().get(i).get(j); // Get the value from the game model
                        if (individualValue != 0) { // If the value is not empty
                            txt.setText(individualValue.toString());
                            txt.setDisable(true); // Disable editing for predefined values
                            txt.setStyle(txt.getStyle() +
                                    "-fx-text-fill: darkgreen;" +
                                    "-fx-background-color: lightgreen;" +
                                    "-fx-border-color: green;" +
                                    "-fx-border-width: 2px;" +
                                    "-fx-border-radius: 5px;");
                        }

                        gridPanes[blockRow][blockCol].add(txt, col, row); // Add the TextField to the grid pane
                        textFields.get(i).add(txt); // Add to the list of TextFields
                        txt.setOnMouseClicked(event -> activeTextField = txt); // Set the active TextField on mouse click
                        onKeyTxtTyped(txt, i, j); // Set the key typed event for the TextField
                    }
                }

                // Style the grid pane
                gridPanes[blockRow][blockCol].setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-border-radius: 2px;");
                boardGrid.add(gridPanes[blockRow][blockCol], blockCol, blockRow); // Add the grid pane to the main grid
            }
        }
    }

    /**
     * Adds a listener to the specified TextField to handle input validation and styling.
     *
     * @param txt the TextField to monitor for input changes
     * @param row the row index of the TextField in the grid
     * @param col the column index of the TextField in the grid
     */
    private void onKeyTxtTyped(TextField txt, int row, int col) {
        // Listener that triggers when the text property of the TextField changes
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.equals(newValue, "")){
                validateAndColorFields(txt, row, col, 0, false);
                textFields.get(row).get(col).setStyle("-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-alignment: center;");
            }
        });

        // Event handler for when a key is typed in the TextField
        txt.setOnKeyTyped(event -> {
            String input = txt.getText();
            if (input.length() == 1) {
                char c = input.charAt(0);
                if (Character.isDigit(c)) {
                    int num = Character.getNumericValue(c);
                    if (num >= 1 && num <= 6) {
                        validateAndColorFields(txt, row, col, num, false);
                    } else {
                        txt.clear();
                    }
                } else {
                    txt.clear();
                }
            } else {
                txt.clear();
                txt.positionCaret(1);
            }
        });
    }

    /**
     * Sets action events for number buttons, linking them to insert respective numbers into the active TextField.
     */
    private void setButtonEvents() {
        button1.setOnAction(event -> insertNumberIntoActiveTextField(1));
        button2.setOnAction(event -> insertNumberIntoActiveTextField(2));
        button3.setOnAction(event -> insertNumberIntoActiveTextField(3));
        button4.setOnAction(event -> insertNumberIntoActiveTextField(4));
        button5.setOnAction(event -> insertNumberIntoActiveTextField(5));
        button6.setOnAction(event -> insertNumberIntoActiveTextField(6));
    }

    /**
     * Inserts a number into the currently active TextField and validates the input.
     *
     * @param number the number to insert into the active TextField
     */
    @Override
    public void insertNumberIntoActiveTextField(int number) {
        if (activeTextField != null) {
            int row = 0, col = 0;

            // Find the row and column of the active TextField
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 6; j++){
                    if (activeTextField == textFields.get(i).get(j)){
                        row = i;
                        col = j;
                        break;
                    }
                }
            }
            activeTextField.setText(String.valueOf(number));
            validateAndColorFields(activeTextField, row, col, number, false);
        }
    }

    /**
     * Validates the input in the specified TextField and updates its appearance based on correctness.
     *
     * @param txt the TextField to validate
     * @param row the row index of the TextField in the grid
     * @param col the column index of the TextField in the grid
     * @param number the number entered into the TextField
     * @param isClue indicates if the number is a clue (true) or a player input (false)
     */
    @Override
    public void validateAndColorFields(TextField txt, int row, int col, int number, boolean isClue) {
        game.getActualBoard().get(row).set(col, number);

        if (number != 0 && !isClue) {
            if (Board.verifyNum(game.getActualBoard(), number, row, col)) {
                showMessage("correct");
            } else {
                showMessage("incorrect");
            }
        }

        // Update the styles of all TextFields based on their current values
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int temp = game.getActualBoard().get(i).get(j);
                if (temp == 0 || textFields.get(i).get(j).isDisabled()) {
                    continue;
                }
                if (Board.verifyNum(game.getActualBoard(), temp, i, j)) {
                    textFields.get(i).get(j).setStyle("-fx-font-size: 25px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: darkgreen;" +
                            "-fx-background-color: lightgreen;" +
                            "-fx-border-color: green;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-text-alignment: center;");
                } else {
                    textFields.get(i).get(j).setStyle("-fx-font-size: 25px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: darkred;" +
                            "-fx-background-color: lightcoral;" +
                            "-fx-border-color: red;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-text-alignment: center;");
                }
            }
        }

        if (game.isSolved()) {
            handleWinEvent();
        }
    }

    /**
     * Handles the help button action, providing a hint if available.
     *
     * @param event the ActionEvent triggered by the help button
     */
    public void handleHelp(ActionEvent event) {
        if (helpsLeft > 0) {
            Random rand = new Random();
            int i, j, num, actualNum;
            do {
                i = rand.nextInt(6);
                j = rand.nextInt(6);
                actualNum = game.getActualBoard().get(i).get(j);
                num = game.getSolvedBoard().get(i).get(j);
            } while (actualNum == num);

            TextField txt = textFields.get(i).get(j);
            txt.setText(Integer.toString(num));
            helpsLeft--;
            helpsLabel.setText("Ayudas restantes: " + helpsLeft);
            showMessage("help");
            validateAndColorFields(txt, i, j, num, true);
        } else {
            showMessage("noHelpsLeft");
        }
    }

    /**
     * Handles the exit action, closing the game stage.
     *
     * @param event the ActionEvent triggered by the exit button
     * @throws IOException if an I/O error occurs during exit
     */
    public void handleExit(ActionEvent event) throws IOException {
        GameStage.deletedInstance();
    }

    /**
     * Handles the restart action, resetting the game state and UI.
     *
     * @param event the ActionEvent triggered by the restart button
     */
    public void handleRestart(ActionEvent event) {
        stopTimer();
        game = new Game();
        messagesBox.getChildren().clear();
        helpsLabel.setText("Ayudas restantes: 3");
        helpsLeft = 3;
        secondsPassed = 0;
        activeTextField = null;
        textFields = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            textFields.add(new ArrayList<>());
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                gridPanes[i][j].getChildren().clear();
            }
        }

        resetTimer();
        setButtonEvents();
        winLabel.setText("");
        initialize();
        helpButton.setDisable(false);
    }

    /**
     * Handles the action of returning to the main menu.
     *
     * @param event the ActionEvent triggered by the back to menu button
     * @throws IOException if an I/O error occurs during the transition
     */
    public void handleBackToMenu(ActionEvent event) throws IOException {
        GameStage.closeInstance();
        WelcomeStage.getInstance();
    }

    /**
     * Handles the win event in the game.
     * This method displays a victory message, disables all input fields,
     * stops the timer, and updates the UI to indicate the win status.
     */
    public void handleWinEvent() {
        showMessage("win");

        // Remove the win label node from the main grid pane if it exists
        mainGridPane.getChildren().removeIf(node -> {
            Integer nodeColumn = GridPane.getColumnIndex(node);
            Integer nodeRow = GridPane.getRowIndex(node);

            nodeColumn = (nodeColumn == null) ? -1 : nodeColumn;
            nodeRow = (nodeRow == null) ? -1 : nodeRow;

            // Return true if the node is located in column 1 and row 2
            return nodeColumn == 1 && nodeRow == 2;
        });

        // Disable all text fields on the grid
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                textFields.get(i).get(j).setDisable(true);
            }
        }

        // Stop the timer
        stopTimer();

        // Set up the win label text and style
        winLabel.setText("¡¡¡HAS GANADO!!!");
        winLabel.setStyle("-fx-background-color: transparent;" +
                "-fx-font-size: 50px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Arial', sans-serif;" +
                "-fx-font-weight: bold;" +
                "-fx-alignment: center;" +
                "-fx-text-alignment: center;" +
                "-fx-border-color: transparent;" +
                "-fx-effect: dropshadow( gaussian , rgba(0, 255, 0, 0.75) , 10, 0.5 , 0 , 0 );");

        // Add the win label to the main grid pane at specified position
        mainGridPane.add(winLabel, 1, 2);

        // Disable the help and restart buttons
        helpButton.setDisable(true);
        restartButton.setDisable(true);
    }

    /**
     * Displays a message to the user based on the provided message type.
     * The message will be randomly selected from predefined sets based on the type.
     *
     * @param type The type of message to display. It can be one of the following:
     *             - "correct": Message indicating a correct action.
     *             - "incorrect": Message indicating an incorrect action.
     *             - "help": Message indicating a help action.
     *             - "noHelpsLeft": Message indicating no help is available.
     *             - "win": Message indicating a win event.
     */
    public void showMessage(String type) {
        String currentMessage = "";
        Label message = new Label();
        // Select a random message based on the type provided
        if (Objects.equals(type, "correct")) {
            currentMessage = messages[0][new Random().nextInt(13)];

        } else if (Objects.equals(type, "incorrect")) {
            currentMessage = messages[1][new Random().nextInt(13)];
            message.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Arial', sans-serif;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-alignment: center;" +
                    "-fx-border-color: transparent;" +
                    "-fx-border-width: 3px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-effect: dropshadow( gaussian , rgba(255, 0, 0, 0.75) , 10, 0.5 , 0 , 0 );");
        } else if (Objects.equals(type, "help")) {
            currentMessage = messages[2][new Random().nextInt(13)];
            message.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Arial', sans-serif;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-alignment: center;" +
                    "-fx-border-color: transparent;" +
                    "-fx-border-width: 3px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-effect: dropshadow( gaussian , rgba(255, 255, 54, 0.75) , 10, 0.5 , 0 , 0 );");
        } else if (Objects.equals(type, "noHelpsLeft")) {
            currentMessage = messages[3][new Random().nextInt(13)];
            message.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Arial', sans-serif;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-alignment: center;" +
                    "-fx-border-color: transparent;" +
                    "-fx-border-width: 3px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-effect: dropshadow( gaussian , rgba(255, 255, 54, 0.75) , 10, 0.5 , 0 , 0 );");
        } else if (Objects.equals(type, "win")) {
            currentMessage = messages[4][new Random().nextInt(13)];
            message.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Arial', sans-serif;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-alignment: center;" +
                    "-fx-border-color: transparent;" +
                    "-fx-border-width: 3px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-effect: dropshadow( gaussian , rgba(54, 255, 0, 0.75) , 10, 0.5 , 0 , 0 );");
        }

        // Create a new label with the selected message and add it to the message box
        message.setWrapText(true);
        message.setText(currentMessage);
        messagesBox.getChildren().add(message);
        scrollPane.layout();
        scrollPane.setVvalue(1.0); // Scroll to the bottom of the message box
    }
}
