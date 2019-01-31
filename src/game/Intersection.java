/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.UserView;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * Class Intersection that deals with intersection with enemies
 */
public class Intersection implements CollisionListener {

    private Rabbit rabbitShape;
    private Game game;
    private SoundClip lifeClip;
//      private Game game;
//      private GameWorld world;

    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private UserView view;

    /**
     * @param rabbitShape The Rabbit
     * @param Game The Game
     * @param lifeClip The SoundClip
     */

    public Intersection(Rabbit rabbitShape, Game game, SoundClip lifeClip) {
        this.rabbitShape = rabbitShape;
        this.game = game;
        this.lifeClip = lifeClip;
    }

    /**
     * Handle collisions
     *
     * @param e description of the collision
     * @param lifeclip gets played
     */
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == rabbitShape) {
            rabbitShape.decrementLives();
//           
            e.getReportingBody().destroy();
            game.decrementHealth();
            //try and catch method to grab the sound clip
            try {
                lifeClip = new SoundClip("data/lifelost.wav");
                lifeClip.play();
                lifeClip.setVolume(0.4f);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }

        }

    }

}
