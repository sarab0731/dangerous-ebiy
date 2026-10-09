package game;

import city.cs.engine.*;

public class MonsterBullet extends DynamicBody {
    private static final BoxShape shape = new BoxShape(1.25f,0.2f);
    private static final BodyImage image = new BodyImage("data/projectiles/monster_projectile.png", 0.5f);

    public MonsterBullet(World world, Monster monster) {
            super(world, shape);
            addImage(image);

            this.addCollisionListener(new MonsterBulletCollisions());
            setGravityScale(0); // no gravity for the projectile
            setBullet(true);
        }
}
