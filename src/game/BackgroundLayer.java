package game;


import org.jbox2d.common.Vec2;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.*;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.Toolkit;

public class BackgroundLayer implements BackgroundObject {
    private Vec2 position;
    private float parallaxFactor;
    private Image image;
    private GameLevel currentLevel;
    private Character character;
    private int imageWidth;

    public BackgroundLayer(GameLevel currentLevel, Character character, Vec2 position, float parallaxFactor, String imagePath) {
        this.currentLevel = currentLevel;
        this.character = character;
        this.position = position;
        this.parallaxFactor = parallaxFactor;
        this.image = Toolkit.getDefaultToolkit().getImage(imagePath).getScaledInstance(1300,600, Image.SCALE_AREA_AVERAGING);

        this.imageWidth = 1300;
    }

    @Override
    public void move() {
        Vec2 velocity = character.getLinearVelocity();

        if (Math.abs(velocity.x) > 0.01f) {
            position.x -= velocity.x * parallaxFactor;

            // Loop the background when it moves too far left or right
            if (position.x <= -imageWidth) {
                position.x += imageWidth;
            } else if (position.x >= imageWidth) {
                position.x -= imageWidth;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        int screenWidth = 1300;
        int screenHeight = 600;

        // Draw the background twice to cover the whole screen
        int x = (int) position.x;
        int y = (int) position.y;

        g.drawImage(image, x, y, null);
        g.drawImage(image, x + imageWidth, y, null);

    }


    public Vec2 getPosition() {
        return position;
    }
}
