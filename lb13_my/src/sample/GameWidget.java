package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameWidget extends Canvas { // canvas - empty rectangle area

    public GameWidget() {
        super(300, 300);
        draw();
    }

    private void draw() { // for now just draws black rectangle
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());
    }
}
