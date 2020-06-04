package com.commando.game.entity.GameObject;

import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.Vector2d;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */

enum GameObjectID {
    Player,
    Monster,
    Gun,
    Bullet
}

public abstract class GameObject {
    protected SpriteSheet spriteSheet;
    protected GameObjectID id;
    protected Vector2d position;
    protected Vector2d size;

    public abstract GameObjectID getId();
    public abstract Vector2d getPosition();
    public abstract Vector2d getSize();

    public abstract void update();
    public abstract void render(Graphics2D graphics);

    public static Vector2d add(Vector2d one, Vector2d two) {
        Vector2d third = new Vector2d();
        third.x = one.x + two.x;
        third.y = one.y + two.y;

        return third;
    }

    public static Vector2d minus(Vector2d one, Vector2d two) {
        Vector2d third = new Vector2d();
        third.x = one.x - two.x;
        third.y = one.y - two.y;

        return third;
    }

    public static Vector2d multiplyScalar(Vector2d one, float scalar) {
        Vector2d third = new Vector2d();
        third.x = one.x + scalar;
        third.y = one.y + scalar;

        return third;
    }
}
