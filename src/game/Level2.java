package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Level2 extends GameLevel{
    private GameManager gameManager;
    private final ArrayList<BackgroundObject> backgroundObjects;
    private final Image background = new ImageIcon("data/background/cave/01.png").getImage().getScaledInstance(1300,600, Image.SCALE_AREA_AVERAGING);

    //


    public Level2(Game game, Level1 level1) {
        super(game);
        this.setNumberOfPanels(3);
<<<<<<< Updated upstream
        this.spawnPoint = new Vec2(-27.7f, -2.6f);
=======
        this.spawnPoint = new Vec2(-30.9f, -12.2f);
        int score = level1.getGameManager().getScore();
        int lives = level1.getGameManager().getLives();

        this.gameManager = new GameManager(this, game, score, lives);
>>>>>>> Stashed changes

        // Background objects
        backgroundObjects = new ArrayList<>();
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.001f,"data/background/cave/02.png"));
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.005f,"data/background/cave/03.png"));
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.01f,"data/background/cave/04.png"));
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.02f,"data/background/cave/05.png"));



        createLevel();
        //this.initGameManager(this.gameManager);

    }

    private void createLevel(){

        // ---ground & ceiling ---
        for (int i = 0; i < 50; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -15.4f));
        }

        for (int i = 0; i < 50; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -12.2f));
        }

        for (int i = 0; i < 50; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -9f));
        }

        for (int i = 0; i < 50; i++) {
            GrassTile tile = new GrassTile(this);
            float xPos = -27.7f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -5.8f));
        }

        for (int i = 0; i < 50; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, 13.4f));
        }

        for (int i = 0; i < 50; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, 10.2f));
        }

        for (int i = 0; i < 50; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, 7f));
        }

        // --- walls ---
        for (int i = 0; i < 8; i++) {
            BrickTile tile = new BrickTile(this);
            float yPos = -12.2f + (i * 3.2f);
            tile.setPosition(new Vec2(-30.9f, yPos));
        }

        for (int i = 0; i < 8; i++) {
            BrickTile tile = new BrickTile(this);
            float yPos = -12.2f + (i * 3.2f);
            tile.setPosition(new Vec2(129.1f, yPos));
        }

        // --- fire tile sections ---
        for (int i = 0; i < 5; i++) {
            FireTile tile = new FireTile(this);
            float xPos = -14.9f + (i * 12.8f);
            tile.setPosition(new Vec2(xPos, -2.6f));
        }


        // --- Adding Collectibles ---
<<<<<<< Updated upstream
        Gun gun = new Gun(this, this.getGameManager());
        gun.setPosition(new Vec2(-1.1f, 3.8f));

        for (int i = 0; i < 2; i++) {
            Diamond diamond = new Diamond(this, this.getGameManager());
            float xPos = -27.7f + (i * 12.8f);
            diamond.setPosition(new Vec2(xPos, 3.8f));
        }

        for (int i = 0; i < 6; i++) {
            Diamond diamond = new Diamond(this, this.getGameManager());
            float xPos = 13.9f + (i * 12.8f);
            diamond.setPosition(new Vec2(xPos, 3.8f));
        }


        Key key = new Key(this, this.getGameManager());
        key.setPosition(new Vec2(109.9f, -2.6f));
=======
        Gun gun = new Gun(this, this.gameManager);
        gun.setPosition(new Vec2(-14.9f, 0.6f));

        Jetpack jetpack = new Jetpack(this, this.gameManager);
        jetpack.setPosition(new Vec2(33.1f, -12.2f));

        Key key = new Key(this, this.gameManager);
        key.setPosition(new Vec2(7.5f, -12.2f));

        Beanie beanie = new Beanie(this, this.gameManager);
        beanie.setPosition(new Vec2(20.3f, -12.2f));

        Diamond diamond = new Diamond(this, this.gameManager);
        diamond.setPosition(new Vec2(-11.7f, -12.2f));

        Diamond diamond2 = new Diamond(this, this.gameManager);
        diamond2.setPosition(new Vec2(-8.5f, 7f));
>>>>>>> Stashed changes

        // --- Adding Monster ---
        Monster monster = new Monster(this);
        monster.setPosition(new Vec2(77.9f, 0.6f));

        Monster monster2 = new Monster(this);
        monster2.setPosition(new Vec2(103.5f, 0.6f));

        // --- Door ---
<<<<<<< Updated upstream
        Door door = new Door(this, this.getGameManager());
        door.setPosition(new Vec2(125.9f, -2.6f));
=======
        Door door = new Door(this, this.gameManager);
        door.setPosition(new Vec2(36.3f, -12.2f));
>>>>>>> Stashed changes
        setDoor(door);
    }


    @Override
<<<<<<< Updated upstream
=======
    public void isComplete() {
        return; // fill later
    }

    @Override
>>>>>>> Stashed changes
    public Image getBackgroundImage() {
        return background;
    }

    @Override
    public ArrayList<BackgroundObject> getBackgroundObjects() {
        return backgroundObjects;
    }

}