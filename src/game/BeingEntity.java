package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeingEntity extends Walker {

    public BeingEntity(World world, Shape shape) {
        super(world, shape);
    }

    @Override
    public void destroy() {
        this.stopWalking();
        float x = this.getPosition().x; // capture last position
        float y = this.getPosition().y;
        super.destroy();
        animateDeath(this.getWorld(), x, y); // Start explosion animation
    }

    private void animateDeath(World world, float x, float y) {
        // Create a static body at the last position
        StaticBody explosion = new StaticBody(world);
        explosion.setPosition(new Vec2(x, y));

        int delay = 200; // (100ms per frame)
        int cycleCount = 3; // repeat animation x3
        int frameCount = 3; // num of images

        BodyImage[] explosionImages = {
                new BodyImage("data/destroy1.png", 3.2f),
                new BodyImage("data/destroy2.png", 3.2f),
                new BodyImage("data/destroy3.png", 3.2f)
        };

        Timer timer = new Timer(delay, new ActionListener() {
            int frame,cycle = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame < frameCount) {
                    explosion.removeAllImages();
                    explosion.addImage(explosionImages[frame]); // Change image
                    frame++;
                } else {
                    frame = 0;
                    cycle++;
                }

                if (cycle >= cycleCount) {
                    ((Timer) e.getSource()).stop(); // stop the timer
                    explosion.destroy();
                }
            }
        });

        timer.setRepeats(true);
        timer.start(); // Start the animation
    }


}