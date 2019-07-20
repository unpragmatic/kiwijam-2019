package game.objects;

import game.Camera;
import processing.Drawable;
import processing.core.PApplet;

public class Ball implements Drawable {
    
    public float x;
    public float y;
    public float dx;
    public float dy;
    public float radius = 25f;

    public Ball(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(PApplet d, Camera c) {

        /**
        int local_x = c.world_to_local_x(x);
        int local_y = c.world_to_local_y(y);
        int size = c.world_to_local_x(radius*2);

        d.circle(local_x, local_y, local_size);
         **/
    }
}
