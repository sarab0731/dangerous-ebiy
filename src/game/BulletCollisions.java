package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BulletCollisions implements CollisionListener {
    private GameManager gameManager;

    public BulletCollisions(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void collide(CollisionEvent e) {
        System.out.println("The bullet has been fired");
        if (e.getOtherBody() instanceof Monster) {
            gameManager.increaseScore(1000);
            System.out.println("Monster killed! Score:" + gameManager.getScore());
            e.getReportingBody().destroy();
            e.getOtherBody().destroy();
        }
        else if (e.getOtherBody() !=gameManager.getPlayer()){
            e.getReportingBody().destroy();
        }
    }
}
