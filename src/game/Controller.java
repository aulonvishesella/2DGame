package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Key handler to control an Walker.
 */
public class Controller extends KeyAdapter {

    private static final float JUMPING_SPEED = 18;
    private static final float WALKING_SPEED = 4;
    private Walker body;
    private SoundClip jumpsound;

    /**
     * Specify the variable body and jumpsound to its classes
     *
     */
    public Controller(Walker body, SoundClip jumpsound) {
        this.body = body;
        this.jumpsound = jumpsound;

    }

    /**
     * Handle key press events for walking and jumping.
     *
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_W) { // SPACE = jump

            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
                try {//if the player jumps, play that specific clip
                    jumpsound = new SoundClip("data/jump.wav");
                    jumpsound.play();
                    jumpsound.setVolume(0.4f);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                    System.out.println(f);
                }

            }
        } else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED);
            body.removeAllImages(); // remove the image
            body.addImage(new BodyImage("data/rabbitrotate.png", 2.5f)); //add a new image

        } else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED);
            body.removeAllImages(); // remove image
            body.addImage(new BodyImage("data/rabbit.png", 2.5f)); //add image

        }
    }

    /**
     * Handle key release events (stop walking).
     *
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();

        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();

        } else if (code == KeyEvent.VK_W) {
            jumpsound.stop();
        }

    }

    /**
     * Handle where the body is positioned
     *
     * @param Body set to body
     */
    public void setBody(Walker body) {
        this.body = body;
    }

}
