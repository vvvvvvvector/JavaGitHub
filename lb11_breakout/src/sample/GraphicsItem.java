package sample;

import javafx.scene.canvas.GraphicsContext;

public abstract class GraphicsItem {
    protected static double canvasWidth;
    protected static double canvasHeight;

    protected double x; // item x position
    protected double y; // item y position
    protected double width; // item width
    protected double height; // item height

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public static void setCanvasSize(double canvasWidth, double canvasHeight) {
        GraphicsItem.canvasWidth = canvasWidth;
        GraphicsItem.canvasHeight = canvasHeight;
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
