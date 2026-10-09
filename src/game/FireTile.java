package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FireTile extends StaticBody {
    private static final String[] fireImages = {
            "data/tiles/fire1.png",
            "data/tiles/fire2.png",
            "data/tiles/fire3.png"
    };

    private static final BoxShape shape = new BoxShape(1.6f, 1.4f);

    Random random = new Random();
    int currentImageIndex = random.nextInt(3);
    private AttachedImage currentImage;
    private final Timer animationTimer;

    public FireTile(World world) {
        super(world,shape);

        currentImage = new AttachedImage(this, new BodyImage(fireImages[currentImageIndex], 3.2f), 1, 0, new Vec2(0, 0));
        animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateFire();
            }
        });
        animationTimer.start();


    }

    private void animateFire() {
        this.removeAllImages();
        currentImageIndex = (currentImageIndex + 1) % fireImages.length;
        currentImage = new AttachedImage(this, new BodyImage(fireImages[currentImageIndex], 3.2f), 1, 0, new Vec2(0, 0));
    }


}
