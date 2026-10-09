package game;

import city.cs.engine.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * main game entry point
 */
public class Game {
    GameLevel currentLevel;
    GameView view;
    CharacterController controller;
    Tracker tracker;

    private GameMusic gameMusic;


    /** Initialise a new Game. */
    public Game() {

        // SOUND
        try {
            gameMusic = new GameMusic("data/sound/gameTheme.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }

        // Set Current Level
        currentLevel = new Level1(this);
        view = new GameView(currentLevel, 1300, 600);

        //JFrame debugView = new DebugViewer(currentLevel, 500, 500); //debugging view

        // STEP LISTENER
        tracker = new Tracker(view, currentLevel);
        currentLevel.addStepListener(tracker); // tracker listener

        // CHARACTER CONTROLLER
        controller = new CharacterController(currentLevel, this);
        view.addKeyListener(controller);

        //view.setGridResolution(1);


        final JFrame frame = new JFrame("");
        frame.add(view, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // enable the frame to quit the application when the x button is pressed
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack(); // size the frame to fit the world view
        frame.setVisible(true); // make the frame visible


        view.addMouseListener(new GiveFocus(view));
        currentLevel.start();



    }

    public void goToNextLevel(){

        System.out.println("Level completed");
        GameLevel oldLevel = currentLevel;

        if (currentLevel instanceof Level1) {

            // Move to the next level
            currentLevel.stop();
<<<<<<< Updated upstream
            currentLevel = new Level2(this);

            // Reset the GameManager, keeping score and lives
            currentLevel.getGameManager().resetFlags();
            currentLevel.getGameManager().setWorld(currentLevel);
            currentLevel.getGameManager().respawnCharacter();

            // update view
            view.setWorld(currentLevel);
            view.setGameLevel(currentLevel);

            // update controller
            controller.updateController(currentLevel);
            //controller.updatePlayer(currentLevel.getCharacter());

            // update tracker
            tracker.updateTracker(currentLevel);
            currentLevel.addStepListener(tracker);

            // start new level
=======
            //view.setWorld(//LevelCompletionsequence);
            currentLevel = new Level2(this, (Level1) oldLevel);
            view.setWorld(currentLevel);
            view.setGameLevel(currentLevel);
            tracker = new Tracker(view, currentLevel);
            currentLevel.addStepListener(tracker); // tracker listener
            controller = new CharacterController(currentLevel, this);
            view.addKeyListener(controller);
>>>>>>> Stashed changes
            currentLevel.start();
        }else if (currentLevel instanceof Level2){

                // Move to the next level
                currentLevel.stop();
                currentLevel = new Level3(this);

                // Reset the GameManager, keeping score and lives
                currentLevel.getGameManager().resetFlags();
                currentLevel.getGameManager().setWorld(currentLevel);
                currentLevel.getGameManager().respawnCharacter();

                // update view
                view.setWorld(currentLevel);
                view.setGameLevel(currentLevel);

                // update controller
                controller.updateController(currentLevel);
                //controller.updatePlayer(currentLevel.getCharacter());

                // update tracker
                tracker.updateTracker(currentLevel);
                currentLevel.addStepListener(tracker);

                // start new level
                currentLevel.start();
        }
        else{
            System.exit(0);
            //view.displayEndScreen();
        }
    }

//    public void goToNextLevel(){
//        if (level instanceof Level1){
//            level.stop();
//            level = new Level2(this);
//            //level now refer to the new level
//            view.setWorld(level);
//            controller.updateStudent(level.getStudent());
//            level.start();
//        }
//        else if (level instanceof Level2){
//            System.out.println("Well done! Game complete.");
//            System.exit(0);
//        }
//    }

    public void updateCharacterController(Character newCharacter) { controller.updatePlayer(newCharacter);}
    public void updateTracker(Character newCharacter){ tracker.updatePlayer(newCharacter);}


    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
