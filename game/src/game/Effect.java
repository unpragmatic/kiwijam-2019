package game;

import game.objects.Ball;

public class Effect {

    public enum Name {
        WARP, SPLIT, LIFE_UP, SPEED_UP
    }

    public static void applyEffect(Name effect, Game game, Ball colidee){
        switch (effect){

            case WARP:
                colidee.dx = (float) (4000 / Math.sqrt((colidee.dx * colidee.dx) + (colidee.dy * colidee.dy))
                        * (colidee.dx));
                colidee.dy = (float) (4000 / Math.sqrt((colidee.dx * colidee.dx) + (colidee.dy * colidee.dy))
                        * (colidee.dy));
                break;

            case SPLIT:
                float original_dx = colidee.dx;
                float original_dy = colidee.dy;

                Ball secondBall = new Ball(colidee.x, colidee.y);

                colidee.dx = (float) Math.sin(0.26) * original_dx;
                colidee.dy = (float) Math.sin(-0.26) * original_dy;
                game.ballsToAdd.add(secondBall);
                break;

            case LIFE_UP:
                game.increaseLife(1, 3);
                game.increaseLife(1, 3);
                break;

            case SPEED_UP:
                game.
        }
    }


}
