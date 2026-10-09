package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Door extends StaticBody implements StepListener{
    //private final Shape doorShape = new BoxShape(1.6f, 1.6f);
    private final BodyImage closedImage = new BodyImage("data/tiles/door_closed.png", 3.5f);
    private final BodyImage openedImage = new BodyImage("data/tiles/door_opened.png", 3.5f);
    private AttachedImage aImage;

    private GameLevel level;
    private GameManager gameManager;

    private final float interactionDistance = 2f;

    public Door(GameLevel level, GameManager gameManager) {
        super(level);
        this.level = level;
        this.gameManager = gameManager;

        aImage = new AttachedImage(this, closedImage, 1, 0, new Vec2(0,0));
        level.addStepListener(this);
    }

    private void tryOpen() {
        Character player = gameManager.getPlayer();
        if (!gameManager.isOpen() && gameManager.hasKey()) {
            if (player.getPosition().sub(this.getPosition()).length() < interactionDistance) {
                gameManager.setOpen();
                removeAllImages();
                aImage = new AttachedImage(this, openedImage, 1 ,0, new Vec2(0, 0));
            }
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        tryOpen();
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
