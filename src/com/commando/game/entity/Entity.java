package com.commando.game.entity;

import com.commando.game.graphics.Animation;
import com.commando.game.graphics.Sprite;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;
import com.commando.game.util.collision.TileCollision;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public abstract class Entity {

    //for animations ( png my_hero)
    protected final int FALLEN = 4;
    protected final int UP = 3;
    protected final int DOWN = 2;
    protected final int RIGHT = 0;
    protected final int LEFT = 1;

    protected int currentAnimation;

    protected TileCollision tileCollision;
    protected Animation animation;
    protected Sprite sprite;
    protected Vector2d position;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;

    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    protected boolean fallen;

    protected float speed_x;
    protected float speed_y;

    // to be set in each entity
    protected float maxSpeed;
    protected float acceleration;
    protected float deceleration;

    protected AABB hitBounds;
    protected AABB bounds;

    public Entity(Sprite sprite, Vector2d origin, int size) {
        this.sprite = sprite;
        this.position = origin;
        this.size = size;

        this.tileCollision = new TileCollision(this);
        this.animation = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2d(origin.x + (size / 2), origin.y), size, size);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    public void setSize(int i ) { this.size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcceleration(float f) { acceleration = f; }
    public void setDeceleration(float f) { deceleration = f; }
    public void setFallen(boolean b) { fallen = b; }

    public AABB getBounds() { return bounds; }
    public int getSize() { return size; }
    public Animation getAnimation() { return animation; }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(frames);
        animation.setDelay(delay);
    }

    public void animate() {
        if(up) {
            if(currentAnimation != UP || animation.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if(down) {
            if(currentAnimation != DOWN || animation.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(left) {
            if(currentAnimation != LEFT || animation.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if(right) {
            if(currentAnimation != RIGHT || animation.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else if(fallen) {
            if(currentAnimation != FALLEN || animation.getDelay() == -1) {
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }
        }
        else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }

    }


    private void setHitBoxDirection() {
        if(up) {
            hitBounds.setyOffSet(-size / 2);
            hitBounds.setxOffSet(-size / 2);
        }
        else if(down) {
            hitBounds.setyOffSet(size / 2);
            hitBounds.setxOffSet(size / 2);
        }
        else if(left) {
            hitBounds.setxOffSet(-size);
            hitBounds.setyOffSet(0);
        }
        else if(right) {
            hitBounds.setxOffSet(0);
            hitBounds.setxOffSet(0);
        }
    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public abstract void render(Graphics2D g);

    public void input(KeyHandler key, MouseHandler mouse) {  }

}
