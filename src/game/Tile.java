package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public abstract class Tile extends StaticBody {
    private static final BoxShape shape = new BoxShape(1.6f, 1.6f); // all tiles are the same size

    public Tile(World world, String imagePath) {
        super(world, shape);
        addImage(new BodyImage(imagePath, 3.2f));

    }
}
