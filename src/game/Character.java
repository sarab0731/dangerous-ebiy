package game;

import city.cs.engine.*;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

public class Character extends BeingEntity {

    //SHAPE AND IMAGES
    private static final Shape characterShape = new PolygonShape(
            -1.55f, 1.4f,   // Top-left vertex (reduced width by 0.05)
            1.55f, 1.4f,    // Top-right vertex (reduced width by 0.05)
            1.55f, -1.4f,   // Bottom-right vertex (reduced width by 0.05)
            1.4f, -1.6f,    // Slightly inward bottom-right vertex
            -1.4f, -1.6f,   // Slightly inward bottom-left vertex
            -1.55f, -1.4f   // Bottom-left vertex (reduced width by 0.05)
    );

   // private static final Shape characterShape = new BoxShape(1.5f, 1.55f);


    public float halfWidth = 1.6f;

    private static final BodyImage idleImage = new BodyImage("data/player/beanie_off/idle.png", 3.3f);
    private static final BodyImage jumpImage = new BodyImage("data/player/beanie_off/jump.png", 3.2f);

    private static final BodyImage[] walkFrames = {
            new BodyImage("data/player/beanie_off/walk3.png", 3.2f),
            new BodyImage("data/player/beanie_off/walk1.png",3f),
            new BodyImage("data/player/beanie_off/walk3.png", 3.2f),
            new BodyImage("data/player/beanie_off/walk2.png",3f)
    }; // Array of images for walking animation

    private static final BodyImage jetpack = new BodyImage("data/player/beanie_off/fly.png", 3f);

    private AttachedImage currentImage;
    private int currentFrame = 0; // Current frame index
    public boolean walking = false;
    public boolean jumping = false;
    public boolean isGrounded = true;
    public boolean facingRight = true;
    public boolean touchingRightWall = false;
    public boolean touchingLeftWall = false;
    public boolean movingRight = false;
    public boolean movingLeft = false;

    // ATTRIBUTES
    private int lives;

    // CHARACTER SOUND EFFECTS

    //static


    public Character(World world) {
        super(world, characterShape);
        this.lives = 3;
        currentImage = new AttachedImage(this, idleImage, 1, 0, new Vec2(0,0));

        Timer animationTimer = new Timer(80, ae -> {
            if (walking) {
                currentFrame = (currentFrame + 1) % walkFrames.length;
                setCharacterImage(walkFrames[currentFrame]);
                if (!facingRight) {
                    currentImage.flipHorizontal();// Flip the sprite for left movement
                }
            }
        });
        animationTimer.start();

        Shape footSensorShape = new BoxShape(1.6f, 0.1f, new Vec2(0, -1.6f)); // Slightly below body
        Sensor footSensor = new Sensor(this, footSensorShape);
        footSensor.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent e) {
                if (e.getContactBody() instanceof Tile) {
                    isGrounded = true;
                }
            }
            @Override
            public void endContact(SensorEvent e) {
                isGrounded = false;
            }
        });


        Sensor leftSensor = new Sensor(this, new BoxShape(0.1f, 1.4f, new Vec2(-1.6f, 0)));
        leftSensor.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent e) {
                if (e.getContactBody() instanceof Tile) {
                    touchingLeftWall = true;
                }
            }
            @Override
            public void endContact(SensorEvent e) {
                touchingLeftWall = false;
            }
        });

        Sensor rightSensor = new Sensor(this, new BoxShape(0.1f, 1.4f, new Vec2(1.6f, 0)));
        rightSensor.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent e) {
                if (e.getContactBody() instanceof Tile) {
                    touchingRightWall = true;
                }
            }
            @Override
            public void endContact(SensorEvent e) {
                touchingRightWall = false;
            }
        });



    }


    public void startWalkingAnimation() {
        //check if jumping, if not, start walking animation
        if (Math.abs(getLinearVelocity().y) < 0.1f) { // ensure the player is not in the air
            walking = Math.abs(getLinearVelocity().x) > 0f;
        }
    }

    public void stopMoving() {
        setLinearVelocity(new Vec2(0,getLinearVelocity().y));
        stopWalking();
        walking = false;
    }

    @Override
    public void jump(float speed) {
        Vec2 v = this.getLinearVelocity();
        if (Math.abs(v.y) < 0.01F) {
            this.setLinearVelocity(new Vec2(v.x, speed));
        }
        if (this.getLinearVelocity().y != 0){
            jumping = true;
        }else {
            setCharacterImage(idleImage); // currently doesnt work
        }
    }


    //--Character image handling methods--
    public void addJumpImage(){
        setCharacterImage(jumpImage);
        if (!facingRight) {
            currentImage.flipHorizontal();// Flip the sprite for left movement
        }
    }

    public void addJetpackImage(){
        setCharacterImage(jetpack);
        if (!facingRight) {
            currentImage.flipHorizontal();// Flip the sprite for left movement
        }
    }


    private void setCharacterImage(BodyImage image){
        this.removeAllImages();
        currentImage = new AttachedImage(this, image, 1, 0, new Vec2(0,0));
    }

    //--live methods--

    public void reduceLife() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

}