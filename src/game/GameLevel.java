package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Rabbit player;
    private SoundClip dooropen;
    
    //method to get the player which returns the rabbit
    public Rabbit getPlayer() {
        return player;
    }
    
    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        //initialise and declare the player i.e. the rabbit
        player = new Rabbit(this,50);
        player.setPosition(startPosition());
        //intialise and declare door
        Door door = new Door(this);
        //set position of the door
        door.setPosition(doorPosition());
       
        
      //add the collision listener with the door so something occurs when door hits the player
        door.addCollisionListener(new DoorListener(game,dooropen));
    }
    
    /** The initial position of the player. */
    public abstract Vec2 startPosition();
    
   
    
    /** The position of the exit door. */
    public abstract Vec2 doorPosition();
    
    /** Is this level complete? */
    public abstract boolean isCompleted();
}
