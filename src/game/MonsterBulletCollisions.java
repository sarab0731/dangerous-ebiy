package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class MonsterBulletCollisions implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        // destroy bullet if it collides with another Body
        e.getReportingBody().destroy();
    }
}
