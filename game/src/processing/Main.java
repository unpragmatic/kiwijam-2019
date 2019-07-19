package processing;

import game.DrawPayload;
import game.Game;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Main extends PApplet {
    private static Game game = null;

    public void settings() {
        size(400, 400);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        game.input.setKey(event.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        game.input.setKey(event.getKeyCode(), false);
    }

    public void draw() {
        DrawPayload payload = game.getDrawPayload();
        if (payload == null) { return; }

        for (Drawable d : payload.drawables){
            d.draw(this);
        }

        background(50, 50, 50);
        square(50, 50, 100);
        text("Hello world", 50, 50);
    }

    public static void main(String[] args) {
        Main.game = new Game();


        PApplet.main("processing.Main");
        Main.game.mainloop();
    }
}
