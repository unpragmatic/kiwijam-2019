package game.objects;

import game.Camera;
import processing.Drawable;
import processing.ResourceLoader;
import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ball implements Drawable {
    
    public float x;
    public float y;
    public float dx;
    public float dy;
    public float radius = 12.5f;

    public ArrayList<int[]> history = new ArrayList<>();

    public Ball(float x, float y){
        this.x = x;
        this.y = y;
        // System.out.println("Ball created with world X pos: " + x + " and world y pos: " + y);
    }

    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {

        int local_x = c.world_to_pixel_x(x, d.width);
        int local_y = c.world_to_pixel_y(y, d.height);
        int local_size = c.world_to_pixel_width(radius*2, d.width);

        d.fill(240, 240, 240);

        if (history.size() > 40) history.remove(0);
        history.add(new int[]{local_x, local_y});

        d.circle(local_x, local_y, local_size);

        for (int i = 0; i < history.size(); i++){
            d.fill(d.color(240, 240, 240), (i / (float)history.size()) * 255);
            float size = (i / (float)history.size()) * local_size;
            d.circle(history.get(i)[0], history.get(i)[1], size);
        }

    }
}
