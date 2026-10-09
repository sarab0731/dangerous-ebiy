package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CharacterCollisions implements CollisionListener {
    private GameManager gameManager;

    public CharacterCollisions(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() == gameManager.getPlayer()) { // Ensure the colliding object is the Character
            if (e.getOtherBody() instanceof Diamond) {
                gameManager.increaseScore(100);  // Increase score
                e.getOtherBody().destroy();  // Remove the diamond
            }
            else if (e.getOtherBody() instanceof MonsterBullet) {
                gameManager.characterDied();
                e.getOtherBody().destroy(); // Destroy the bullet
            }
            else if (e.getOtherBody() instanceof FireTile){
                gameManager.characterDied(); // player loses a life when touching fire
            }
            else if (e.getOtherBody() instanceof Monster){
                gameManager.characterDied(); // player loses a life when touching monster
            }
        }
    }
<<<<<<< Updated upstream
}
=======

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
>>>>>>> Stashed changes
