package game.input;

public class MouseDragEvent {
    public final float startX;
    public final float startY;
    public final float endX;
    public final float endY;

    public MouseDragEvent(float x, float y, float endX, float endY) {
        this.startX = x;
        this.startY = y;
        this.endX = endX;
        this.endY = endY;
    }
}
