package com.commando.game.entity;

import com.commando.game.entity.GameObject.GameObjectID;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class Enemy extends Entity{

    private AABB detect;
    private int radius;

    public Enemy(SpriteSheet spriteSheet, Vector2d origin, int size) {
        super(spriteSheet, origin, size);

        acceleration = 1f;
        deceleration = 0.3f;
        maxSpeed = 2f;

        radius = 1000;

        bounds.setWidth(160);
        bounds.setHeight(160);
        bounds.setxOffSet(-40);
        bounds.setyOffSet(-40);

        detect = new AABB(new Vector2d(origin.x + size / 2 - radius / 2, origin.y + size / 2 - radius / 2), radius);

        health = 300;
        maxHealth = 300;
    }

    @Override
    public GameObjectID getId() {
        return null;
    }

    @Override
    public Vector2d getPosition() {
        return null;
    }

    public void update(Hero hero, boolean pause) {

        if(!pause) {
            //move(hero);
            super.update();
            if(!fallen) {
                //System.out.println("Still alive");
                if (!tileCollision.collisionTile(speed_x, 0)) {
                    detect.getPosition().x += speed_x;
                    position.x += speed_x;
                }

                if (!tileCollision.collisionTile(0, speed_y)) {
                    detect.getPosition().y += speed_y;
                    position.y += speed_y;
                }
                if (bounds.collides(hero.getBounds())) {
                    if ( !hero.fallen) {
                        hero.health -= 1.5;
                    }
                }

            }
            else {
                //System.out.println("Now dead ");
                destroy();
            }
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

        graphics.drawImage(animation.getImage().image, (int)(position.getWorldVar().x), (int)(position.getWorldVar().y), size, size, null);
    }

    private void move(Hero hero) {
        if (detect.collisionCircleBox(hero.getBounds())) {
            System.out.println("inside");
            if (position.y > hero.position.y + 1) {
                speed_y -= acceleration;

                up = true;
                down = false;
                if (speed_y < -maxSpeed) {
                    speed_y = -maxSpeed;
                }
            } else if (position.y < hero.position.y) {
                speed_y += acceleration;

                down = true;
                up = false;
                if (speed_y > maxSpeed) {
                    speed_y = maxSpeed;
                }
            } else {
                speed_y = 0;
                up = false;
                down = false;
            }

            if (position.x > hero.position.x + 1) {
                speed_x -= acceleration;

                left = true;
                right = false;
                if (speed_x < -maxSpeed) {
                    speed_x = -maxSpeed;
                }
            } else if (position.x < hero.position.x - 1) {
                speed_x += acceleration;
                right = true;
                left = false;
                if (speed_x > maxSpeed) {
                    speed_x = maxSpeed;
                }
            } else {
                speed_x = 0;
                right = false;
                left = false;
            }
        }
        else {
            up = false;
            down = false;
            left = false;
            right = false;

            speed_x = 0;
            speed_y = 0;
        }
    }

    private void destroy() {
    }
}

