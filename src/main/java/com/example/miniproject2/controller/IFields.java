package com.example.miniproject2.controller;

import javafx.scene.control.TextField;

public interface IFields {

    void createTextFields();
    void insertNumberIntoActiveTextField(int number);
    void validateAndColorField(TextField textField, int num, int num2, int num3);
}