package processing;

import game.Effect;
import game.objects.Powerup;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.PipedWriter;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {

    private Map<String, PImage> imageMap = new HashMap<>();

    public ResourceLoader(PApplet applet){
        loadImages(applet);
    }

    private void loadImages(PApplet applet){
        for (Effect.Name effectName : Effect.Name.values()){
            PImage img = applet.loadImage(effectName.toString() + ".png");
            //img.resize(Powerup.IMAGE_WIDTH, Powerup.IMAGE_HEIGHT);
            imageMap.put(effectName.toString(), img);
        }
    }

    public PImage getImage(String imageName){
        return imageMap.get(imageName);
    }

}
