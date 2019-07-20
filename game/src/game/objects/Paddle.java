package game.objects;

import game.Camera;
import processing.Drawable;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 100;

    public float x;
    public float y;

    public static final float default_width = 35f;
    public static final float default_height = 200f;

    public float width;
    public float height;

    public Paddle(float x, float y){
        this.x = x;
        this.y = y;
        this.width = Paddle.default_width;
        this.height = Paddle.default_height;
    }


    @Override
    public void draw(PApplet d, Camera c) {

        int pixel_x = c.world_to_pixel_x(x, d.width);
        int pixel_y = c.world_to_pixel_y(y, d.height);

        int pixel_width = c.world_to_pixel_x(width, d.width);
        int pixel_height = c.world_to_pixel_y(height, d.height);

        d.rect(pixel_x, pixel_y, pixel_width, pixel_height);
    }
}
