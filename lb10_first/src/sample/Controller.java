package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label helloLabel;

    @FXML
    void showHello(){
        helloLabel.setText("Hello World!");
    }
}
