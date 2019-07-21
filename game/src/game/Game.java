package game;

import game.input.InputFrame;
import game.input.MouseDragEvent;
import game.objects.*;
import processing.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Game implements GetDrawPayload {
    public static final float ROOM_WIDTH = 1777f;
    public static final float ROOM_HEIGHT = 1000f;

    private final float padding = ROOM_WIDTH * 0.015f;
    private final float upper_border_y = ROOM_HEIGHT * 0.10f;
    private final float lower_border_y = ROOM_HEIGHT * 0.90f;

    public int player_0_life = 10;
    public int player_0_life_total = 10;
    public int player_1_life = 10;
    public int player_1_life_total = 10;

    // Game Objects
    private final Paddle paddle_0 = new Paddle(
            padding,
            ROOM_HEIGHT / 2 - Paddle.DEFAULT_HEIGHT / 2
    );
    private final Paddle paddle_1 = new Paddle(
            ROOM_WIDTH - Paddle.DEFAULT_WIDTH - padding,
            ROOM_HEIGHT / 2 - Paddle.DEFAULT_HEIGHT / 2
    );

    private final List<Ball> balls = new ArrayList<>();
    private final List<Powerup> powerups = new ArrayList<>();
    private final Border upper_border = new Border(0, upper_border_y);
    private final Border lower_border = new Border(0, lower_border_y);

    private final UI ui = new UI(this);

    public final InputFrame input = new InputFrame();

    private Camera canonical_camera = new Camera(0, 0, ROOM_WIDTH, ROOM_HEIGHT);

    private final boolean running = true;
    private final int tickTime = 10;

    private void restart() {
        paddle_0.x = ROOM_WIDTH - Paddle.DEFAULT_WIDTH - padding;
        paddle_1.y = ROOM_HEIGHT / 2 - Paddle.DEFAULT_HEIGHT / 2;
        player_0_life = player_0_life_total;
        player_1_life = player_1_life_total;

        ballsToRemove.addAll(balls);
    }

    // public interface
    public final List<Ball> ballsToAdd = new ArrayList<>();
    public final List<Ball> ballsToRemove = new ArrayList<>();

    public void increaseLife(int player, int amount){
        if (player == 0) player_0_life += amount;
        if (player == 1) player_1_life += amount;
    }

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
        handleInput(delta);

        if (player_0_life <= 0 || player_1_life <= 0) {
            return;
        }

        handleCollision(delta);

        for (Ball b: balls) {
            b.x += b.dx * delta;
            b.y += b.dy * delta;
        }

        paddle_0.y += paddle_0.dy * delta;
        paddle_1.y += paddle_1.dy * delta;
        if (paddle_0.y < upper_border_y + upper_border.DEFAULT_HEIGHT) {
            paddle_0.y = upper_border_y + upper_border.DEFAULT_HEIGHT;
        } else if (paddle_0.y + paddle_0.height > lower_border_y) {
            paddle_0.y = lower_border_y - paddle_0.height;
        }
        if (paddle_1.y < upper_border_y + upper_border.DEFAULT_HEIGHT) {
            paddle_1.y = upper_border_y + upper_border.DEFAULT_HEIGHT;
        } else if (paddle_1.y + paddle_1.height > lower_border_y) {
            paddle_1.y = lower_border_y - paddle_1.height;
        }

        balls.removeAll(ballsToRemove);
        balls.addAll(ballsToAdd);
        ballsToRemove.clear();
        ballsToAdd.clear();
    }

    private void handle_ball_ball_collision(float delta) {
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
        }
    }
    private void handle_ball_paddle_collision(float delta) {
        for (Ball b: balls) {
            if (b.y > paddle_0.y && b.y < paddle_0.y + paddle_0.height &&
                    b.x - b.radius < paddle_0.x + paddle_0.width &&
                    b.x - b.radius > paddle_0.x &&
                    b.dx < 0) {

                b.dx *= -1;
            }

            // With right side
            if (b.y > paddle_1.y && b.y < paddle_1.y + paddle_1.height &&
                    b.x + b.radius > paddle_1.x &&
                    b.x + b.radius < paddle_1.x + paddle_1.width &&
                    b.dx > 0) {
                b.dx *= -1;
            }
        }
    }
    private void handle_ball_border_collision(float delta) {
        for (Ball b: balls) {
            if (b.y - b.radius < upper_border_y + upper_border.DEFAULT_HEIGHT && b.dy < 0) {
                b.dy *= -1;
            }
            if (b.y + b.radius > lower_border_y && b.dy > 0) {
                b.dy *= -1;
            }

            if (b.x - b.radius < -10) {
                player_0_life -= 1;
                ballsToRemove.add(b);
            } else if (b.x + b.radius > ROOM_WIDTH) {
                player_1_life -= 1;
                ballsToRemove.add(b);
            }

        }
    }

    private void handleCollision(float delta) {
        // todo.
        handle_ball_ball_collision(delta);
        handle_ball_paddle_collision(delta);
        handle_ball_border_collision(delta);
    }

    private void handleInput_paddles(float delta) {
        float floatiness = 0.95f;
        float acceleration = 2500f;
        float initial_speed = 150;

        if (input.keyPressed(InputFrame.W)) {
            if (paddle_0.dy > 0) {
                paddle_0.dy = -initial_speed;
            }
            paddle_0.dy = Math.min(paddle_0.dy - (acceleration * delta), -paddle_0.max_speed);
        } else if (input.keyPressed(InputFrame.S)) {
            if (paddle_0.dy < 0) {
                paddle_0.dy = initial_speed;
            }
            paddle_0.dy = Math.max(paddle_0.dy + (acceleration * delta), paddle_0.max_speed);
        } else {
            paddle_0.dy = floatiness * paddle_0.dy;
            if (Math.abs(paddle_0.dy) < 10f) { paddle_0.dy = 0; }
        }

        if (input.keyPressed(InputFrame.UP)) {
            if (paddle_1.dy > 0) {
                paddle_1.dy = -initial_speed;
            }
            paddle_1.dy = Math.min(paddle_1.dy - (acceleration * delta), -paddle_1.max_speed);
        } else if (input.keyPressed(InputFrame.DOWN)) {
            if (paddle_1.dy < 0) {
                paddle_1.dy = initial_speed;
            }
            paddle_1.dy = Math.max(paddle_1.dy + (acceleration * delta), paddle_1.max_speed);
        } else {
            paddle_1.dy = floatiness * paddle_1.dy;
            if (Math.abs(paddle_1.dy) < 10f) { paddle_1.dy = 0; }
        }

    }

    private void handleInput(float delta) {
        // todo.
        while (!input.mouseDragEventQueue.isEmpty()) {
            MouseDragEvent event = input.mouseDragEventQueue.poll();
            Ball new_ball = new Ball(event.startX, event.startY);
            new_ball.dx = (event.endX - event.startX)*2.5f;
            new_ball.dy = (event.endY - event.startY)*2.5f;
            balls.add(new_ball);
        }

        handleInput_paddles(delta);

        if (input.keyPressed(InputFrame.T)){
            canonical_camera.translate_x -= 10;
        }
        if (input.keyPressed(InputFrame.Y)){
            canonical_camera.translate_x += 10;
        }
        if (input.keyPressed(InputFrame.G)){
            powerups.add(new Powerup(100, 100, Effect.Name.SPEED));
        }
        if (input.keyPressed(InputFrame.H)){
            Effect.applyEffect(Effect.Name.SPEED, this, balls.get(0));
        }
        if (input.keyPressed(InputFrame.P)){
            Ball b = new Ball((float)Math.random()*ROOM_WIDTH, (float)Math.random()*ROOM_HEIGHT);
            float velocity = 1000f;
            double angle = Math.random()*Math.PI*2;
            b.dx = (float) (Math.cos(angle) * velocity);
            b.dy = (float) (Math.sin(angle) * velocity);
            ballsToAdd.add(b);
        }
        if (input.keyPressed(InputFrame.R)) {
            restart();
        }
    }

    public Camera getCamera() {
        return canonical_camera;
    }

    private List<Drawable> drawables = new ArrayList<>();
    @Override
    public DrawPayload getDrawPayload() {
        drawables.clear();
        drawables.add(paddle_0);
        drawables.add(paddle_1);
        drawables.addAll(balls);
        drawables.addAll(powerups);
        drawables.add(ui);
        drawables.add(upper_border);
        drawables.add(lower_border);


        return new DrawPayload(drawables, this.getCamera());
    }
}
