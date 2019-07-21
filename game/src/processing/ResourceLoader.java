package processing;

import game.Effect;
import game.objects.Powerup;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

import java.io.File;
import java.io.IOException;
import java.io.PipedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ResourceLoader {

    private Map<String, PImage> imageMap = new HashMap<>();
    private Map<String, SoundFile> soundFiles = new HashMap<>();

    public ResourceLoader(PApplet applet){
        loadAssets(applet);
    }

    private void loadAssets(PApplet applet) {
        try (Stream<Path> files = Files.walk(Paths.get("./src/data"))) {
            files.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .forEach(s -> {
                        System.out.println(s);
                        if (s.endsWith(".png")) {
                            imageMap.put(s.toString(), applet.loadImage(s.toString()));
                        } else if (s.endsWith(".mp3")) {
                            soundFiles.put(s.toString(), new SoundFile(applet, s.toString()));
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException("Couldn't load files");
        }
    }

    public PImage getImage(String imageName){
        return imageMap.get(imageName);
    }

    public SoundFile getSoundFile(String filename) {
        return soundFiles.get(filename);
    }
}
