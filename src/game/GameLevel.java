package game;

import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class GameLevel extends World {
    private GameManager gameManager;
    private Game game;

    private int numberOfPanels;
    private Vec2[] viewCenters;

    public Vec2 spawnPoint;
    private Door door;

    private float gravity = 16.8f;


    public GameLevel(Game game){
        this.game = game;
        this.setGravity(gravity);

    }

    public Character getCharacter(){ return gameManager.getPlayer(); }

    public void characterKilled() {
        gameManager.characterDied();
    }

    public Door getDoor() { return door; }
    public void setDoor(Door door) { this.door = door; }

    public GameManager getGameManager(){
        return gameManager;
    }
//
//    public void initGameManager() {
//        this.gameManager = new GameManager(this, game);
//   }
//

<<<<<<< Updated upstream
    public void initGameManager() {
        this.gameManager = new GameManager(this, game); // default behavior if no previous manager
    }

    public void isComplete(){
        game.goToNextLevel();
    }
=======
//   public void newGameManager() {
//       this.gameManager = new GameManager(this, game, gameManager.getScore(), gameManager.getLives());
//   }
    public void isComplete(){
        game.goToNextLevel();
    };
>>>>>>> Stashed changes

    public abstract Image getBackgroundImage();

    public abstract ArrayList<BackgroundObject> getBackgroundObjects();

    public int getNumberOfPanels() {
        return numberOfPanels;
    }

    public void setNumberOfPanels(int numberOfPanels) {
        this.numberOfPanels = numberOfPanels;
    }
}