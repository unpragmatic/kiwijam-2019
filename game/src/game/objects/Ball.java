package game.objects;

import game.Camera;
import processing.Drawable;
import processing.core.PApplet;

public class Ball implements Drawable {
    public float world_x;
    public float world_y;
    public float world_size = 50f;

    public Ball(float world_x, float world_y){
        this.world_x = world_x;
        this.world_y = world_y;
    }

    @Override
    public void draw(PApplet d, Camera c) {

        int x = c.world_to_local_x(world_x);
        int y = c.world_to_local_y(world_y);
        int size = c.world_to_local_x(world_size);

        d.circle(x, y, size);
    }
}
