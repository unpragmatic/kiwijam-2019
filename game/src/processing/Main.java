package processing;

import game.DrawPayload;
import game.Game;
import processing.core.PApplet;

public class Main extends PApplet {
    private static Game game = null;

    public void settings() {
        size(400, 400);
    }

    public void draw() {
        DrawPayload payload = game.getDrawPayload();

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
