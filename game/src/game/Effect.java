package game;

import game.objects.Ball;

public class Effect {

    public enum Name {
        SPEED
    }

    public static void applyEffect(Name effect, Game game, Ball colidee){
        switch (effect){

            case SPEED:
                System.out.println("Gotta go fast!");
                colidee.dx = (float) (4000 / Math.sqrt((colidee.dx * colidee.dx) + (colidee.dy * colidee.dy)) * (colidee.dx));
                colidee.dy = (float) (4000 / Math.sqrt((colidee.dx * colidee.dx) + (colidee.dy * colidee.dy)) * (colidee.dy));
        }
    }

}
