package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector = new Point2D(1, -1).normalize(); // 45 degrees corner -> right
    private double velocity = 240;
    double lastX, lastY;

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

    public void updatePosition(double difference) {
        lastX = this.x;
        lastY = this.y;
        this.x += moveVector.getX() * velocity * difference;
        this.y += moveVector.getY() * velocity * difference;
    }

    public void bounceHorizontally() {
        moveVector = new Point2D(moveVector.getX(), -moveVector.getY()).normalize();
    }

    public void bounceVertically() {
        moveVector = new Point2D(-moveVector.getX(), moveVector.getY()).normalize();
    }

    public void bounceFromPaddle(double pos) {
        moveVector = new Point2D(pos * 5, -moveVector.getY()).normalize();
    }
}
