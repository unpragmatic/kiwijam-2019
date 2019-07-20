package game;

import game.input.InputFrame;
import game.input.MouseDragEvent;
import game.objects.Ball;
import game.objects.Paddle;
import processing.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Game implements GetDrawPayload {
    public float room_width = 1777f;
    public float room_height = 1000f;

    private final Paddle paddle_0 = new Paddle(
            0f,
            room_height / 2 - Paddle.default_height / 2);

    private final Paddle paddle_1 = new Paddle(
            room_width - Paddle.default_width,
            room_height / 2 - Paddle.default_height / 2);

    private final List<Ball> balls = new ArrayList<>();

    public final InputFrame input = new InputFrame();

    private Camera canonical_camera = new Camera(0, 0, room_width, room_height);

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
                tick(tickTime / 1000f);
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
        for (Ball b: balls) {
            if (b.y > paddle_0.y && b.y < paddle_0.y + paddle_0.height &&
                    b.x - b.radius < paddle_0.x + paddle_0.width && b.dx < 0) {

                b.dx *= -1;
            }

            // With right side
            if (b.y > paddle_1.y && b.y < paddle_1.y + paddle_1.height &&
                    b.x + b.radius > paddle_1.x && b.dx > 0) {
                b.dx *= -1;
            }

            if (b.y - b.radius < 0 && b.dy < 0) {
                b.dy *= 1;
            }
            if (b.y + b.radius > room_height && b.dy > 0) {
                b.dy *= 1;
            }
        }
    }

    private void handleInput(float delta) {
        // todo.
        while (!input.mouseDragEventQueue.isEmpty()) {
            MouseDragEvent event = input.mouseDragEventQueue.poll();
            System.out.println("Mouse event");
            Ball new_ball = new Ball(event.startX, event.startY);
            new_ball.dx = (event.endX - event.startX) / 5f;
            new_ball.dy = (event.endY - event.startY) / 5f;
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
        if (input.keyPressed(InputFrame.T)){
            canonical_camera.translate_x -= 10;
        }
        if (input.keyPressed(InputFrame.Y)){
            canonical_camera.translate_x += 10;
        }
    }

    public Camera getCamera() {
        return new Camera(canonical_camera.translate_x, canonical_camera.translate_y, room_width, room_height);
    }

    @Override
    public DrawPayload getDrawPayload() {
        List<Drawable> drawables = new ArrayList<>();
        drawables.add(paddle_0);
        drawables.add(paddle_1);
        drawables.addAll(balls);


        return new DrawPayload(drawables, this.getCamera());
    }
}
