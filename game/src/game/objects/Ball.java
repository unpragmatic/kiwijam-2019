package game.objects;

import processing.Drawable;
import processing.core.PApplet;

public class Ball implements Drawable {

    private float x;
    private float y;
    private float size = 75;

    public Ball(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(PApplet d) {
        d.circle(x, y, size);
    }
}
