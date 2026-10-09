package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Level3 extends GameLevel{
    private final ArrayList<BackgroundObject> backgroundObjects;
    private final Image background = new ImageIcon("data/background/sky/sky.png").getImage();

    public Level3(Game game) {
        super(game);
        this.setNumberOfPanels(3);
        this.spawnPoint = new Vec2(-30.9f, -12.2f);

        // background objects
        backgroundObjects = new ArrayList<>();
        backgroundObjects.add(new Cloud(this, new Vec2(-100, 100), 0.05f,"data/background/sky/cloud1.png"));
        backgroundObjects.add(new Cloud(this, new Vec2(-300, 300), 0.01f,"data/background/sky/cloud1.png"));

        createLevel();
<<<<<<< Updated upstream
=======
        //this.initGameManager();
>>>>>>> Stashed changes
    }

    private void createLevel(){
        // ---ground ---
        for (int i = 0; i < 8; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -34.1f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -15.4f));
        }

        for (int i = 0; i < 12; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = 7.5f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -15.4f));
        }

        // --- platform with stone tiles ---
        for (int i = 0; i < 3; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -24.5f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -8.7f));
        }

        for (int i = 0; i < 2; i++) {
            BrickTile tile = new BrickTile(this);
            float xPos = -14.9f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -2.6f));
        }

        for (int i = 0; i < 1; i++) {
            StoneTile tile = new StoneTile(this);
            float xPos = -8.5f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, 3.8f));
        }

        // --- fire tile sections ---
        for (int i = 0; i < 5; i++) {
            FireTile tile = new FireTile(this);
            float xPos = -8.5f + (i * 3.2f);
            tile.setPosition(new Vec2(xPos, -15.4f));
        }

        // --- Walls ---
        for (int i = 0; i < 11; i++) {
            StoneTile tile = new StoneTile(this);
            float yPos = -15.4f + (i * 3.2f);
            tile.setPosition(new Vec2(-34.1f, yPos));
        }

        // --- Adding Collectibles ---
        Gun gun = new Gun(this, this.getGameManager());
        gun.setPosition(new Vec2(-14.9f, 0.6f));

        Jetpack jetpack = new Jetpack(this, this.getGameManager());
        jetpack.setPosition(new Vec2(33.1f, -12.2f));

        Key key = new Key(this, this.getGameManager());
        key.setPosition(new Vec2(7.5f, -12.2f));

        Beanie beanie = new Beanie(this, this.getGameManager());
        beanie.setPosition(new Vec2(20.3f, -12.2f));

        Diamond diamond = new Diamond(this, this.getGameManager());
        diamond.setPosition(new Vec2(-11.7f, -12.2f));

        Diamond diamond2 = new Diamond(this, this.getGameManager());
        diamond2.setPosition(new Vec2(-8.5f, 7f));

        // --- Adding Monster ---
        Monster monster = new Monster(this);
        monster.setPosition(new Vec2(1.1f, -5.8f));

        Monster monster2 = new Monster(this);
        monster2.setPosition(new Vec2(26.7f, 0.3f));

        // --- Door ---
        Door door = new Door(this, this.getGameManager());
        door.setPosition(new Vec2(36.3f, -12.2f));
        setDoor(door);
    }

<<<<<<< Updated upstream
=======
    @Override
    public void isComplete() {
        return; // fill later
    }
>>>>>>> Stashed changes

    @Override
    public Image getBackgroundImage() {
        return background;
    }

    @Override
    public ArrayList<BackgroundObject> getBackgroundObjects() {
        return backgroundObjects;
    }
}