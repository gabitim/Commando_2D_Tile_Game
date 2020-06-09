package com.commando.game.entity.caracters;

import com.commando.game.GamePanel;
import com.commando.game.entity.heroItems.Bullet;
import com.commando.game.entity.GameObjectID;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.PlayState;
import com.commando.game.states.levels.LevelManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.util.ArrayList;

import static com.commando.game.states.PlayState.*;
import static com.commando.game.util.hub.Define.HERO_LIFE;

/**
 * @author Timofti Gabriel
 */
public class Hero extends Entity {

    double currentTime;
    double oldTime = 0;

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private static boolean fired = false;

    public static int totalDamage;

    Image lifeIcon;
    SpriteSheet lifeSprite;
    int lifeIconStartPosition = GamePanel.width - 200;

    public Hero(SpriteSheet spriteSheet, Vector2d origin, int size) {
        super(spriteSheet, origin, size);
        acceleration = 2f;
        deceleration = 0.3f;
        maxSpeed = 3f;

        //initialize the box of the player
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setxOffSet(12);
        bounds.setyOffSet(40);

        health = HERO_LIFE;
        maxHealth = HERO_LIFE;

        lifeSprite = new SpriteSheet("resources\\gui\\items.png");

        totalDamage = LevelManager.totalDamage;
        noOfLifes = LevelManager.noOfLives;
    }

    @Override
    public GameObjectID getId() {
        return GameObjectID.Hero;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public void update(ArrayList<Enemy> enemies, boolean pause) {
        if (!pause) {
            super.update();

            for (Enemy enemy : enemies) {
                if (attack && directHitBounds.collides(enemy.getBounds())) {
                    System.out.println("I've been hit");
                }
            }

            setHealth(health , 0);
            if (health < 0) fallen = true;

            if (!fallen ) {
                //System.out.println(health);
                move();
                if (fired) {
                    for(Bullet bullet : bullets) {
                        if(!bullet.isHit())
                            for (Enemy enemy : enemies) {
                                bullet.update(enemy);
                            }
                    }
                }

                if (!tileCollision.collisionTile(speed_x, 0)) {
                    PlayState.map.x += speed_x;
                    position.x += speed_x;
                }
                if (!tileCollision.collisionTile(0, speed_y)) {
                    PlayState.map.y += speed_y;
                    position.y += speed_y;
                }
            } else {
                if (animation.hasPLayedOnce()) {
                    health = 500;
                    resetPosition();
                    speed_x = 0;
                    speed_y = 0;
                    fallen = false;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {

        if (attack) {
            graphics.setColor(Color.CYAN);
            graphics.drawRect(
                    (int)(directHitBounds.getPosition().getWorldVar().x + directHitBounds.getxOffSet()),
                    (int)(directHitBounds.getPosition().getWorldVar().y + directHitBounds.getyOffSet()),
                    (int) directHitBounds.getWidth(),
                    (int) directHitBounds.getHeight()
            ) ;
        }

        //render the life icons
        for(int i = 0; i < noOfLifes; i++) {
            lifeIcon = lifeSprite.getSubImage(0, 32, 16, 16);
            graphics.drawImage(lifeIcon, lifeIconStartPosition + i * 40, GamePanel.height - 100, 40, 40, null);
        }

        //render the hero
        graphics.drawImage(animation.getImage().image, (int)(position.getWorldVar().x), (int)(position.getWorldVar().y), size, size, null );

        //render the bullets
        if(fired) {
            for(Bullet bullet : bullets) {
                if(!bullet.isHit())
                    bullet.render(graphics);
            }
        }
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

        if (!pause) {
            if (!fallen) {
                // 1- left mouse button
                // 2 - scroll button
                //3 - right mouse button
                if (mouse.getButton() == 1) {
                    currentTime = System.currentTimeMillis();

                    if ( currentTime - oldTime > 100) {
                        bullets.add(new Bullet(10, new Vector2d(position.x + 40, position.y + 32),
                                new Vector2d(mouse.getX() + SPAWN_POSITION_OFFSET_X, mouse.getY() + SPAWN_POSITION_OFFSET_Y)));
                        fired = true;

                        oldTime = System.currentTimeMillis();
                    }
                }

                this.attack = key.attack._down;
                this.up = key.up._down; // extends
                this.down = key.down._down; // extends
                this.left = key.left._down; // extends
                this.right = key.right._down; // extends
                if (up && down) {
                    up = false;
                    down = false;
                }
                if (right && left) {
                    right = false;
                    left = false;
                }
            } else {
                up = false;
                down = false;
                right = false;
                left = false;
                attack = false;
            }
        }
    }

    public void resetPosition() {
        System.out.println("Reseting Player... ");

        noOfLifes--;
        if(noOfLifes == 0) {
            LevelManager.lose = true;
        }
        position.x = PlayState.MIDDLE_OF_MAP_X + SPAWN_POSITION_OFFSET_X;
        PlayState.map.x = 0;

        position.y = PlayState.MIDDLE_OF_MAP_Y + SPAWN_POSITION_OFFSET_Y;
        PlayState.map.y = 0;

        setAnimation(RIGHT, spriteSheet.getSpriteArray(RIGHT), 10);
    }
}
