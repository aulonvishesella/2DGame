/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * Walker class for Rabbit
 */
public class Rabbit extends Walker {

    private static final Shape rabbitShape = new PolygonShape(
            0.149f, 0.975f, 0.775f, 0.193f, 0.772f, -0.099f, 0.401f, -0.928f, -0.36f, -0.922f, -0.719f, -0.025f, -0.725f, 0.163f, -0.14f, 0.972f);
    private SolidFixture fixture = new SolidFixture(this, rabbitShape, 50); //implement fricition
    private static final BodyImage image
            = new BodyImage("data/rabbit.png", 2.5f);

    private int count;
    private int carrotCount;
    private int lives;

    /**
     *
     *
     * @param rabbitShapeis the walker
     *
     */
    public Rabbit(World world, int radius) {
        super(world, rabbitShape);
        addImage(image);
        count = 0;
        fixture.setFriction(50); //friction set the image
        lives = 3;

    }

    /**
     *
     *
     * method for carrot count to be returned
     *
     */

    public int getCarrotCount() {
        return carrotCount; //when getter method called, return the carrotCount.
    }

    /**
     *
     *
     * method to increment carrot count
     *
     */

    public void incrementcarrotCount() {
        carrotCount++; //incrementing the carrotCount
        //Printing line of the text 'score' besides the count.
//        GameWon(); // call the method game won.  
    }

    /**
     *
     *
     * method to decrement lives
     *
     */
    public void decrementLives() {
        lives--; // decrement the lives (lives=lives-1)
        //printing line of code 'live left' next to the number of lives left
        GameLost(); //call the method game lost.
    }

    /**
     *
     *
     * method to return lives
     *
     */
    public int getLives() {
        return lives; //return the lives when getter method called.
    }

    /**
     *
     *
     * method if all lives lost
     *
     */
    public void GameLost() {
        if (lives == 0) { //if statement to see if lives are equal to zero.
            System.out.println("RAN OUT OF LIVES, GAME LOST"); // if trye, print line

        }
    }

}
