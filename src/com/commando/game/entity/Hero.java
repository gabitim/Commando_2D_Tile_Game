package com.commando.game.entity;

import com.commando.game.graphics.Sprite;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class Hero extends Entity{

    public Hero(Sprite sprite, Vector2d origin, int size) {
        super(sprite, origin, size);
    }

    public void update() {
        super.update();
        move();
        position.x += speed_x;
        position.y += speed_y;
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(animation.getImage(), (int)position.x, (int)position.y, size, size, null );
    }

    public void move() {
        if(up) {
            speed_y -= acceleration;
            if(speed_y < -maxSpeed) {
                speed_y = -maxSpeed;
            }
        } else {
            if(speed_y < 0) {
                speed_y += deceleration;
                if(speed_y > 0) {
                    speed_y = 0;
                }
            }
        }

        if(down) {
            speed_y += acceleration;
            if(speed_y < maxSpeed) {
                speed_y = maxSpeed;
            }
        } else {
            if(speed_y > 0) {
                speed_y -= deceleration;
                if(speed_y < 0) {
                    speed_y = 0;
                }
            }
        }

        if(left) {
            speed_x -= acceleration;
            if(speed_x < -maxSpeed) {
                speed_x = -maxSpeed;
            }
        } else {
            if(speed_x < 0) {
                speed_x += deceleration;
                if(speed_x > 0) {
                    speed_x = 0;
                }
            }
        }

        if(right) {
            speed_x += acceleration;
            if(speed_x > maxSpeed) {
                speed_x = maxSpeed;
            }
        } else {
            if(speed_x > 0) {
                speed_x -= deceleration;
                if(speed_x < 0) {
                    speed_x = 0;
                }
            }
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if(mouse.getButton() == 1) {
            System.out.println("Player: " + position.x + ", " + position.y);
        }

        if(key.up._down) {
            this.up = true; // extends
        } else {
            this.up = false;
        }
        if(key.down._down) {
            this.down = true; // extends
        } else {
            this.down = false;
        }
        if(key.left._down) {
            this.left = true; // extends
        } else {
            this.left = false;
        }
        if(key.right._down) {
            this.right = true; // extends
        } else {
            this.right = false;
        }
        if(key.attack._down) {
            this.attack = true; // extends
        } else {
            this.attack = false;
        }
    }
}
