package game.objects;

import game.Camera;
import processing.Drawable;
import processing.core.PApplet;

public class Ball implements Drawable {
    
    public float x;
    public float y;
    public float dx;
    public float dy;
    public float radius = 12.5f;

    public Ball(float x, float y){
        this.x = x;
        this.y = y;
        System.out.println("Ball created with world X pos: " + x + " and world y pos: " + y);
    }

    @Override
    public void draw(PApplet d, Camera c) {

        int local_x = c.world_to_pixel_x(x, d.width);
        int local_y = c.world_to_pixel_y(y, d.height);
        int local_size = c.world_to_pixel_width(radius*2, d.width);

        d.fill(240, 240, 240);
        d.circle(local_x, local_y, local_size);
    }
}
