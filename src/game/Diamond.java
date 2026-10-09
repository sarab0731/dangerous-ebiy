package game;

import city.cs.engine.*;

public class Diamond extends Collectable {
    private static final Shape diamondShape = new BoxShape(1.3f, 1f);
    private static final BodyImage diamondImage = new BodyImage("data/collectables/diamond.png", 2);

    public Diamond(World world, GameManager gameManager) {
        super(world, diamondShape, gameManager);
        addImage(diamondImage);
    }

    @Override
    public void applyEffect() {
        gameManager.increaseScore(100);
        destroy();
    }
}
