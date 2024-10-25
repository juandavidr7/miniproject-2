package com.example.miniproject2.controller;

import javafx.scene.control.TextField;

/**
 * The {@code IFields} interface defines the methods required for managing
 * text fields in the Sudoku application. Implementing classes should provide
 * functionality for creating text fields, inserting numbers, and validating
 * input.
 */
public interface IFields {

    /**
     * Creates the text fields for the Sudoku grid. This method should
     * initialize and configure the text fields as needed.
     */
    void createTextFields();

    /**
     * Inserts a number into the currently active text field.
     *
     * @param number The number to be inserted into the active text field.
     */
    void insertNumberIntoActiveTextField(int number);

    /**
     * Validates the input in a specified text field and applies color
     * based on its correctness. This method checks the number against
     * the Sudoku rules and updates the visual representation accordingly.
     *
     * @param textField The text field to validate.
     * @param i The row index of the field in the Sudoku grid.
     * @param j The column index of the field in the Sudoku grid.
     * @param num The number to validate.
     * @param isClue Indicates whether the field is a clue (immutable).
     */
    void validateAndColorFields(TextField textField, int i, int j, int num, boolean isClue);
}
