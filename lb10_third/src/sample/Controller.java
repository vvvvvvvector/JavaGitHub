package sample;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private MenuItem closeItem;

    @FXML
    private AnchorPane mainPane;

    @FXML
    public void initialize() {
        closeItem.setOnAction(closeItemEvent -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        });
    }
}
