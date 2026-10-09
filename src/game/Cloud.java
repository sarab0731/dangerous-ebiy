package game;

import org.jbox2d.common.Vec2;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class Cloud implements BackgroundObject{
    private Vec2 position;
    private float speed;
    private Image image;
    private GameView view;
    private GameLevel currentLevel;

    public Cloud(GameLevel currentLevel, Vec2 position, float speed, String imagePath) {
       // this.view = view;
        this.currentLevel = currentLevel;
        this.position = position;
        this.speed = speed;
        this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
    }

    public void move() {
       // float viewportWidth = (float) view.getWidth();
        // Set the cloud's start and end positions, allowing it to wrap around the entire level view
        float startX = -1300;
        float endX = 1300;

        // If the cloud moves past the end, reset it to the starting position
        if (position.x > endX) {
            position.x = startX;
        } else if (position.x < startX) {
            // If the cloud moves past the start, reset it to the end
            position.x = endX;
        }
        position.x += speed;
    }

    // Renders the cloud image on the screen at its current position
    public void render(Graphics2D g) {
        g.drawImage(image, (int) position.x, (int) position.y, null);
    }

    public Vec2 getPosition() {
        return position;
    }
}
