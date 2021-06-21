package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    Client client;
    GameWidget gameWidget;

    @FunctionalInterface
    public interface CreateListener {
        void method();
    }

    @FunctionalInterface
    public interface JoinListener {
        void method(String address);
    }

    @FunctionalInterface
    public interface PositionClickedListener {
        void method(int x, int y);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionWidget connectionWidget = new ConnectionWidget();

        connectionWidget.setCreateGameButton(() -> { // the action that createGameButton performs
            Server server = new Server(5000, this);
            server.start();
            client = new Client(5000, "localhost", this);
            client.start();
        });

        connectionWidget.setJoinGameButton((address) -> { // the action that joinGameButton performs
            client = new Client(5000, address, this);
            client.start();
            showGameWidget("Client", false);
        });

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.getIcons().add(new Image("file:D:\\javaUniGitHub\\lb13_my\\Icon\\TicTacToe.png"));
        primaryStage.setScene(new Scene(connectionWidget, 300, 300)); // open ConnectionWidget main window
        primaryStage.show();
    }

    public void setRemoteSymbol(int x, int y) {
        gameWidget.setRemoteSymbol(x, y);
    }

    public void showGameWidget(String windowTitle, boolean isMyTurn) { // shows GameWidget window lul
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        gameWidget = new GameWidget(isMyTurn);

        gameWidget.setPositionClickedListener((x, y) -> client.sendPos(x, y));

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(gameWidget);
        stage.setScene(new Scene(anchorPane));
        stage.getIcons().add(new Image("file:D:\\javaUniGitHub\\lb13_my\\Icon\\TicTacToe.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
