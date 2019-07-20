package game.objects;

import game.Camera;
import game.Effect;
import processing.Drawable;
import processing.core.PApplet;
import processing.core.PImage;

public class Powerup implements Drawable {

    public float x;
    public float y;

    Effect.Name type;
    PImage img;

    public Powerup(float x, float y, Effect.Name type) {
        this.x = x;
        this.y = y;
        this.type = type;

    }

    @Override
    public void draw(PApplet d, Camera c) {
        PImage img = d.loadImage(type.toString() + ".png");
        int pixel_x = c.world_to_pixel_x(x, d.width);
        int pixel_y = c.world_to_pixel_y(y, d.height);

        d.image(img, pixel_x, pixel_y);
    }


}
