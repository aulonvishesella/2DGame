package game;

import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Collision listener that allows the rabbit to collect carrots
 */
public class Pickup implements CollisionListener {

    private Rabbit rabbitShape;
    private Game game;
    private SoundClip pickupClip;

    /**
     * @param rabbitShape The Rabbit
     * @param Game The Game
     * @param pickupClip The SoundClip
     */
    public Pickup(Rabbit rabbitShape, Game game, SoundClip pickupClip) {
        this.rabbitShape = rabbitShape;
        this.game = game;
        this.pickupClip = pickupClip;
    }

    /**
     * Handle collisions
     *
     * @param e description of the collision
     * @param pickupClip gets played
     *
     */
    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() == rabbitShape) {
            rabbitShape.incrementcarrotCount();
            e.getReportingBody().destroy();
            game.incrementCount();
            game.decrementCount();
            try {
                pickupClip = new SoundClip("data/pickup.wav");
                pickupClip.play();
                pickupClip.setVolume(0.4f);

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
        }
    }

}
