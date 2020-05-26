package com.commando.game.entity;

import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.PlayState;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;

import static com.commando.game.states.PlayState.SPAWN_POSITION_OFFSET_X;
import static com.commando.game.states.PlayState.SPAWN_POSITION_OFFSET_Y;

/**
 * @author Timofti Gabriel
 */
public class Hero extends Entity{

    public Hero(SpriteSheet spriteSheet, Vector2d origin, int size) {
        super(spriteSheet, origin, size);
        acceleration = 2f;
        deceleration = 0.3f;
        maxSpeed = 3f;

        //modify the 'box' of the player
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setxOffSet(12);
        bounds.setyOffSet(40);

    }

    public void update(Enemy enemy) {
        super.update();

        if(attack && hitBounds.collides(enemy.getBounds())) {
            System.out.println("I've been hit");
        }

        if(!fallen) {
            move();
            if (!tileCollision.collisionTile(speed_x, 0)) {
                PlayState.map.x += speed_x;
                position.x += speed_x;
            }
            if (!tileCollision.collisionTile(0, speed_y)) {
                PlayState.map.y += speed_y;
                position.y += speed_y;
            }
        }
        else {
            if (animation.hasPLayedOnce()) {
                resetPosition();
                speed_x = 0;
                speed_y = 0;
                fallen = false;

            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(
                (int)(position.getWorldVar().x + bounds.getxOffSet()),
                (int)(position.getWorldVar().y + bounds.getyOffSet()),
                (int)bounds.getWidth(),
                (int)bounds.getHeight()
        );

        if (attack) {
            graphics.setColor(Color.CYAN);
            graphics.drawRect(
                    (int)(hitBounds.getPosition().getWorldVar().x + hitBounds.getxOffSet()),
                    (int)(hitBounds.getPosition().getWorldVar().y + hitBounds.getyOffSet()),
                    (int)hitBounds.getWidth(),
                    (int)hitBounds.getHeight()
            ) ;
        }

        graphics.drawImage(animation.getImage().image, (int)(position.getWorldVar().x), (int)(position.getWorldVar().y), size, size, null );
    }

    private void move() {
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
            if(speed_y > maxSpeed) {
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

        if(!fallen) {
            // 1- left mouse button
            // 2 - scroll button
            //3 - right mouse button
            if(mouse.getButton() == 1) {
                //System.out.println("Player: " + position.x + ", " + position.y);
                this.attack = true;
            } else {
                this.attack = false;
            }

            if (key.up._down) {
                this.up = true; // extends
            } else {
                this.up = false;
            }
            if (key.down._down) {
                this.down = true; // extends
            } else {
                this.down = false;
            }
            if (key.left._down) {
                this.left = true; // extends
            } else {
                this.left = false;
            }
            if (key.right._down) {
                this.right = true; // extends
            } else {
                this.right = false;
            }
            if (up && down) {
                up = false;
                down = false;
            }
            if (right && left) {
                right = false;
                left = false;
            }
        }
        else {
            up = false;
            down = false;
            right = false;
            left = false;
            attack = false;
        }
    }

    public void resetPosition() {
        System.out.println("Reseting Player... ");

        position.x = PlayState.MIDDLE_OF_MAP_X + SPAWN_POSITION_OFFSET_X;
        PlayState.map.x = 0;

        position.y = PlayState.MIDDLE_OF_MAP_Y + SPAWN_POSITION_OFFSET_Y;
        PlayState.map.y = 0;

        setAnimation(RIGHT, spriteSheet.getSpriteArray(RIGHT), 10);
    }
}
