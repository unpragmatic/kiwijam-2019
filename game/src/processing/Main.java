package processing;

import game.DrawPayload;
import game.Game;
import game.input.MouseDragEvent;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Main extends PApplet {
    private static Game game = null;

    //todo - better place for these?
    public static int camera_width = 400;
    public static int camera_height = 400;

    public void settings() {
        size(camera_width, camera_height);
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
        float start_x = game.getDrawPayload().camera.local_to_world_x(lastMousePressX);
        float start_y = game.getDrawPayload().camera.local_to_world_y(lastMousePressY);
        float end_x = game.getDrawPayload().camera.local_to_world_x(event.getX());
        float end_y = game.getDrawPayload().camera.local_to_world_x(event.getY());
        game.input.broadcastMouseDrag(new MouseDragEvent(start_x, start_y, end_x, end_y));
    }

    public void draw() {
        DrawPayload payload = game.getDrawPayload();
        if (payload == null) { return; }

        background(0);

        translate(payload.camera.translate_x, payload.camera.translate_y);

        for (Drawable d : payload.drawables){
            d.draw(this, payload.camera);
        }

    }

    public static void main(String[] args) {
        Main.game = new Game();


        PApplet.main("processing.Main");
        Main.game.mainloop();
    }
}
