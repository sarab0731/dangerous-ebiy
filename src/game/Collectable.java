package game;

import city.cs.engine.*;

public abstract class Collectable extends StaticBody {
    protected GameManager gameManager;
    private final Sensor sensor;

    public Collectable(World world, Shape shape, GameManager gameManager) {
        super(world);
        this.gameManager = gameManager;

        sensor = new Sensor(this, shape);
        sensor.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent e) {
                if (e.getContactBody() instanceof Character) {
                    applyEffect();
                }
            }
            @Override
            public void endContact(SensorEvent e) {
            }});
        }


        public abstract void applyEffect();
}
