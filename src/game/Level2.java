/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.*;
import java.awt.Color;
import org.jbox2d.common.Vec2;
/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel{
    
    private static final int NUM_CARROTS2 = 3; //declaring and initialising variable
    private static final int NUM_CARROTS3 = 3;//declaring and initialising variable
    private SoundClip pickupClip;
    private SoundClip lifeLost;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        // make the ground
        Shape groundShape = new BoxShape(13, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -12f));
        ground.setFillColor(Color.green);
       //wall is set which is unseen but acts as a barrier.
        Shape platform3Shape = new BoxShape(100, 0.2f);
        Body platform5 = new StaticBody(this, platform3Shape);
        platform5.setPosition(new Vec2(-13f, -6));
        platform5.setAngleDegrees(90);
       
        Shape platform4Shape = new BoxShape(100, 0.2f);
        Body platform4 = new StaticBody(this, platform3Shape);
        platform4.setPosition(new Vec2(13f, -6));
        platform4.setAngleDegrees(90);

        // make some platforms
        Shape platformShape = new BoxShape(4, 0.5f);
        Shape platformShape2 = new BoxShape(1f, 0.5f);
        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-7, 5.5f));
        Body platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(5, -2.5f));
        Body platform3 = new StaticBody(this, platformShape2);
        platform3.setPosition(new Vec2(10.1f, 8.6f));
       //fox declared and initiliased
        Fox foxShape = new Fox(this);
        foxShape.setPosition(new Vec2(10, -12));
        
//number of carrots are set and placed on the world
       
        

        for (int i = 0; i < NUM_CARROTS2; i++) {
            Carrot carrot = new Carrot(this);
            carrot.setPosition(new Vec2(i * 2 + 3.4f, -1.1f));
            carrot.addCollisionListener(new Pickup(getPlayer(), game, pickupClip));
        }
        for (int i = 0; i < NUM_CARROTS3; i++) {
            Carrot carrot = new Carrot(this);
            carrot.setPosition(new Vec2(i * 2 - 9, 6.7f));
            carrot.addCollisionListener(new Pickup(getPlayer(), game, pickupClip));
        }
        //allows the intersection class to be activated for both fox and farmer.
           
        foxShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        platform5.addCollisionListener(new FoxWallCollision(foxShape)); //set body platform 5 when calling FoxWallCollision class
        platform4.addCollisionListener(new FoxWallCollision2(foxShape));//set body platform 6 when calling FoxWallCollision2 class

    }

    @Override
    //set the position of the player
    public Vec2 startPosition() {
        return new Vec2(-12, -10);
    }
    //set the position of the door
    @Override
    public Vec2 doorPosition() {
        return new Vec2(10.3f, 10);
    }
    //method to check if the carrot count is equal to number of carrots put on the map.
    @Override
    public boolean isCompleted() {
        return getPlayer().getCarrotCount() ==  NUM_CARROTS2 + NUM_CARROTS3;
    }
}
