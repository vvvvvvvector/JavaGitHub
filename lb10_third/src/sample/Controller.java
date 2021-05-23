package sample;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {
    @FXML
    private MenuItem closeItem;

    @FXML
    private TextArea textArea;

    @FXML
    private MenuItem openItem;

    @FXML
    private MenuItem saveAsItem;

    @FXML
    private AnchorPane mainPane;

    private File file = null;

    @FXML
    public void initialize() {
        closeItem.setOnAction(closeItemEvent -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        });

        openItem.setOnAction(openItemEvent -> {
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(new Stage()); // open fileChooser window
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath()))); // shows file text
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveAsItem.setOnAction(saveAsItemEvent -> {
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(new Stage());
            Path pathToWrite = file.toPath(); // the path to the file
            String textAreaText = textArea.getText(); // text to be written
            try {
                Files.writeString(pathToWrite, textAreaText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
