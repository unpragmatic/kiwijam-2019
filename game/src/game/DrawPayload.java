package game;

import processing.Drawable;

import java.util.List;

public class DrawPayload {
    public final List<Drawable> drawables;

    public DrawPayload(List<Drawable> drawables) {
        this.drawables = drawables;
    }
    // todo: Add camera.
}
