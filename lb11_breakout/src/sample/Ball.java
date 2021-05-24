package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector = new Point2D(1, -1).normalize(); // 45 degrees corner -> right
    private double velocity = 170;

    public Ball() {
        width = height = canvasHeight * 0.03;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void setPosition(Point2D point2D) {
        this.x = point2D.getX() - width / 2;
        this.y = point2D.getY() - width / 2 - 8;
    }

    public void updatePosition(double difference){
        this.x += moveVector.getX() * velocity * difference;
        this.y += moveVector.getY() * velocity * difference;
    }
}
