package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @FunctionalInterface
    public interface CreateListener {
        void method();
    }

    @FunctionalInterface
    public interface JoinListener {
        void method(String address);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionWidget connectionWidget = new ConnectionWidget();

        connectionWidget.setCreateGameButton(() -> { // the action that createGameButton performs
            Server server = new Server(5000, this);
            server.start();
            Client client = new Client(5000, "localhost");
            client.start();
        });

        connectionWidget.setJoinGameButton((address) -> { // the action that joinGameButton performs
            Client client = new Client(5000, address);
            client.start();
            showGameWidget("Client");
        });

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(connectionWidget, 300, 300)); // open ConnectionWidget main window
        primaryStage.show();
    }

    public void showGameWidget(String windowTitle) { // shows GameWidget window lul
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        GameWidget gameWidget = new GameWidget();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(gameWidget);
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
