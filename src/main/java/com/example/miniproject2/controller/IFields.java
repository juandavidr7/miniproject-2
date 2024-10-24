package com.example.miniproject2.controller;

import javafx.scene.control.TextField;

public interface IFields {

    void createTextFields();
    void insertNumberIntoActiveTextField(int number);
    void validateAndColorFields(TextField textField, int i, int j, int num, boolean isClue);
}
