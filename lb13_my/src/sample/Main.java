package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public void showGameWidget() { // shows GameWidget window lul
        Stage stage = new Stage();
        GameWidget gameWidget = new GameWidget();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(gameWidget);
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionWidget connectionWidget = new ConnectionWidget();
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(connectionWidget, 300, 300)); // open ConnectionWidget main window
        primaryStage.show();
        connectionWidget.getJoinGameButton().setOnAction(actionEvent -> { // for now just opening GameWidget window
            showGameWidget();
        });
        connectionWidget.getCreateGameButton().setOnAction(actionEvent -> { // for now just opening GameWidget
            showGameWidget();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
