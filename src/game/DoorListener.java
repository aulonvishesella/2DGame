package game;

import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if th e current level is complete the game is advanced to the next level. 
 */
public class DoorListener implements CollisionListener {
    private Game game;
    private SoundClip dooropen;
    
    public DoorListener(Game game, SoundClip dooropen) {
        this.game = game;
        this.dooropen=dooropen;
    }
    /**
     * Handle collisions
     *
     * @param e description of the collision
     * @param dooropen plays music
     */
    @Override
    public void collide(CollisionEvent e) {
        Rabbit player = game.getPlayer();
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
//            System.out.println("Going to next level...");
            game.goNextLevel();
             try {//if the player jumps, play that specific clip
                    dooropen = new SoundClip("data/doorlock.wav");
                    dooropen.play();
                    dooropen.setVolume(0.99f);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                    System.out.println(f);
                }
            
        }
    }
}
