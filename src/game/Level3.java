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
 * Level 3 of the game
 */
public class Level3 extends GameLevel {
    
    private static final int NUM_CARROTS = 11;
    private static final int NUM_CARROTS2 = 2;
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
        
        //walls that act as barriers.
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
        Shape platformShape2 = new BoxShape(4.3f, 0.5f);
        //platforms set on the map
        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, 5.5f));

        Body platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(5, -2.5f));

        Body platform3 = new StaticBody(this, platformShape2);
        platform3.setPosition(new Vec2(7f, 8.3f));
        
        
        //declare and initiliase the fox class
        Fox foxShape = new Fox(this);
        foxShape.setPosition(new Vec2(10, -12));
        //declare and initiliase the farmer class
        Farmer farmerShape = new Farmer(this);
        farmerShape.setPosition(new Vec2(-10, -12));
        //declare and initiliase the bear class
        Bear bearShape = new Bear(this);
        bearShape.setPosition(new Vec2(5f, 9));
        //declare and initiliase the coyote class
        Coyote coyoteShape = new Coyote(this);
        coyoteShape.setPosition(new Vec2(2, 2));
       //declare and initiliase the raccoon class
        Raccoon raccoonShape = new Raccoon(this);
        raccoonShape.setPosition(new Vec2(-11, 10));
       //declare and initiliase the wolf class
        Wolf wolfShape = new Wolf(this);
        wolfShape.setPosition(new Vec2(-5, 10));
        
        //array to set the carrots on the map
        for (int i = 0; i < NUM_CARROTS; i++) {

            Carrot carrot = new Carrot(this);
            carrot.setPosition(new Vec2(i * 2 - 11, -7));

            carrot.addCollisionListener(new Pickup(getPlayer(), game, pickupClip));
        }

        for (int i = 0; i < NUM_CARROTS2; i++) {

            Carrot carrot = new Carrot(this);
            carrot.setPosition(new Vec2(i * 2 + 5f, -1.1f));
            carrot.setPosition(new Vec2(i * 2 - 9, 6.7f));
            carrot.addCollisionListener(new Pickup(getPlayer(), game, pickupClip));
        }
      
        //collion listneres all declared
        foxShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        farmerShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        bearShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        wolfShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        bearShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        raccoonShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        coyoteShape.addCollisionListener(new Intersection(getPlayer(), game, lifeLost));
        foxShape.addCollisionListener(new FarmerFoxIntersection(farmerShape));
        platform5.addCollisionListener(new FoxWallCollision(foxShape));
        platform4.addCollisionListener(new FarmerWallCollision(farmerShape));

    }

    @Override
    
    //set the starting position of the player
    public Vec2 startPosition() {
        return new Vec2(0, -10);
    }
     
    //set the starting position of the DOOR
    @Override
    public Vec2 doorPosition() {
        return new Vec2(10.5f, 10);
    }
    //method to check if the carrot count is equal to number of carrots put on the map. 
    @Override
    public boolean isCompleted() {
        return getPlayer().getCarrotCount() == NUM_CARROTS + (NUM_CARROTS2*2);
    }

}
