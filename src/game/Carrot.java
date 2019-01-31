/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import city.cs.engine.*;

/**
 * A class called Carrot which extends StaticBody
 */
public class Carrot extends StaticBody {

    private static final Shape carrotShape = new PolygonShape(
            //use of the pologyn editor to measure the correct fit of the image.
            0.034f, 0.468f, -0.476f, -0.39f, -0.398f, -0.472f, 0.468f, 0.054f, 0.408f, 0.296f, 0.266f, 0.406f, 0.046f, 0.48f);
    private static final BodyImage image
            = new BodyImage("data/carrot.png", 1.3f); //uplading the carrot image with it's size(1.3)

    /**
     * add an image specified
     *
     * @param world The World
     *
     */
    public Carrot(World world) {
        super(world, carrotShape);
        addImage(image);
    }
}
