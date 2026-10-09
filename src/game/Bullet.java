package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bullet extends DynamicBody{
    private static final Shape bulletShape = new BoxShape(0.8f,0.3f);
    private static final BodyImage image = new BodyImage("data/projectiles/bullet.png", 0.6f);
    public AttachedImage bulletImage;

    public Bullet(World world, GameManager gameManager){
        super(world, bulletShape);
        bulletImage = new AttachedImage(this, image, 1, 0, new Vec2(0,0));
        //addImage(image);
        //collision listener
        BulletCollisions kill = new BulletCollisions(gameManager);
        this.addCollisionListener(kill);
        setGravityScale(0);
        setBullet(true);
    }
}
