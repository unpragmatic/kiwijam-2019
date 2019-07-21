package game.objects;

import game.Camera;
import processing.Drawable;
import processing.ResourceLoader;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 500;

    public float x;
    public float y;
    public float dx;
    public float dy;

    public static final float DEFAULT_WIDTH = 20f;
    public static final float DEFAULT_HEIGHT = 200f;

    public float width;
    public float height;

    public Paddle(float x, float y){
        this.x = x;
        this.y = y;
        this.width = Paddle.DEFAULT_WIDTH;
        this.height = Paddle.DEFAULT_HEIGHT;
    }


    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {

        int pixel_x = c.world_to_pixel_x(x, d.width);
        int pixel_y = c.world_to_pixel_y(y, d.height);

        int pixel_width = c.world_to_pixel_width(width, d.width);
        int pixel_height = c.world_to_pixel_y(height, d.height);

        d.fill(173, 173, 173);
        d.rect(pixel_x, pixel_y, pixel_width, pixel_height);
    }

}
