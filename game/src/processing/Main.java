package processing;

import game.DrawPayload;
import game.Game;
import game.input.MouseDragEvent;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Main extends PApplet {
    private static Game game = null;

    public ResourceLoader resourceLoader;

    public void settings() {
        size(1000, 600);
    }

    public void setup(){
        resourceLoader = new ResourceLoader(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        game.input.setKey(event.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        game.input.setKey(event.getKeyCode(), false);
    }


    private int lastMousePressX = 0;
    private int lastMousePressY = 0;

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);
        lastMousePressX = event.getX();
        lastMousePressY = event.getY();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        super.mouseReleased(event);
        float start_x = game.getCamera().pixel_to_world_x(lastMousePressX, width);
        float start_y = game.getCamera().pixel_to_world_y(lastMousePressY, height);
        float end_x = game.getCamera().pixel_to_world_x(event.getX(), width);
        float end_y = game.getCamera().pixel_to_world_y(event.getY(), height);
        game.input.broadcastMouseDrag(new MouseDragEvent(start_x, start_y, end_x, end_y));
    }

    public void draw() {
        DrawPayload payload = game.getDrawPayload();
        if (payload == null) { return; }

        noStroke();
        background(2434341);

        for (Drawable d : payload.drawables){
            d.draw(this, payload.camera, resourceLoader);
        }

    }

    public static void main(String[] args) {
        Main.game = new Game();

        PApplet.main("processing.Main");
        Main.game.mainloop();
    }
}
