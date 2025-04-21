package com.calculator;

import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CalculatorController {

    @FXML private JFXTextField display;
    private String operator = "";
    private double firstNumber = 0;
    private boolean start = true;

    @FXML
    private void initialize() {
        display.setOnKeyPressed(this::handleKeyInput);
    }

    public void handleDigit(javafx.event.ActionEvent e) {
        String value = ((Button) e.getSource()).getText();
        if (start || display.getText().equals("0")) {
            display.setText(value);
            start = false;
        } else {
            display.setText(display.getText() + value);
        }
    }

    public void handleDot() {
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    public void handleOperation(javafx.event.ActionEvent e) {
        operator = ((Button) e.getSource()).getText();
        firstNumber = Double.parseDouble(display.getText());
        display.clear();
    }

    public void handleEquals() {
        double secondNumber = Double.parseDouble(display.getText());
        double result = switch (operator) {
            case "+" -> firstNumber + secondNumber;
            case "−" -> firstNumber - secondNumber;
            case "×" -> firstNumber * secondNumber;
            case "÷" -> secondNumber != 0 ? firstNumber / secondNumber : 0;
            case "%" -> firstNumber % secondNumber;
            case "^" -> Math.pow(firstNumber, secondNumber);
            default -> 0;
        };
        display.setText(String.valueOf(result));
        start = true;
    }

    public void handleClear() {
        display.clear();
        operator = "";
        firstNumber = 0;
        start = true;
    }

    public void handleBackspace() {
        String text = display.getText();
        if (text.length() > 0) {
            display.setText(text.substring(0, text.length() - 1));
        }
    }

    public void handleSqrt() {
        double value = Double.parseDouble(display.getText());
        display.setText(String.valueOf(Math.sqrt(value)));
    }

    public void handlePower() {
        operator = "^";
        firstNumber = Double.parseDouble(display.getText());
        display.clear();
    }

    public void handleKeyInput(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.isDigitKey() || code.isKeypadKey()) {
            display.setText(display.getText() + event.getText());
        } else if (code == KeyCode.BACK_SPACE) {
            handleBackspace();
        } else if (code == KeyCode.ENTER) {
            handleEquals();
        }
    }
}
