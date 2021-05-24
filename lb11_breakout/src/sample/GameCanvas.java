package sample;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private boolean isGameRunning = false;
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();
    private Paddle paddle;
    private Ball ball;

    public void initialize() {
        paddle = new Paddle();
        ball = new Ball();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK); // GameCanvas color
        graphicsContext.fillRect(0, 0, getWidth(), getHeight()); // drawing GameCanvas
        paddle.draw(graphicsContext); // drawing paddle
        ball.draw(graphicsContext); // drawing ball
    }

    public GameCanvas() {
        super(440, 550);
        GraphicsItem.setCanvasSize(getWidth(), getHeight()); // making GraphicsItem dependent on CanvasSize

        this.setOnMouseMoved(mouseEvent -> {
            paddle.setPosition(mouseEvent.getX());
            draw();
            if(!isGameRunning){
                ball.setPosition(new Point2D(mouseEvent.getX(), paddle.getY()));
            } else {
                ball.updatePosition();
            }
        });

        this.setOnMouseClicked(mouseClickEvent -> {
            isGameRunning = true;
        });
    }
}
