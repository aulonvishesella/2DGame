


package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.Color;

/**
 * Level 1 of the game
 */
public class Level1 extends GameLevel {

    private static final int NUM_CARROTS = 3; // set variable to equal 3
    private Game game; //declare variable game
    private SoundClip pickupClip; //declare variable
     private SoundClip powerupClip; //declare variable

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);
        this.game = game;

        // make the ground
        Shape groundShape = new BoxShape(100, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -12f));
        ground.setFillColor(Color.green);
         //make the walls which are not seen but act as barriers
         Shape platform3Shape = new BoxShape(100,0.2f);
         Body platform3 = new StaticBody(this, platform3Shape);
         platform3.setPosition(new Vec2(-13f, -6));
         platform3.setAngleDegrees(90);
         
         Shape platform4Shape = new BoxShape(100,0.2f);
         Body platform4 = new StaticBody(this, platform3Shape);
         platform4.setPosition(new Vec2(13f, -6));
         platform4.setAngleDegrees(90);
        
 
         
        //array to set the carrots in the world.
        for (int i = 0; i < NUM_CARROTS; i++) {
            Carrot carrot = new Carrot(this);
            carrot.setPosition(new Vec2(i * 4 - 8, -7));
            carrot.addCollisionListener(new Pickup(getPlayer(),game,pickupClip));
        }
        
    }
    //set position of the player
    @Override
    public Vec2 startPosition() {
        return new Vec2(-10, -10);   //position of rabbit
    }
    //set the position of the door
    @Override
    public Vec2 doorPosition() {
        return new Vec2(-11.9f, -10f);
    } 
    //method that checks if the carrot count is the same as the number of carrots put on the map.
    @Override
    public boolean isCompleted() {
        return getPlayer().getCarrotCount() == NUM_CARROTS;
    }
    
}
