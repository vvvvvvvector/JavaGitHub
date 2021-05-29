package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GraphicsItem {
    public Paddle() {
        height = canvasHeight * 0.02; // paddle height
        width = canvasWidth * 0.2; // paddle width

        y = canvasHeight * 0.9; // start position of paddle y
        x = (canvasWidth - width) / 2; // start position of paddle x (center of GameCanvas)
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(x, y, width, height);
    }

    public void setPosition(double x) {
        this.x = clamp(x - width / 2, canvasWidth - width);
    }

    private static double clamp(double val, double max) {
        return Math.max(0, Math.min(max, val)); // min = 0
    }

    public double getPosition() {
        return x + width / 2;
    }
}
