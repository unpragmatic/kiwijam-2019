package game.objects;

import game.Camera;
import processing.Drawable;
import processing.ResourceLoader;
import processing.core.PApplet;

import processing.core.PConstants;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.Queue;

public class Ball implements Drawable {
    public PShape[] shapes = new PShape[40];
    public float x;
    public float y;
    public float dx;
    public float dy;
    public float radius = 12.5f;

    private float deltaSum = 0f;

    public ArrayList<int[]> history = new ArrayList<>();
    private float[][] buffer = new float[10][];
    private int offset = 0;

    public Ball(float x, float y){
        this.x = x;
        this.y = y;
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = new float[]{x, y};
        }
        // System.out.println("Ball created with world X pos: " + x + " and world y pos: " + y);
    }

    public void tick(float delta){
        deltaSum += delta;

        if (deltaSum >= .1){
            buffer[offset % buffer.length][0] = x;
            buffer[offset % buffer.length][1] = y;
            offset++;
        }
    }

    @Override
    public void draw(PApplet d, Camera c, ResourceLoader r) {
        int local_x = c.world_to_pixel_x(x, d.width);
        int local_y = c.world_to_pixel_y(y, d.height);
        int local_size = c.world_to_pixel_width(radius*2, d.width);

        d.fill(240, 240, 240);


        d.circle(local_x, local_y, local_size);

        int to = d.color(240, 240, 240);
        int from = d.color(2434341);
        for (int i = 0; i < buffer.length; i++) {
            float[] point = buffer[(i + offset) % buffer.length];

            if (shapes[i] == null) {
                float size = (i / (float)buffer.length) * local_size;
                shapes[i] = d.createShape(PConstants.ELLIPSE, 0, 0, size, size);
            }
            d.fill(d.lerpColor(from, to, (i / (float)buffer.length)));
            d.shape(shapes[i], c.world_to_pixel_x(point[0], d.width), c.world_to_pixel_y(point[1], d.height));
        }

    }
}
