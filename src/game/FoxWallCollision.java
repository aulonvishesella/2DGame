/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.UserView;
/**
 *
 * Class FoxWallCollision deals with collision of with a fox and the wall
 */
public class FoxWallCollision implements CollisionListener {

    private Fox foxShape;
    

    private UserView view;
    /**
     * @param foxShape The Fox
     * @param foxShape set to the foxShape
     */
    public FoxWallCollision(Fox foxShape) {
        this.foxShape = foxShape;
    }
    /**
     * Handle collisions
     *
     * @param e description of the collision
     * @param foxShape deleted reporting body is then deleted too a new foxShape
     * is added and walks across the positive axis
     */
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == foxShape) {

                 foxShape.removeAllImages(); //if the body has contact with the foxshape, remove image
              foxShape.addImage(new BodyImage("data/fox2.png", 1.6f)); //load image
              foxShape.startWalking(17); //start walking at positive x -axis at pace 17
            
        }

    }

}
