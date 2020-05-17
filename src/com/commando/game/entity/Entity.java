package com.commando.game.entity;

import com.commando.game.graphics.Animation;
import com.commando.game.graphics.Sprite;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public abstract class Entity {

    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;

    protected int currentAnimation;

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

    protected float dx;
    protected float dy;

    protected float maxSpeed;
    protected float acceleration;
    protected float deceleration;

    public Entity(Sprite sprite, Vector2d origin, int size) {
        this.sprite = sprite;
        this.position = origin;
        this.size = size;

        this.animation = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

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
        else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
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
