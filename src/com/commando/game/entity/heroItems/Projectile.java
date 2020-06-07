package com.commando.game.entity.heroItems;

import com.commando.game.entity.GameObject;
import com.commando.game.entity.GameObjectID;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;

import static com.commando.game.states.PlayState.HERO_SPAWN_POSITION_X;
import static com.commando.game.states.PlayState.HERO_SPAWN_POSITION_Y;
import static com.commando.game.util.hub.Define.*;

/**
 * @author Timofti Gabriel
 */
public abstract class Projectile extends GameObject {

    protected Vector2d source;
    protected Vector2d destination;
    protected Vector2d speed;
    protected Vector2d direction;

    protected AABB bounds;

    protected boolean hit = false;

    public Projectile(int size, Vector2d source, Vector2d destination) {

        //this.spriteSheet = spriteSheet;

        Vector2d HERO_MOVEMENT_OFFSET = new Vector2d(0, 0);
        HERO_MOVEMENT_OFFSET.x = source.x - HERO_SPAWN_POSITION_X;
        HERO_MOVEMENT_OFFSET.y  = source.y - HERO_SPAWN_POSITION_Y;

        destination.x += HERO_MOVEMENT_OFFSET.x;
        destination.y += HERO_MOVEMENT_OFFSET.y;

        //System.out.println("Player: " + source.x + ", " + source.y);
        //System.out.println("Mouse: " + destination.x + ", "+ destination.y);

        this.source = source;
        this.destination = destination;
        this.size = size;
        this.position = source;
        direction = GameObject.minus(destination, source);

        float directionLength = (float) Math.sqrt(direction.x * direction.x + direction.y * direction.y);
        direction.x = direction.x / directionLength;
        direction.y = direction.y / directionLength;

        speed = new Vector2d(BASIC_PROJECTILE_SPEED, BASIC_PROJECTILE_SPEED);
    }

    public boolean isHit() { return hit; }

    @Override
    public GameObjectID getId() {
        return GameObjectID.Bullet;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

   public void update() {
        Vector2d auxSpeed = new Vector2d(direction.x * speed.x, direction.y * speed.y);
        position = GameObject.add(position, auxSpeed);
    }

    @Override
    public void render(Graphics2D graphics) {
        if(!hit) { //
            Vector2d positionAux = GameObject.add(position, speed);
            graphics.setColor(Color.YELLOW);
            graphics.fillOval(
                    (int) (positionAux.getWorldVar().x), (int) (positionAux.getWorldVar().y), (size), (size));
        }
    }

}
