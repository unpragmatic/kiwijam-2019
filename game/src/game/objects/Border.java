package game.objects;

import game.Camera;
import game.Game;
import processing.Drawable;
import processing.ResourceLoader;
import processing.core.PApplet;

public class Border implements Drawable {
    public float x;
    public float y;

    public static final float DEFAULT_WIDTH = Game.ROOM_WIDTH;
    public static final float DEFAULT_HEIGHT = 10f;

    public Border(float x, float y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {
        int pixel_x = c.world_to_pixel_x(x, d.width);
        int pixel_y = c.world_to_pixel_y(y, d.height);

        int pixel_width = c.world_to_pixel_width(DEFAULT_WIDTH, d.width);
        int pixel_height = c.world_to_pixel_y(DEFAULT_HEIGHT, d.height);

        d.fill(110, 110, 110);
        d.rect(pixel_x, pixel_y, pixel_width, pixel_height);
    }
}
