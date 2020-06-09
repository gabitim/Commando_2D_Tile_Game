package com.commando.game.entity;

import com.commando.game.graphics.Animation;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.TileCollision;

import java.awt.*;
import java.io.IOException;

public abstract class GameObject {

    protected int currentAnimation;
    protected int size;

    protected TileCollision tileCollision;
    protected Animation animation;
    public Vector2d position;
    protected SpriteSheet spriteSheet;
    protected GameObjectID id;

    public abstract GameObjectID getId();
    public abstract Vector2d getPosition();
    public int getSize() { return size; };
    public Animation getAnimation() { return animation; }

    public abstract void update();
    public abstract void render(Graphics2D graphics) throws IOException;

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
