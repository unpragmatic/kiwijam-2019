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
        int bar_height = (int) (d.height * 0.035);
        int seperator = (int) ((d.width /2 ) * 0.25);


        d.fill(67, 67, 67);
        d.rect(padding, padding, bar_width, bar_height);
        d.rect(d.width/2 + seperator, padding, bar_width, bar_height);

        float hp_lost = bar_width * ((game.player_0_life_total - game.player_0_life) / (float)game.player_0_life_total);
        d.fill(175, 4, 4);
        d.rect(padding, padding, bar_width - hp_lost, bar_height);
        hp_lost = bar_width * ((game.player_1_life_total - game.player_1_life) / (float)game.player_1_life_total);
        d.rect(d.width/2 + seperator + hp_lost, padding, bar_width - hp_lost, bar_height);
    }

    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {
        drawHealthBars(d, c);
    }
}
