package game;

import city.cs.engine.*;

public class Key extends Collectable {
    private static final Shape keyShape = new BoxShape(1.6f, 1.6f);
    private static final BodyImage keyImage = new BodyImage("data/collectables/key.png", 3.2f);

    public Key(World world, GameManager gameManager) {
        super(world, keyShape, gameManager);
        addImage(keyImage);
    }

    @Override
    public void applyEffect() {
        gameManager.collectKey();
        destroy();
    }
}
