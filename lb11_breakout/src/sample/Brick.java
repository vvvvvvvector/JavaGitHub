package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicsItem {
    private static int gridRows; // so 20
    private static int gridCols; // so 10
    private Color color;

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
        graphicsContext.fillRect(x, y, width, height);
        graphicsContext.setStroke(color.brighter());
        graphicsContext.strokeLine(x, y, x + width, y);
        graphicsContext.strokeLine(x, y, x, y + height);
        graphicsContext.setStroke(color.darker());
        graphicsContext.strokeLine(x, y + height, x + width, y + height);
        graphicsContext.strokeLine(x + width, y, x + width, y + height);
    }
}
