package game;

import city.cs.engine.*;

public class Gun extends Collectable {
    private static final Shape gunShape = new BoxShape(1.6f, 1.1f);
    private static final BodyImage gunImage = new BodyImage("data/collectables/gun.png", 2.2f);

    public Gun(World world, GameManager gameManager) {
        super(world, gunShape, gameManager);
        addImage(gunImage);
    }

    @Override
    public void applyEffect() {
        gameManager.collectGun();
        destroy();
    }
}
