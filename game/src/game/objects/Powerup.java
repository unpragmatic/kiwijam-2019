package game.objects;

import game.Camera;
import game.Effect;
import game.Game;
import processing.Drawable;
import processing.ResourceLoader;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class Powerup implements Drawable {

    public static final float IMAGE_WIDTH = 50f;
    public static final float IMAGE_HEIGHT = 50f;

    public float x;
    public float y;

    public float width;
    public float height;

    public Effect.Name type;

    public Powerup(float x, float y, float width, float height, Effect.Name type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static Powerup createRandomPowerup(float x1, float y1, float x2, float y2){
        Random r = new Random();

        Effect.Name type = Effect.Name.values()[r.nextInt(Effect.Name.values().length)];

        float xPos = (r.nextFloat() * (x2 - x1)) + x1;
        float yPos = (r.nextFloat() * (y2 - y1)) + y1;

        return new Powerup(xPos, yPos, IMAGE_WIDTH, IMAGE_HEIGHT, type);
    }

    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {
        PImage img = r.getImage(type.toString() + ".png");
        int pixel_x = c.world_to_pixel_x(x, d.width);
        int pixel_y = c.world_to_pixel_y(y, d.height);

        d.image(img, pixel_x, pixel_y, IMAGE_WIDTH, IMAGE_HEIGHT);
    }


}
