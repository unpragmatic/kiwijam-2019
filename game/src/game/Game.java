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

    public void mainloop() {
        
    }

    private void tick(float delta) {
        handleCollision();
        handleInput();
    }

    private void handleCollision() {
        // todo.
    }

    private void handleInput() {
        // todo.
    }

    @Override
    public DrawPayload getDrawPayload() {
        return null;
    }
}
