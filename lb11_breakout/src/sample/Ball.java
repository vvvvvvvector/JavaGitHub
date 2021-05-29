package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector = new Point2D(1, -1).normalize(); // 45 degrees corner -> right
    private double velocity = 350;
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
        this.x = point2D.getX() - width / 2; // x: center of gameCanvas
        this.y = point2D.getY() - width / 2 - 8; // y: like paddle, but top +8; ball doesn't lie on the platform
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

    public Point2D[] borderPoints() {
        Point2D[] points = new Point2D[4];
        points[0] = new Point2D(x, y + height / 2); // (0, height / 2) - ball left point
        points[1] = new Point2D(x + width, y + height / 2); // (width, height / 2) - ball right point
        points[2] = new Point2D(x + width / 2, y); // (width / 2, 0) - ball top point
        points[3] = new Point2D(x + width / 2, y + height); // (width / 2, height) - ball bottom point
        return points;
    }
}
