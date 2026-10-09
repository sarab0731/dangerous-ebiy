package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Level1 extends GameLevel{
    private GameManager gameManager;
    private final ArrayList<BackgroundObject> backgroundObjects;
<<<<<<< Updated upstream
    private final Image background = new ImageIcon("data/background/cave/01.png").getImage().getScaledInstance(1300,600, Image.SCALE_AREA_AVERAGING);

    public Level1(Game game) {
        super(game);
        this.setNumberOfPanels(1);
        this.spawnPoint = new Vec2(-27.7f, -2.6f);
        this.initGameManager();
=======
    private final Image background = new ImageIcon("data/background/cave/01.png").getImage().getScaledInstance(1300,650,16);

    public Level1(Game game) {
        super(game);
        this.setNumberOfPanels(3);
        this.spawnPoint = new Vec2(-30.9f, -12.2f);
        this.gameManager = new GameManager(this, game);
>>>>>>> Stashed changes


        backgroundObjects = new ArrayList<>();
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.001f,"data/background/cave/02.png"));
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.005f,"data/background/cave/03.png"));
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.01f,"data/background/cave/04.png"));
        backgroundObjects.add(new BackgroundLayer( this,getCharacter(), new Vec2(0, 0), 0.02f,"data/background/cave/05.png"));
        createLevel();
    }

    private void createLevel(){
        // ---ground & ceiling ---
        for (int i = 0; i < 21; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -15.4f));
        }

        for (int i = 0; i < 21; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -30.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, 13.4f));
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
            tile.setPosition(new Vec2(26.7f, yPos));
        }

        for (int i = 0; i < 8; i++) {
            BrickTile tile = new BrickTile(this);
            float yPos = -12.2f + (i * 3.2f);
            tile.setPosition(new Vec2(29.9f, yPos));
        }

        for (int i = 0; i < 8; i++) {
            BrickTile tile = new BrickTile(this);
            float yPos = -12.2f + (i * 3.2f);
            tile.setPosition(new Vec2(33.1f, yPos));
        }

        for (int i = 0; i < 1; i++) {
            BrickTile tile = new BrickTile(this);
            tile.setPosition(new Vec2(4.3f, -12.2f));
        }

        // --- platforms---
        for (int i = 0; i < 4; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -18.1f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -9f));
        }

        for (int i = 0; i < 6; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = 4.3f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -9f));
        }

            //spaced platforms
        for (int i = 0; i < 5; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -21.3f + (i * 12.8f);
            tile.setPosition(new Vec2(xPos, 3.8f));
        }

        for (int i = 0; i < 6; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -27.7f + (i * 12.8f);
            tile.setPosition(new Vec2(xPos, -2.6f));
        }


        // --- fire tile sections ---


        // --- Adding Collectibles ---
<<<<<<< Updated upstream
        Key key = new Key(this, this.getGameManager());
        key.setPosition(new Vec2(4.3f, 7f));

        Jetpack jetpack = new Jetpack(this, this.getGameManager());
        jetpack.setPosition(new Vec2(-8.5f,-9f));

        for (int i = 0; i < 2; i++) {
            Diamond diamond = new Diamond(this, this.getGameManager());
            float xPos = -21.3f + (i * 12.8f);
            diamond.setPosition(new Vec2(xPos, 7));
        }
        for (int i = 0; i < 1; i++) {
            Diamond diamond = new Diamond(this, this.getGameManager());
            diamond.setPosition(new Vec2(17.1f, 7));
        }

        for (int i = 0; i < 5; i++) {
            Diamond diamond = new Diamond(this, this.getGameManager());
            float xPos = -27.7f + (i * 12.8f);
            diamond.setPosition(new Vec2(xPos, 0.6f));
        }

        // --- Door ---
        Door door = new Door(this, this.getGameManager());
        door.setPosition(new Vec2(7.5f, -12.2f));
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

        // --- Adding Monster ---
        Monster monster = new Monster(this);
        monster.setPosition(new Vec2(1.1f, -5.8f));

        Monster monster2 = new Monster(this);
        monster2.setPosition(new Vec2(26.7f, 0.3f));

        // --- Door ---
        Door door = new Door(this, this.gameManager);
        door.setPosition(new Vec2(36.3f, -12.2f));
>>>>>>> Stashed changes
        setDoor(door);

    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Override
    public Image getBackgroundImage() {
        return background;
    }

    @Override
    public ArrayList<BackgroundObject> getBackgroundObjects() {
        return backgroundObjects;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
