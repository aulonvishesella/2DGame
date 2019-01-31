package game;

import city.cs.engine.*;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 * A world with some bodies.
 */
public class Game {

    private SoundClip gameMusic1;  //declaring the variable gamemusic1 to the 
    private SoundClip jumpsound;
    private SoundClip introMusic;
    private SoundClip gameovermusic;
    private SoundClip gamewonmusic;

    //implementing music in the game
    /**
     * The World in which the bodies move and interact.
     */
    private GameLevel world;

    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private ScoreView view;

    private int level;  //declaring variable level to the 
    private int carrotCount; //declare the count of the carrot
    private int lives; //declare the number of lives.
    private Controller controller; //declare controller to the class Controller

    private long start; //start of the time watch
    private boolean stop; //variable stop being declared to a boolean.
    private int carrotCountScreen;

//    
// private Image background;
    /**
     * Initialise a new Game.
     */
    public Game() {
        // make the world
        level = 1; //initialise the level to equal 1

        world = new Level1(); //set world to the first level
        world.populate(this); //populate this world 
        try {
            gameMusic1 = new SoundClip("data/gamemusic.wav"); //set the gamemusic to where it holds the music

            gameMusic1.loop(); //loop so it plays continiously
            gameMusic1.setVolume(0.2f); //set the volume of the game music

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        try {
            gameovermusic = new SoundClip("data/gameover.wav"); //set the gamelostmusic to where it holds the music

            gameovermusic.setVolume(0.99f); //set the volume of the gamelostmusic

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        try {
            gamewonmusic = new SoundClip("data/gamewon1.wav"); //set the gamewonmusic to where it holds the music

            gamewonmusic.setVolume(0.99f); //set the volume of the gamewonmusic

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        carrotCount = 0; //initialise the carrot count to equal 0
        carrotCountScreen = 3;
        lives = 3; //set lives to equal to 3
        start = System.currentTimeMillis(); //set the start of the the time watch
//        view = new UserView(world, 500, 500);
        view = new ScoreView(world, this, 500, 500); //set the user view to the class 'ScoreView'

        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);
        // display the view in a frame
        JFrame frame = new JFrame("Multi-level game");

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);

        UserInterface userInterface = new UserInterface(view, gameMusic1, this);

        frame.add(userInterface, BorderLayout.SOUTH);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        controller = new Controller(world.getPlayer(), jumpsound);
        frame.addKeyListener(controller);

        // start!
        world.start();
    }

    /**
     * Initialise music being played
     */
    public void playMusic() {
        gameMusic1.loop(); //method called playMusic which loops the game music
    }

    /**
     * The player in the current level.
     */
    public Rabbit getPlayer() {
        return world.getPlayer();
    }

    /**
     * Is the current level of the game finished?
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    // method that prints a message once level 4 is passed
    public void gameWon() {
        if (level == 4) {
            System.out.println("RAN OUT OF LIVES, GAME LOST"); // if true, print line

            gameMusic1.stop(); //stop the game music
            gamewonmusic.play(); //play the game won music

            world.stop();

            GameWon gamewon = new GameWon();
            gamewon.setVisible(true);

        }

    }
    /**
      *method which prints a message when level 1 is completed
     */
    

    public void nextLevelText1() {
        if (level == 1) {
            System.out.println("Loading next level......");

        }

    }
       /**
      *method which prints a message when level 3 is completed
     */


    public void nextLevelText3() {
        if (level == 3) {
            System.out.println("Loading next level.......");

        }

    }
    /**
      *method which prints a message when level 2 is completed
     */

    public void nextLevelText2() {
        if (level == 2) {
            System.out.println("Loading next level.......");
            gameMusic1.stop();
        }

    }

     /**
      *method which restarts the game
     */
    public void restart() {
        world.stop();
        level = 1;
        world = new Level1();
        // fill it with bodies
        world.populate(this);
        // switch the keyboard control to the new player
        controller.setBody(world.getPlayer());
        // show the new world in the view
        view.setWorld(world);
        ImageIcon background = new ImageIcon("data/back.jpg");
        view.updateBackground(background);
        world.start();
        carrotCount = 0;

        lives = 3;
        gameMusic1.play();
        start = System.currentTimeMillis();
    }
/**
      *method which starts a new game
     */
    public void NewGame() {
        level = 1;
        world = new Level1();
        // fill it with bodies
        world.populate(this);
        // switch the keyboard control to the new player
        controller.setBody(world.getPlayer());
        // show the new world in the view
        view.setWorld(world);
        ImageIcon background = new ImageIcon("data/back.jpg");
        view.updateBackground(background);
        world.start();
        carrotCount = 0;

        lives = 3;
        gameMusic1.play();
        start = System.currentTimeMillis();
    }
/**
      *method to pause the game
     */

    public void pause() {
        if (stop) {
            //if stop is false, start the game, resume the game music
            world.start();
            stop = false;
            gameMusic1.resume();
        } else {
            //if stop is true, stop the world and pause the music.
            world.stop();
            stop = true;
            gameMusic1.pause();
        }
    }

    /**
     * Advance to the next level of the game.
     */
    public void goNextLevel() {
        world.stop();
        playMusic();
        nextLevelText1();
        nextLevelText2();
        nextLevelText3();

        if (level == 4) {
            gameWon();
        } else if (level == 1) {
            level++;

            // get a new world
            world = new Level2();
            // fill it with bodies
            world.populate(this);
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);
            //when the next level begins, update the background image
            ImageIcon background = new ImageIcon("data/back2.jpg");
            view.updateBackground(background);

            world.start();
        } else if (level == 2) {
            level++;
            // get a new world
            world = new Level3();
            // fill it with bodies
            world.populate(this);
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);

            world.start();
            playMusic();
            //when the next level begins, update the background image
            ImageIcon background = new ImageIcon("data/back3.jpg");
            view.updateBackground(background);

        } else if (level == 3) {
            level++;
            world = new Level4();
            // fill it with bodies
            world.populate(this);
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);
            world.start();
            playMusic();
            //when the next level begins, update the background image
            ImageIcon background = new ImageIcon("data/back4.jpg");
            view.updateBackground(background);
        }

    }
/**
      *to get the carrot count
     */

    public int getCountScreen() {
        return carrotCountScreen;
    }
/**
      *method which decrements carrot count
     */
    public void decrementCount() {
        carrotCountScreen--;
    }
    /**
      *method gets the carrot count
     */

    public int getCount() {
        return carrotCount;
    }
/**
      *method which increments carrot count
     */

    public void incrementCount() {
        carrotCount++;
    }
/**
      *method that decrements life
      * call the method gameLost
     */
    public void decrementHealth() {
        lives--; // decrement the lives (lives=lives-1)
        //printing line of code 'live left' next to the number of lives left
        gameLost();
    }
/**
      *method that returns the lives
     */
    public int getHealth() {
        return lives; //return the lives when getter method called.

    }
/**
      *method for when the game is lost
     */
    public void gameLost() {
        if (lives == 0) { //if statement to see if lives are equal to zero.
            System.out.println("RAN OUT OF LIVES, GAME LOST"); // if true, print line

            world.stop();
//           
            gameMusic1.stop(); //stop the background music
            gameovermusic.play(); //play the gameover music

            GameOver gameover = new GameOver();
            gameover.setVisible(true);

        }

    }
    /**
      *method to play the music
     */
    public void introMusic() {
        try {
            introMusic = new SoundClip("data/intro.wav"); //set the gamemusic to where it holds the music

            introMusic.loop(); //loop so it plays continiously
            introMusic.setVolume(0.9f); //set the volume of the game music

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

    }

  /**
      *method for countdown
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 100.0;
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {
        new Game();
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
