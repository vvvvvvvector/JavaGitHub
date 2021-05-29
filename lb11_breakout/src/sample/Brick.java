package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Brick extends GraphicsItem {
    private static int gridRows; // so 20
    private static int gridCols; // so 10
    private Color color;

    public enum CrushType {NoCrush, HorizontalCrush, VerticalCrush}

    public static int getGridCols() {
        return gridCols;
    }

    // here we set GRID columns and rows
    public static void setGridRowsAndCols(int gridRows, int gridCols) {
        Brick.gridRows = gridRows;
        Brick.gridCols = gridCols;
    }

    public Brick(int gridX, int gridY, Color color) {
        this.color = color;
        width = canvasWidth / gridCols; // brick width
        height = canvasHeight / gridRows; // birch height
        x = gridX * width; // brick x position
        y = gridY * height; // brick y position
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, width, height); // fill one brick
        graphicsContext.setStroke(color.brighter());
        graphicsContext.strokeLine(x, y, x + width, y); // p1 = (0, 0) p2 = (width, 0) - horizontal line (top)
        graphicsContext.strokeLine(x, y, x, y + height); // p1 = (0, 0) p2 = (0, height) - vertical line (left)
        graphicsContext.setStroke(color.darker());
        graphicsContext.strokeLine(x, y + height, x + width, y + height); // p1 = (0, height) p2 = (width, height) - horizontal line (bottom)
        graphicsContext.strokeLine(x + width, y, x + width, y + height); // p1 = (width, 0) p2 = (width, height) - vertical line (right)
    }

    public CrushType crushes(Point2D left, Point2D right, Point2D top, Point2D bottom) {
        if (contains(left) || contains(right)) return CrushType.HorizontalCrush;
        if (contains(top) || contains(bottom)) return CrushType.VerticalCrush;
        return CrushType.NoCrush;
    }

    private boolean contains(Point2D point) {
        return x <= point.getX() && point.getX() <= x + width && y <= point.getY() && point.getY() <= y + height;
    }
}
