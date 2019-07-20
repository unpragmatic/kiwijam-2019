package game.objects;

import game.Camera;
import game.Game;
import processing.Drawable;
import processing.ResourceLoader;
import processing.core.PApplet;

public class UI implements Drawable {
    private final Game game;

    public UI(Game game) {
        this.game = game;
    }

    private void drawHealthBars(PApplet d, Camera c) {
        int padding = (int) ((d.width / 2) * 0.05);
        int bar_width = (int) ((d.width /2 ) * 0.70);
        int bar_height = (int) (d.height * 0.05);
        int seperator = (int) ((d.width /2 ) * 0.25);


        d.fill(67, 67, 67);
        d.rect(padding, padding, bar_width, bar_height);
        d.rect(d.width/2 + seperator, padding, bar_width, bar_height);

        d.fill(175, 4, 4);
        d.rect(padding, padding, bar_width, bar_height);
        d.rect(d.width/2 + seperator, padding, bar_width, bar_height);
    }

    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {
        drawHealthBars(d, c);
    }
}
