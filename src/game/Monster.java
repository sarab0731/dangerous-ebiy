package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Monster extends BeingEntity implements ActionListener,StepListener {
    private final World world;
    private final Timer fireTimer;
    private boolean isDestroyed = false; // tracks if monster has been destroyed
    private final float halfWidth;

    private final int SPEED = 4;
    private float top,bottom;
    private final int RANGE = 3;


    public Monster(World world) {
        super(world, new BoxShape(3.2f, 1.6f));
        this.world = world;
        halfWidth = 3.2f;

        addImage(new BodyImage("data/monsters/spider.png", 3.2f));

        // Create a timer to fire projectiles every 6 seconds
        fireTimer = new Timer(6000, this);
        fireTimer.setInitialDelay(10000);
        fireTimer.start(); // Start the timer

        // notify the world of this step listener and start walking
        world.addStepListener(this);
        setGravityScale(0);
        //startWalking(SPEED);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        fireProjectile();
    }

    private void fireProjectile() {
        // Create a projectile at the enemy's position
        MonsterBullet mBullet = new MonsterBullet(world, this);
        mBullet.setPosition(new Vec2(this.getPosition().x -halfWidth - 1, this.getPosition().y));

        // Set the projectile's velocity to move left
        mBullet.setLinearVelocity(new Vec2(-20, 0));
    }

    public boolean isDestroyed(){
        return  isDestroyed;
    }

    @Override
    public void destroy() {
        super.destroy(); // Destroy the monster body
        isDestroyed = true;
        fireTimer.stop(); // Stop shooting bullets if monster is destroyed
    }

    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
        bottom = position.y - RANGE; // Set lower limit
        top = position.y + RANGE; // Set upper limit
        setLinearVelocity(new Vec2(0, SPEED)); // Start moving up
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().y > top) {
            setLinearVelocity(new Vec2(0, -SPEED)); // Move downward
        }
        if (getPosition().y < bottom) {
            setLinearVelocity(new Vec2(0, SPEED)); // Move upward
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }


}