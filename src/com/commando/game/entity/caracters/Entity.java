package com.commando.game.entity.caracters;

import com.commando.game.entity.GameObject;
import com.commando.game.graphics.Animation;
import com.commando.game.graphics.Sprite;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;
import com.commando.game.util.collision.TileCollision;

import static com.commando.game.util.hub.Define.*;

import java.awt.*;
import java.io.IOException;

/**
 * @author Timofti Gabriel
 */
public abstract class Entity extends GameObject {

    //for animations
    protected final int FALLEN = 4;
    protected final int UP = 3;
    protected final int DOWN = 2;
    protected final int LEFT = 1;
    protected final int RIGHT = 0;


    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;

    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;
    protected int invincibleTime;

    protected boolean fallen;
    protected boolean dead = false;
    protected boolean isInvincible = false;

    protected float speed_x;
    protected float speed_y;

    // to be set in each entity
    protected float maxSpeed;
    protected float acceleration;
    protected float deceleration;

    protected AABB directHitBounds;
    protected AABB bounds;

    public static int noOfLifes = 4;
    protected int health;
    protected int maxHealth;
    protected float healthPercent = 1;
    protected int defense = BASIC_DEFENSE;
    protected double damage;

    public Entity(SpriteSheet spriteSheet, Vector2d origin, int size) {
        this.spriteSheet = spriteSheet;
        this.position = origin;
        this.size = size;

        this.tileCollision = new TileCollision(this);
        this.animation = new Animation();
        setAnimation(RIGHT, spriteSheet.getSpriteArray(RIGHT), 10);

        bounds = new AABB(origin, size, size);
        directHitBounds = new AABB(origin, size, size);
        directHitBounds.setxOffSet(size / 2);
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
    public void setSize(int i ) { this.size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcceleration(float f) { acceleration = f; }
    public void setDeceleration(float f) { deceleration = f; }
    public void setFallen(boolean b) { fallen = b; }

    public void setAnimation(int i, Sprite[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(i, frames);
        animation.setDelay(delay);
    }

    private void setHitBoxDirection() {
        if(up) {
            directHitBounds.setyOffSet(-size / 2);
            directHitBounds.setxOffSet(0);
        }
        else if(down) {
            directHitBounds.setyOffSet(size / 2);
            directHitBounds.setxOffSet(0);
        }
        else if(left) {
            directHitBounds.setxOffSet(-size / 2);
            directHitBounds.setyOffSet(0);
        }
        else if(right) {
            directHitBounds.setxOffSet(size / 2);
            directHitBounds.setyOffSet(0);
        }
    }

    public void setHealth(int life) {
        health += life;
    }

    public void setHealth(int life, int force ) {
        health = life;
        if ( health <= 0) {
            dead = true;
        }

        damage += force;

        healthPercent = (float)health / (float)maxHealth;
    }


    public boolean getDeath() { return dead; }
    public int getHealth() { return health; }
    public float getHealthPercent() { return healthPercent; }
    public int getDefense() { return defense; }

    public Animation getAnimation() { return animation; }
    public AABB getDirectHitBounds() { return directHitBounds; }
    public AABB getBounds() { return bounds; }

    public void animate() {
        if(up) {
            if(currentAnimation != UP || animation.getDelay() == -1) {
                setAnimation(UP, spriteSheet.getSpriteArray(UP), 5);
            }
        }
        else if(down) {
            if(currentAnimation != DOWN || animation.getDelay() == -1) {
                setAnimation(DOWN, spriteSheet.getSpriteArray(DOWN), 5);
            }
        }
        else if(left) {
            if(currentAnimation != LEFT || animation.getDelay() == -1) {
                setAnimation(LEFT, spriteSheet.getSpriteArray(LEFT), 5);
            }
        }
        else if(right) {
            if(currentAnimation != RIGHT || animation.getDelay() == -1) {
                setAnimation(RIGHT, spriteSheet.getSpriteArray(RIGHT), 5);
            }
        }
        else if(fallen) {
            if(currentAnimation != FALLEN || animation.getDelay() == -1) {
                setAnimation(FALLEN, spriteSheet.getSpriteArray(FALLEN), 15);
            }
        }
        else {
            setAnimation(currentAnimation, spriteSheet.getSpriteArray(currentAnimation), -1);
        }

    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public abstract void render(Graphics2D g) throws IOException;

    public void input(KeyHandler key, MouseHandler mouse) {  }

}
