package processing;

import game.Effect;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {

    private Map<String, PImage> imageMap = new HashMap<>();

    public ResourceLoader(PApplet applet){
        loadImages(applet);
    }

    private void loadImages(PApplet applet){
        for (Effect.Name effectName : Effect.Name.values()){
            imageMap.put(effectName.toString(), applet.loadImage(effectName.toString() + ".png"));
        }
    }

    public PImage getImage(String imageName){
        return imageMap.get(imageName);
    }

}
