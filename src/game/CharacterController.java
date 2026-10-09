package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterController implements KeyListener {
    private static final float WALKING_SPEED = 10;
    private static final float JUMP_FORCE = 15;
    private static final float BULLET_SPEED = 20;

    private Character player;
    private GameLevel level;
    private GameManager gameManager;

    public CharacterController(GameLevel level, Game game){
        this.level = level;
<<<<<<< Updated upstream
=======
        this.gameManager = level.getGameManager();
        player = gameManager.getPlayer();
        this.game = game;
>>>>>>> Stashed changes
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_UP:
                if (gameManager.jetpackActivated()) {
                    player.setLinearVelocity(new Vec2(0, WALKING_SPEED));
                } else {
                    Door door = level.getDoor();
                    if (door != null && gameManager.isOpen()) {
                        if (player.getPosition().sub(door.getPosition()).length() < 2.5f) {
<<<<<<< Updated upstream
=======
                            player.destroy();
>>>>>>> Stashed changes
                            level.isComplete();
                            return;
                        }
                    }
                    player.jump(JUMP_FORCE);
                    player.addJumpImage();
                }
                break;

            case KeyEvent.VK_DOWN:
                if (gameManager.jetpackActivated()) {
                    player.setLinearVelocity(new Vec2(0, -WALKING_SPEED));
                }
                break;

            case KeyEvent.VK_RIGHT:
                player.facingRight = true;
                player.movingLeft = false;
                player.movingRight = true;
                if (gameManager.jetpackActivated()) {
                    player.setLinearVelocity(new Vec2(WALKING_SPEED, 0));
                } else {
                    player.startWalking(WALKING_SPEED);
                    player.startWalkingAnimation();
                    if (player.touchingRightWall) {
                        player.stopMoving();
                    }
                }
                break;

            case KeyEvent.VK_LEFT:
                player.facingRight = false;
                player.movingRight = false;
                player.movingLeft = true;
                if (gameManager.jetpackActivated()) {
                    player.setLinearVelocity(new Vec2(-WALKING_SPEED, 0));
                } else {
                    player.startWalking(-WALKING_SPEED);
                    player.startWalkingAnimation();
                    if (player.touchingLeftWall) {
                        player.stopMoving();
                    }
                }
                break;

            case KeyEvent.VK_SPACE:
                if (gameManager.hasGun()) {
                    fireProjectile();
                }
                break;

            case KeyEvent.VK_J:
                if (gameManager.hasJetpack()) {
                    gameManager.activateJetpack();
                } else {
                    gameManager.deactivateJetpack();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                player.stopMoving();
                player.movingRight = false;
                player.movingLeft = false;
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                if (gameManager.hasJetpack() && gameManager.jetpackActivated()) {
                    player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, 0));
                    player.stopWalking();
                    player.walking = false;
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void fireProjectile() {
        // Create a projectile at the player's position
        Bullet bullet = new Bullet(level,gameManager);
        // set projectile's velocity to move in the players direction
        if (player.facingRight){
            bullet.setPosition(new Vec2((player.getPosition().x +player.halfWidth + 0.1f), player.getPosition().y));
            bullet.setLinearVelocity(new Vec2(BULLET_SPEED, 0));
        }else{
            bullet.setPosition(new Vec2((player.getPosition().x -player.halfWidth - 0.1f), player.getPosition().y));
            bullet.setLinearVelocity(new Vec2(-BULLET_SPEED, 0));
            bullet.bulletImage.flipHorizontal();
        }

    }

    public void updatePlayer(Character newPlayer){
        player = newPlayer;
    }

<<<<<<< Updated upstream
    public void updateController(GameLevel newLevel) {
        this.level = newLevel;
        this.gameManager = newLevel.getGameManager();
        this.player = gameManager.getPlayer();
    }

=======
    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
>>>>>>> Stashed changes
}
