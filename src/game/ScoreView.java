package game;

import java.awt.Graphics2D;
import city.cs.engine.*;
import game.Game;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

/**
 * The class ScoreView will be the view to the user
 */
public class ScoreView extends UserView {

    private Game game;
    private Image background;

    /**
     * @param world The World
     * @param game The Game
     * @param width The Width
     * @param height The Height
     */
    public ScoreView(World world, Game game, int width, int height) {
        super(world, width, height);
        this.game = game;
        //setting the background to an image in that specific location
        this.background = new ImageIcon("data/back.jpg").getImage();
    }

    /**
     * method to draw the image on the screen
     */
    protected void paintBackground(Graphics2D g) {
        AffineTransform at = AffineTransform.getScaleInstance(2, 2); //scaling of the image
        //draw the image at that background set, with the specfiic scale.
        g.drawImage(background, at, this);
    }

    /**
     * method to update background
     */
    public void updateBackground(ImageIcon icon) {
        this.background = icon.getImage();
    }

    /**
     * method to show the score, life left and time taken
     */
    @Override
    protected void paintForeground(Graphics2D g) {

        //graphical interfaces for the user to see. Provides the score, lives lift and time taken.
        g.drawString("Score: " + game.getCount(), 10, 20);
        g.drawString("Lives left: " + game.getHealth(), 100, 20);
        g.drawString("Time taken : " + game.elapsedTime(), 200, 20);

    }
}
