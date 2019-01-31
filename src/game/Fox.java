/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

/**
 *
 * Fox extends the walker class
 */
public class Fox extends Walker {

    private boolean moveleft;
    private static final Shape foxShape = new PolygonShape(
            //use of the pologyn editor to measure the correct fit of the image.
            -0.443f, 0.355f, 0.039f, 0.467f, 0.532f, 0.08f, 0.619f, -0.367f, -0.012f, -0.432f, -0.576f, -0.378f, -0.642f, 0.196f, -0.462f, 0.341f);
    private static final BodyImage image
            = new BodyImage("data/fox.png", 1.6f); //uploading the file of the fox and setting its size.
/**
     *
    
     * @param foxShape is the walker
     * @param foxShape will be walking across the negative x axis
     */
    public Fox(World world) {
        super(world, foxShape);
        addImage(image);
//
        startWalking(-17); // allows the image to move left across the x-axis

    }
}
