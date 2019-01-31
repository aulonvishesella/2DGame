/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.UserView;

/**
 *
 * Class FarmerWallCollision deals with collision of with a farmer and the wall
 */
public class FarmerWallCollision implements CollisionListener {

    private Farmer farmerShape;
    private UserView view;

    /**
     * @param farmerShape The Farmer
     * @param farmerShape set to the farmerShape
     */
    public FarmerWallCollision(Farmer farmerShape) {
        this.farmerShape = farmerShape;
    }

    /**
     * Handle collisions
     *
     * @param e description of the collision
     * @param farmerShape deleted reporting body is then deleted too
     */
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == farmerShape) {

            farmerShape.destroy();

        }

    }

}
