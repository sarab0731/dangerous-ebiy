package game;

import city.cs.engine.*;

public class Jetpack extends Collectable {
    private static final Shape jetpackShape = new BoxShape(1.6f, 1.6f);
    private static final BodyImage jetImage = new BodyImage("data/collectables/jetpack.png", 3.2f);

    public Jetpack(World world, GameManager gameManager) {
        super(world, jetpackShape, gameManager);
        addImage(jetImage);
    }

    @Override
    public void applyEffect() {
        gameManager.collectJetpack();
        //character.enableShooting();
        destroy();
    }
}
