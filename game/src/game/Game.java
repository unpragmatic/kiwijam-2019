package game;

import game.objects.Ball;
import game.objects.Paddle;

import java.util.ArrayList;
import java.util.List;

public class Game implements GetDrawPayload {

    // todo: Add initialisation params such as position
    private final Paddle paddle_0 = new Paddle();
    private final Paddle paddle_1 = new Paddle();

    private final List<Ball> balls = new ArrayList<>();

    public final InputFrame input = new InputFrame();

    public final boolean running = true;
    private final int tickTime = 10;

    public void mainloop() {
        long past = System.currentTimeMillis();
        long accumulator = 0;

        while (running) {
            long delta = System.currentTimeMillis() - past;
            accumulator += delta;

            while (accumulator >= tickTime) {
                tick(tickTime / 1000);
                accumulator -= tickTime;
            }
        }
    }

    private void tick(float delta) {
        handleCollision(delta);
        handleInput(delta);
    }

    private void handleCollision(float delta) {
        // todo.
    }

    private void handleInput(float delta) {
        // todo.
    }

    @Override
    public DrawPayload getDrawPayload() {
        return null;
    }
}
