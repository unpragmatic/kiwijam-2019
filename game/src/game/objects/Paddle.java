package game.objects;

import processing.Drawable;
import processing.core.PApplet;

public class Paddle implements Drawable {
    public float max_speed = 100;

    private float x;
    private float y;

    private float width = 25;
    private float height = 100;

    public Paddle(float x, float y){
        this.x = x;
        this.y= y;
    }

    @Override
    public void draw(PApplet d) {
        d.rect(x, y, width, height);
    }
}
