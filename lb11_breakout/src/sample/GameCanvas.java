package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();
    private Paddle paddle;

    public void initialize() {
        paddle = new Paddle();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK); // GameCanvas color
        graphicsContext.fillRect(0, 0, getWidth(), getHeight()); // GameCanvas size
        paddle.draw(graphicsContext); //drawing paddle
    }

    public GameCanvas() {
        super(440, 550);
        GraphicsItem.setCanvasSize(getWidth(), getHeight()); // making GraphicsItem dependent on CanvasSize

        this.setOnMouseMoved(mouseEvent -> {
            paddle.setPosition(mouseEvent.getX());
            draw();
        });
    }
}
