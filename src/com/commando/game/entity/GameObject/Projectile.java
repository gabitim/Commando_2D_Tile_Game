package com.commando.game.entity.GameObject;

import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.Vector2d;

import java.awt.*;

import static com.commando.game.states.PlayState.HERO_SPAWN_POSITION_X;
import static com.commando.game.states.PlayState.HERO_SPAWN_POSITION_Y;

/**
 * @author Timofti Gabriel
 */
public class Projectile extends GameObject {

    public static final int BULLET_SPEED = 10;

    private Vector2d HERO_MOVEMENT_OFFSET = new Vector2d(0, 0);

    protected SpriteSheet spriteSheet;

    protected Vector2d source;
    protected Vector2d destination;
    protected Vector2d speed;
    protected Vector2d direction;


    public Projectile(Vector2d size, Vector2d source, Vector2d destination) {

        HERO_MOVEMENT_OFFSET.x = source.x - HERO_SPAWN_POSITION_X;
        HERO_MOVEMENT_OFFSET.y  = source.y - HERO_SPAWN_POSITION_Y;

        destination.x += HERO_MOVEMENT_OFFSET.x;
        destination.y += HERO_MOVEMENT_OFFSET.y;

        System.out.println("Player: " + source.x + ", " + source.y);
        System.out.println("Mouse: " + destination.x + ", "+ destination.y);
        //this.spriteSheet = spriteSheet;

        this.source = source;
        this.destination = destination;
        this.size = size;
        this.position = source;
        direction = GameObject.minus(destination, source);

        float directionLength = (float) Math.sqrt(direction.x * direction.x + direction.y * direction.y);
        direction.x = direction.x / directionLength;
        direction.y = direction.y / directionLength;

        speed = new Vector2d(BULLET_SPEED, BULLET_SPEED); //bullet speed
    }

    @Override
    public GameObjectID getId() {
        return id;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public Vector2d getSize() {
        return size;
    }

    @Override
    public void update() {
        Vector2d auxSpeed = new Vector2d(direction.x * speed.x , direction.y * speed.y );
        position = GameObject.add(position,auxSpeed);
    }

    @Override
    public void render(Graphics2D graphics) {
        Vector2d positionAux = GameObject.add(position, speed);
        //System.out.println("Road" + positionAux.x + ", " + positionAux.y);
        graphics.fillRect(
                (int)(positionAux.getWorldVar().x), (int)(positionAux.getWorldVar().y), (int)(size.x * .75f), (int)(size.y * .75f) );
    }

}
