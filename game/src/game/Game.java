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

    private final boolean running = true;
    private final int tickTime = 1000;

    public void mainloop() {
        long past = System.currentTimeMillis();
        long accumulator = 0;

        while (running) {
            long delta = System.currentTimeMillis() - past;
            past = System.currentTimeMillis();
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
        input.onKeys.entrySet().stream()
                .filter(e -> e.getValue())
                .forEach(e -> System.out.println(e.getKey()));
    }

    @Override
    public DrawPayload getDrawPayload() {
        return null;
    }
}
