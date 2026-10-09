package game;


import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class GameManager {
    private GameLevel world;
    private Game game;
    private Character player;
    private int score;
    private int lives;

    private boolean hasJetpack = false;
    private boolean activatedJetpack = false;
    private long jetpackEndTime = 0;

    private boolean hasGun = false;
    private boolean hasKey = false;
    private boolean hasBeanie = false;
    private boolean isOpen = false;

    Vec2 characterSpawnPoint;

    public GameManager(GameLevel world, Game game) {
        this.world = world;
        this.game = game;
        this.score = 0; // Initialize score
        this.lives = 3; // Initialize lives

        this.characterSpawnPoint = world.spawnPoint;

        player = new Character(world);
        player.setPosition(characterSpawnPoint);
        player.addCollisionListener(new CharacterCollisions(this));


    }

<<<<<<< Updated upstream
    public GameManager(GameLevel world, Game game, GameManager previousManager) {
        this.world = world;
        this.game = game;

        if (previousManager != null) {
            this.score = previousManager.getScore();
            this.lives = previousManager.getLives();
        } else {
            this.score = 0;
            this.lives = 3;
        }
=======
    public GameManager(GameLevel world, Game game, int score,int lives) {
        this.world = world;
        this.game = game;
        this.score = score; // Initialize score
        this.lives = lives; // Initialize lives
>>>>>>> Stashed changes

        this.characterSpawnPoint = world.spawnPoint;

        player = new Character(world);
        player.setPosition(characterSpawnPoint);
        player.addCollisionListener(new CharacterCollisions(this));
<<<<<<< Updated upstream
    }

=======


    }

//    public GameManager(GameLevel world, Game game, GameManager previousManager) {
//        this.world = world;
//        this.game = game;
//        if (previousManager != null) {
//            this.score = previousManager.getScore();
//            this.lives = previousManager.getLives();
//        } else {
//            this.score = 0;
//            this.lives = 3;
//        }
//        this.characterSpawnPoint = world.spawnPoint;
//        player = new Character(world);
//        player.setPosition(characterSpawnPoint);
//        player.addCollisionListener(new CharacterCollisions(this));
//    }

>>>>>>> Stashed changes
    public void increaseScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

<<<<<<< Updated upstream
=======
//    // used when progressing to the next level
//    public void resetFlags(){
//        hasGun = false;
//        hasJetpack = false;
//        hasKey = false;
//        hasBeanie = false;
//        isOpen = false;
//    }

>>>>>>> Stashed changes
    public void respawnCharacter() {
        if (lives > 0) {
            player = new Character(world);
            player.setPosition(characterSpawnPoint);
            player.addCollisionListener(new CharacterCollisions(this));
            game.updateTracker(player);
            game.updateCharacterController(player);
        } else {
            System.out.println("Game Over! Final Score: " + score);
        }
    }

    public void characterDied() {
        player.destroy();
        lives--;
        if (lives > 0) {
            respawnCharacter();
        } else {
            System.out.println("Game Over! Final Score: " + score);
        }
    }

    public Character getPlayer() {
        return player;
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void activateJetpack() {
        activatedJetpack = true;
        player.addJetpackImage();
        player.setGravityScale(0);
        jetpackEndTime = System.currentTimeMillis() + 10000; // 10 seconds
    }

    public void deactivateJetpack(){
        player.setGravityScale(1);
        activatedJetpack = false;
    }

    public boolean jetpackActivated() {
        if (activatedJetpack && System.currentTimeMillis() > jetpackEndTime) {
            deactivateJetpack();
            hasJetpack = false;
        }
        return activatedJetpack;
    }



    public void collectGun() { hasGun = true; }
    public void collectJetpack() { hasJetpack = true; }
    public void collectKey() { hasKey = true; }
    public void collectBeanie() { hasBeanie = true; }
    public void useBeanie() { hasBeanie = false; }
    public void setOpen(){isOpen = true;}

    public boolean hasGun() { return hasGun; }
    public boolean hasJetpack() { return hasJetpack; }
    public boolean hasKey() { return hasKey; }
    public boolean hasBeanie() { return hasBeanie; }
    public boolean isOpen(){ return isOpen; }


    public GameLevel getWorld() {
        return world;
    }

    public void setWorld(GameLevel world) {
        this.world = world;
    }

    public void resetFlags(){
        hasGun = false;
        hasKey = false;
        hasJetpack = false;
        hasBeanie = false;
        isOpen = false;
    }
}