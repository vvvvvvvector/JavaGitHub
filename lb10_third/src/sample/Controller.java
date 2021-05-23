package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
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
    private MenuItem saveItem;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button openButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button saveAsButton;

    private File file = null;

    @FXML
    public void initialize() {
        closeItem.setOnAction(closeItemEvent -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        });

        openItem.setOnAction(this::handleOpen);
        openButton.setOnAction(this::handleOpen);
        openButton.setGraphic(new ImageView("file:D:\\javaUniGitHub\\lb10_third\\Icons\\open.png"));
        openButton.setTooltip(new Tooltip("Open"));

        saveAsItem.setOnAction(this::handleSaveAs);
        saveAsButton.setOnAction(this::handleSaveAs);
        saveAsButton.setGraphic(new ImageView("file:D:\\javaUniGitHub\\lb10_third\\Icons\\saveAs.png"));
        saveAsButton.setTooltip(new Tooltip("Save As"));

        saveItem.setOnAction(this::handleSave);
        saveButton.setOnAction(this::handleSave);
        saveButton.setGraphic(new ImageView("file:D:\\javaUniGitHub\\lb10_third\\Icons\\save.png"));
        saveButton.setTooltip(new Tooltip("Save"));
    }

    private void handleSaveAs(ActionEvent saveAsItemEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(new Stage());
        Path pathToWrite = file.toPath(); // the path to the file
        String textAreaText = textArea.getText(); // text to be written
        try {
            Files.writeString(pathToWrite, textAreaText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleOpen(ActionEvent openItemEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(new Stage()); // open fileChooser window
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getPath()))); // shows file text
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSave(ActionEvent saveItemEvent) {
        if (file == null) {
            handleSaveAs(saveItemEvent);
        } else {
            Path path = file.toPath();
            String textAreaText = textArea.getText();
            try {
                Files.writeString(path, textAreaText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
