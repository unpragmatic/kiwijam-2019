package game;

import processing.Drawable;
import processing.core.PApplet;

import java.util.List;

public class DrawPayload {

    public final List<Drawable> drawables;
    public Camera camera;

    public DrawPayload(List<Drawable> drawables) {
        this.drawables = drawables;
        this.camera = new Camera();
    }

}

