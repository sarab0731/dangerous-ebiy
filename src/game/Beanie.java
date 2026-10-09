package game;

import city.cs.engine.*;

public class Beanie extends Collectable {
    private static final Shape beanieShape = new BoxShape(1.4f, 1.1f);
    private static final BodyImage gunImage = new BodyImage("data/collectables/beanie.png", 2.2f);

    public Beanie(World world, GameManager gameManager) {
        super(world, beanieShape, gameManager);
        addImage(gunImage);
    }

    @Override
    public void applyEffect() {
        gameManager.collectBeanie();
        gameManager.setLives(gameManager.getLives() + 1);
        destroy();
    }
}
