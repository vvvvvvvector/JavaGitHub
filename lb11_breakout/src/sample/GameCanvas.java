package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas {
    private boolean isGameRunning = false;
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;

    public void initialize() {
        paddle = new Paddle();
        ball = new Ball();
        loadLevel();
    }

    public void loadLevel() {
        bricks = new ArrayList<>();
        Color[] colors = new Color[]{Color.ALICEBLUE, Color.DEEPPINK, Color.BLANCHEDALMOND, Color.BLUEVIOLET, Color.AQUA};
        Brick.setGridRowsAndCols(20, 10); // GRID!!! rows and columns
        for (int i = 0; i < 5; i++) { // 5 rows
            for (int j = 0; j < Brick.getGridCols(); j++) { // 10 columns
                bricks.add(new Brick(j, i + 2, colors[i]));  // (2, 0), ... , (2, 9); (3, 0), ... , (3, 9); ...
            }
        }
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK); // GameCanvas color
        graphicsContext.fillRect(0, 0, getWidth(), getHeight()); // drawing GameCanvas
        paddle.draw(graphicsContext); // drawing paddle
        ball.draw(graphicsContext); // drawing ball
        bricks.forEach(brick -> brick.draw(graphicsContext)); // drawing bricks lul
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
            for (Brick brick : bricks) {
                Point2D[] borderPoints = ball.borderPoints();
                Brick.CrushType crushType = brick.crushes(borderPoints[0], borderPoints[1], borderPoints[2], borderPoints[3]);
                if (crushType != Brick.CrushType.NoCrush) {
                    if (crushType == Brick.CrushType.HorizontalCrush) {
                        ball.bounceVertically();
                    } else {
                        ball.bounceHorizontally();
                    }
                    bricks.remove(brick);
                    break;
                }
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
