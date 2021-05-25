package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private boolean isGameRunning = false;
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();
    private Paddle paddle;
    private Ball ball;

    public void initialize() {
        paddle = new Paddle();
        ball = new Ball();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK); // GameCanvas color
        graphicsContext.fillRect(0, 0, getWidth(), getHeight()); // drawing GameCanvas
        paddle.draw(graphicsContext); // drawing paddle
        ball.draw(graphicsContext); // drawing ball
    }

    public GameCanvas() {
        super(440, 550);
        GraphicsItem.setCanvasSize(getWidth(), getHeight()); // making GraphicsItem dependent on CanvasSize

        this.setOnMouseMoved(mouseEvent -> {
            paddle.setPosition(mouseEvent.getX());
            draw();
            if (!isGameRunning) { // if game isn't running ball is moving with paddle
                ball.setPosition(new Point2D(mouseEvent.getX(), paddle.getY()));
            }
        });

        this.setOnMouseClicked(mouseClickEvent -> { // game starts, ball movement doesn't depend on mouse movement
            isGameRunning = true;
            animationTimer.start();
        });
    }

    private AnimationTimer animationTimer = new AnimationTimer() { // creating a new timer
        private long lastUpdate;

        @Override
        public void handle(long now) { // this method is called in every frame while animationTimer is active
            double diff = (now - lastUpdate) / 1_000_000_000.0; // number of seconds since the last frame
            ball.updatePosition(diff);
            draw();
            lastUpdate = now;
            if (shouldBallBounceHorizontally()) {
                ball.bounceHorizontally();
            }
            if (shouldBallBounceVertically()) {
                ball.bounceVertically();
            }
            if (shouldBallBounceFromPaddle()) {
                ball.bounceFromPaddle((-paddle.getPosition() + (ball.x + ball.width / 2)) / paddle.width);
            }
        }

        @Override
        public void start() { // this method starts animationTimer
            super.start();
            lastUpdate = System.nanoTime();
        }
    };

    private boolean shouldBallBounceVertically() {
        return (ball.x <= 0 && ball.lastX > 0)
                || (ball.x + ball.width >= getWidth() - 1
                && ball.lastX + ball.width < getWidth() - 1);
    }

    private boolean shouldBallBounceHorizontally() {
        return ball.lastY > 0 && ball.y <= 0;
    }

    private boolean shouldBallBounceFromPaddle() {
        return ball.lastY + ball.height < paddle.y
                && ball.y + ball.height >= paddle.y
                && ball.x >= paddle.x
                && ball.x <= paddle.x + paddle.width;
    }
}
