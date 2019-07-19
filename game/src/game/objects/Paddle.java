package game.objects;

import processing.Drawable;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 100;

    private float x;
    private float y;

    private float width;
    private float height;

    public Paddle(float x, float y, float width, float height){
        this.x = x;
        this.y= y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(PApplet d) {
        d.rect(x, y, width, height);
    }
}
