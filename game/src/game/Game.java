package game;

import game.input.InputFrame;
import game.input.MouseDragEvent;
import game.objects.Ball;
import game.objects.Paddle;
import processing.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Game implements GetDrawPayload {

    private final Paddle paddle_0 = new Paddle(0f, 500f,
            Paddle.default_width, Paddle.default_height);

    private final Paddle paddle_1 = new Paddle((1000f - Paddle.default_width), 500f,
            Paddle.default_width, Paddle.default_height);

    private final List<Ball> balls = new ArrayList<>();

    public final InputFrame input = new InputFrame();

    private final boolean running = true;
    private final int tickTime = 10;

    public void mainloop() {
        long past = System.currentTimeMillis();
        long accumulator = 0;

        while (running) {
            long delta = System.currentTimeMillis() - past;
            past = System.currentTimeMillis();
            accumulator += delta;

            while (accumulator >= tickTime) {
                tick(tickTime / 100f);
                accumulator -= tickTime;
            }
        }
    }

    private void tick(float delta) {
        handleCollision(delta);
        handleInput(delta);

        for (Ball b: balls) {
            b.x += b.dx * delta;
            b.y += b.dy * delta;
        }
    }

    private void handleCollision(float delta) {
        // todo.
    }

    private void handleInput(float delta) {
        // todo.
        while (!input.mouseDragEventQueue.isEmpty()) {
            MouseDragEvent event = input.mouseDragEventQueue.poll();
            System.out.println("Mouse event");
            Ball new_ball = new Ball(event.startX, event.startY);
            new_ball.dx = (event.endX - event.startX) / 10f;
            new_ball.dy = (event.endY - event.startY) / 10f;
            balls.add(new_ball);
        }

//        input.onKeys.entrySet().stream()
//                .filter(e -> e.getValue())
//                .forEach(e -> System.out.println(e.getKey()));

        if (input.keyPressed(InputFrame.W)) {
            paddle_0.y -= delta * paddle_0.max_speed;
        }
        if (input.keyPressed(InputFrame.S)) {
            paddle_0.y += delta * paddle_0.max_speed;
        }

        if (input.keyPressed(InputFrame.UP)) {
            paddle_1.y -= delta * paddle_1.max_speed;
        }
        if (input.keyPressed(InputFrame.DOWN)) {
            paddle_1.y += delta * paddle_1.max_speed;
        }
    }

    @Override
    public DrawPayload getDrawPayload() {
        List<Drawable> drawables = new ArrayList<>();
        drawables.add(paddle_0);
        drawables.add(paddle_1);
        drawables.addAll(balls);

        return new DrawPayload(drawables);
    }
}
