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
 * Class FarmerFoxIntersection deals with collision of the farmer and fox class
 */
public class FarmerFoxIntersection implements CollisionListener {
    
private Farmer farmerShape;
//      private Game game;
//      private GameWorld world;

    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private UserView view;

    /**
     * @param farmershape The farmer
     * @param farmerShape set to the farmerShape
     */
    public FarmerFoxIntersection(Farmer farmerShape) {
        this.farmerShape = farmerShape;
    }
    /**
     * Handle collisions
     *
     * @param e description of the collision
     * @param farmerShape deleted reporting body is then deleted too
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == farmerShape) {
           

            e.getReportingBody().destroy();
            farmerShape.destroy();

        }

    }

}
