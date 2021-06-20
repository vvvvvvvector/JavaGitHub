package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;

public class GameWidget extends Canvas { // canvas - empty rectangle area
    private enum Symbol {X, O, Empty}

    private Symbol[][] fields = new Symbol[3][3];

    public GameWidget() {
        super(300, 300);
        for (Symbol row[] : fields) {
            Arrays.fill(row, Symbol.Empty);
        }
        setOnMouseClicked(mouseEvent -> {
            double posX = mouseEvent.getX();
            double posY = mouseEvent.getY();
            int row = (int) (posX / (getWidth() / 3));
            int column = (int) (posY / (getHeight() / 3));
            setSymbol(row, column);
            draw();
        });
        draw();
    }

    private void setSymbol(int row, int column) {
        fields[row][column] = Symbol.X;
    }

    private void draw() { // for now just draws black rectangle
        GraphicsContext graphicsContext = getGraphicsContext2D();
        // drawing rectangle
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());
        //drawing strokes
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.strokeLine(getWidth() / 3, 0, getWidth() / 3, getHeight());
        graphicsContext.strokeLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3, getHeight());
        graphicsContext.strokeLine(0, getHeight() / 3, getWidth(), getHeight() / 3);
        graphicsContext.strokeLine(0, 2 * getHeight() / 3, getWidth(), 2 * getHeight() / 3);
        // drawing symbols
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font("Arial",40));
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                switch (fields[x][y]) {
                    case X -> graphicsContext.fillText("X", (x * getWidth() / 3 + getWidth() / 6) - 13.5, (y * getHeight() / 3 + getHeight() / 6) + 13.5);
                    case O -> graphicsContext.fillText("O", (x * getWidth() / 3 + getWidth() / 6) - 13.5, (y * getHeight() / 3 + getHeight() / 6) + 13.5);
                }
            }
        }
    }
}
