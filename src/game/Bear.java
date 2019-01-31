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
 A class called Bear which extends DynamicBody
 */
public class Bear  extends DynamicBody{
    private static final Shape bearShape = new PolygonShape(
            //use of the pologyn editor to measure the correct fit of the image.
            0.034f, 0.468f, -0.476f, -0.39f, -0.398f, -0.472f, 0.468f, 0.054f, 0.408f, 0.296f, 0.266f, 0.406f, 0.046f, 0.48f);
    private static final BodyImage image
            = new BodyImage("data/bear.png", 1.9f); //uplading the carrot image with it's size(1.9)
  /**
   * add an image specified
   * @param World The World
   
     */
    public Bear(World world) {
        super(world, bearShape);
        addImage(image);
    }
}
