package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();

    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 440, 550);
    }

    public GameCanvas() {
        super(440, 550);
    }
}
