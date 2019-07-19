package game.objects;

import game.Camera;
import processing.Drawable;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 100;

    public float world_x;
    public float world_y;

    public float world_width = 35f;
    public float world_height = 200f;

    public Paddle(float world_x, float world_y){
        this.world_x = world_x;
        this.world_y = world_y;
    }

    @Override
    public void draw(PApplet d, Camera c) {

        int x = c.world_to_local_x(world_x);
        int y = c.world_to_local_y(world_y);

        int width = c.world_to_local_x(world_width);
        int height = c.world_to_local_y(world_height);

        d.rect(x, y, width, height);
    }
}
