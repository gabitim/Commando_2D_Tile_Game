package com.commando.game.entity;

import com.commando.game.graphics.Sprite;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class Enemy extends Entity{

    private AABB detect;
    private int radius;

    public Enemy(Sprite sprite, Vector2d origin, int size) {
        super(sprite, origin, size);

        acceleration = 1f;
        deceleration = 0.3f;
        maxSpeed = 3f;

        radius = 135;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setxOffSet(12);
        bounds.setyOffSet(40);

        detect = new AABB(new Vector2d(origin.x - size / 2, origin.y - size / 2), radius);
    }

    public void update(AABB player) {
        if (detect.collisionCircleBox(player)) {
            System.out.println("yee");
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect((int)(position.getWorldVar().x + bounds.getxOffSet()),
                (int)(position.getWorldVar().y + bounds.getyOffSet()),
                (int)bounds.getWidth(),
                (int)bounds.getHeight()
        );

        graphics.setColor(Color.RED);
        graphics.drawOval((int)(detect.getPosition().getWorldVar().x), (int)(detect.getPosition().getWorldVar().y), radius, radius );

        graphics.drawImage(animation.getImage(), (int)(position.getWorldVar().x), (int)(position.getWorldVar().y), size, size, null);
    }

}
