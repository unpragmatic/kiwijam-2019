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

    public Paddle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public void draw(PApplet d, Camera c) {

        int local_x = c.world_to_local_x(x);
        int local_y = c.world_to_local_y(y);

        int local_width = c.world_to_local_x(width);
        int local_height = c.world_to_local_y(height);

        d.rect(local_x, local_y, local_width, local_height);
    }
}
