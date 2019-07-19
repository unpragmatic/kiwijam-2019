package game.objects;

import processing.Drawable;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 100;
    public float x;
    public float y;
    public float width = 35;
    public float height = 200;

    public Paddle(float x, float y){
        this.x = x;
        this.y= y;
    }

    @Override
    public void draw(PApplet d) {
        d.rect(x, y, width, height);
    }
}
