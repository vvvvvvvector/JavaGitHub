package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectionWidget extends GridPane { // GridPane - rows / cols
    private Button createGameButton;
    private Button joinGameButton;
    private Label addressLabel;
    private TextField addressField;

    public ConnectionWidget() {
        this.createGameButton = new Button("Create Game");
        this.joinGameButton = new Button("Join Game");
        this.addressLabel = new Label("Address: ");
        this.addressField = new TextField("127.0.0.1");
        this.add(createGameButton, 0, 0, 2, 1); // columnIndex, rowIndex, colSpan, rowSpan
        this.add(addressLabel, 0, 1); // column = 1, row = 1
        this.add(addressField, 1, 1); // column = 2, row = 1
        this.add(joinGameButton, 0, 2, 2, 1);
    }

    public void setCreateGameButton(Main.CreateListener createListener) {
        createGameButton.setOnAction(clickEvent -> {
            createListener.method(); // the action that createGameButton performs
        });
    }

    public void setJoinGameButton(Main.JoinListener joinListener) {
        joinGameButton.setOnAction(clickEvent -> {
            joinListener.method(addressField.getText()); // the action that joinGameButton performs
        });
    }
}
