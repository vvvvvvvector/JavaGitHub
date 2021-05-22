package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField firstValueField;

    @FXML
    private TextField secondValueField;

    @FXML
    private TextField resultField;

    @FXML
    private Button resultButton;

    @FXML
    private ChoiceBox<String> operationBox;

    @FXML
    public void initialize() {
        operationBox.getItems().add("+");
        operationBox.getItems().add("-");
        operationBox.getItems().add("*");
        operationBox.getItems().add("/");
        operationBox.setValue(operationBox.getItems().get(0));

        resultButton.setOnAction(actionEvent -> {
            double firstValue = Double.parseDouble(firstValueField.getText());
            double secondValue = Double.parseDouble(secondValueField.getText());
            double result = 0;

            switch (operationBox.getValue()) {
                case "+" -> result = firstValue + secondValue;
                case "-" -> result = firstValue - secondValue;
                case "*" -> result = firstValue * secondValue;
                case "/" -> {
                    if (secondValue != 0) {
                        result = firstValue / secondValue;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Exception");
                        alert.setContentText("Division by 0!");
                        alert.showAndWait();
                    }
                }
            }

            resultField.setText(String.valueOf(result));
        });
    }
}
