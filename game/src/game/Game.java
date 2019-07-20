package game;

import game.input.InputFrame;
import game.input.MouseDragEvent;
import game.objects.Ball;
import game.objects.Paddle;
import game.objects.UI;
import game.objects.Powerup;
import processing.Drawable;
import processing.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

public class Game implements GetDrawPayload {
    public float room_width = 1777f;
    public float room_height = 1000f;

    private int player_0_life = 10;
    private int player_1_life = 10;

    // Game Objects
    private final Paddle paddle_0 = new Paddle(
            0f,
            room_height / 2 - Paddle.default_height / 2
    );
    private final Paddle paddle_1 = new Paddle(
            room_width - Paddle.default_width,
            room_height / 2 - Paddle.default_height / 2
    );

    private final List<Ball> balls = new ArrayList<>();
    private final List<Powerup> powerups = new ArrayList<>();

    private final UI ui = new UI(this);

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
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            for (int j = i+1; j < balls.size(); j++) {
                Ball b2 = balls.get(j);

                if (Math.sqrt((b.x - b2.x)*(b.x - b2.x) + (b.y - b2.y)*(b.y - b2.y)) < b.radius + b2.radius) {
                    float delta_x = b.x - b2.x;
                    float delta_y = b.y - b2.y;

                    float mag = delta_x*delta_x + delta_y*delta_y;

                    float delta_dx = b.dx - b2.dx;
                    float delta_dy = b.dy - b2.dy;

                    float mass_ratio = 2/2f;
                    float mass_velocity_multiplier = (delta_dx * delta_x + delta_dy * delta_y) / mag;
                    b.dx = b.dx - mass_ratio * mass_velocity_multiplier * delta_x;
                    b.dy = b.dy - mass_ratio * mass_velocity_multiplier * delta_y;

                    b2.dx = b2.dx - mass_ratio * mass_velocity_multiplier * -delta_x;
                    b2.dy = b2.dy - mass_ratio * mass_velocity_multiplier * -delta_y;
                }
            }

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
                b.dy *= -1;
            }
            if (b.y + b.radius > room_height && b.dy > 0) {
                b.dy *= -1;
            }
        }
    }

    private void handleInput(float delta) {
        // todo.
        while (!input.mouseDragEventQueue.isEmpty()) {
            MouseDragEvent event = input.mouseDragEventQueue.poll();
            Ball new_ball = new Ball(event.startX, event.startY);
            new_ball.dx = (event.endX - event.startX);
            new_ball.dy = (event.endY - event.startY);
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
        if (input.keyPressed(InputFrame.G)){
            powerups.add(new Powerup(50, 50, Effect.Name.SPEED));
        }
    }

    public Camera getCamera() {
        return canonical_camera;
    }

    @Override
    public DrawPayload getDrawPayload() {
        List<Drawable> drawables = new ArrayList<>();
        drawables.add(paddle_0);
        drawables.add(paddle_1);
        drawables.addAll(balls);
        drawables.addAll(powerups);
        drawables.add(ui);


        return new DrawPayload(drawables, this.getCamera());
    }
}
