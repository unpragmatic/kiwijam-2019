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
import java.util.Objects;
import java.util.stream.Stream;

public class ResourceLoader {
    private static ResourceLoader resourceLoader = null;
    private static PApplet applet = null;
    private Map<String, PImage> imageMap = new HashMap<>();
    private Map<String, SoundFile> soundFiles = new HashMap<>();

    public ResourceLoader(PApplet applet){
        if (ResourceLoader.resourceLoader == null) {
            ResourceLoader.resourceLoader = this;
        }
        if (ResourceLoader.applet == null) {
            ResourceLoader.applet = applet;
        }
        loadAssets(applet);
    }

    private static int counter = 0;
    public static void playSound(String filename) {
        System.out.println(resourceLoader.soundFiles.keySet().toString());
        if (resourceLoader != null && applet != null) {
            SoundFile file = resourceLoader.soundFiles.get(filename);

            if (file != null) { file.play(); }
        } else {
            System.out.println("Couldn't play sound");
        }
    }

    public static void playBeep() {
        if (counter % 2 == 0) {
            playSound("beep1.mp3");
        } else {
            playSound("beep2.mp3");
        }
        counter += 1;
    }

    private void loadAssets(PApplet applet) {
        try (Stream<Path> files = Files.walk(Paths.get("./src/data"))) {
            files.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Objects::toString)
                    .forEach(s -> {
                        System.out.println(s);
                        if (s.contains(".png")) {
                            imageMap.put(s, applet.loadImage(s));
                        } else if (s.contains(".mp3")) {
                            soundFiles.put(s, new SoundFile(applet, s));
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
