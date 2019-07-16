package game;

import processing.core.PApplet;

public class Main extends PApplet {
    public void settings() {
        size(400, 400);
    }

    public void draw() {
        background(50, 50, 50);
        square(50, 50, 100);
        text("Hello world", 50, 50);
    }

    public static void main(String[] args) {
        PApplet.main("game.Main");
    }
}
