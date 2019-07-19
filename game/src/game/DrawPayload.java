package game;

import processing.Drawable;
import processing.core.PApplet;

import java.util.List;

public class DrawPayload {
    public final List<Drawable> drawables;
    public final Camera camera;

    public DrawPayload(List<Drawable> drawables) {
        this.drawables = drawables;
        this.camera = new Camera();
    }

}

class Camera {

    public float translate_x;
    public float translate_y;

    public Camera(){
        this.translate_x = 0;
        this.translate_y = 0;
    }

}