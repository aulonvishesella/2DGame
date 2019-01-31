/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import java.awt.Color;
import org.jbox2d.common.Vec2;
/**
 * Level 4 of the game
 */
public class Level4 extends GameLevel {
    //declaring all variables, initialsing most
    private static final int NUM_CARROTS = 1;
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
         
        //make the wall to act like a barrier.
        Shape platform3Shape = new BoxShape(200, 0.2f);
        Body platform5 = new StaticBody(this, platform3Shape);
        platform5.setPosition(new Vec2(-13f, -6));
        platform5.setAngleDegrees(90);

        Body platform6 = new StaticBody(this, platform3Shape);
        platform6.setPosition(new Vec2(13f, -6));
        platform6.setAngleDegrees(90);

        // make some platforms
        Shape platformShape = new BoxShape(3, 0.5f);
        Shape platformShape2 = new BoxShape(1f, 0.5f);

        Body platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(5, 0f));

        Body platform3 = new StaticBody(this, platformShape2);
        platform3.setPosition(new Vec2(-11f, 8.3f));

        Body platform4 = new StaticBody(this, platformShape2);
        platform4.setPosition(new Vec2(-6f, 4));
        
        //declare and intialise the fox.
        Fox foxShape = new Fox(this);
        foxShape.setPosition(new Vec2(10, -12));
        //declare and intialise the farmer.
        Farmer farmerShape = new Farmer(this);
        farmerShape.setPosition(new Vec2(-10, -6));
        //declare and intialise the coyote.
        Coyote coyoteShape = new Coyote(this);
        coyoteShape.setPosition(new Vec2(3.3f, 0));
        //declare and intialise the raccoon.
        Raccoon raccoonShape = new Raccoon(this);
        raccoonShape.setPosition(new Vec2(6.8f, 0));
         
        
        //aray to set the number of carrot(s) on the map.
        for (int i = 0; i < NUM_CARROTS; i++) {

            Carrot carrot = new Carrot(this);
            carrot.setPosition(new Vec2(5, 1.3f));

            carrot.addCollisionListener(new Pickup(getPlayer(), game, pickupClip));
        } 
        
        //collision listeners added 

        foxShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        farmerShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        coyoteShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        foxShape.addCollisionListener(new FarmerFoxIntersection(farmerShape));
        raccoonShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        platform5.addCollisionListener(new FoxWallCollision(foxShape));//set body platform 5 when calling FoxWallCollision class
        platform6.addCollisionListener(new FoxWallCollision2(foxShape));//set body platform 6 when calling FoxWallCollision2 class
        platform6.addCollisionListener(new FarmerWallCollision(farmerShape));//set body platform 5 when calling FoxWallCollision class

    }

    @Override
    //starting pisition of the player
    public Vec2 startPosition() {
        return new Vec2(0, -10);
    }
    //starting position of the door
    @Override
    public Vec2 doorPosition() {
        return new Vec2(-11f, 10);
    }
    //checks to see the carrot count is equal to the number of carrots on the map.
    @Override
    public boolean isCompleted() {
        return getPlayer().getCarrotCount() == NUM_CARROTS;
    }

}
