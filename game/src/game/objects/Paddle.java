package game.objects;

import game.Camera;
import processing.Drawable;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 100;

    public float x;
    public float y;

    public float world_width = 35f;
    public float world_height = 200f;

    public Paddle(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(PApplet d, Camera c) {

        int local_x = c.world_to_local_x(x);
        int local_y = c.world_to_local_y(y);

        int width = c.world_to_local_x(world_width);
        int height = c.world_to_local_y(world_height);

        d.rect(local_x, local_y, width, height);
    }
}
