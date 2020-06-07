package com.commando.game.entity.heroItems;

import com.commando.game.entity.GameObject;
import com.commando.game.entity.caracters.Enemy;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import static com.commando.game.util.hub.Define.*;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class Bullet extends Projectile {

    public Bullet(int size, Vector2d source, Vector2d destination) {
        super(size, source, destination);

        bounds = new AABB(new Vector2d(source.x, source.y), size);
        bounds.setWidth(10);
        bounds.setHeight(10);
        bounds.setxOffSet(0);
        bounds.setyOffSet(0);
        speed = new Vector2d(BULLET_SPEED, BULLET_SPEED); //bullet speed
    }

    public void update(Enemy enemy) {
        if (!bounds.collides(enemy.getBounds())) {
            Vector2d auxSpeed = new Vector2d(direction.x * speed.x, direction.y * speed.y);
            position = GameObject.add(position, auxSpeed);

            bounds.getPosition().x += auxSpeed.x;
            bounds.getPosition().y += auxSpeed.y;

        }
        else {
            System.out.println(enemy.getHealth());
            hit = true;
            enemy.setHealth(-YELLOW_BULLET_DAMAGE);
        }
    }

    public void render(Graphics2D graphics) {


        if(!hit) { //if the bullet
            Vector2d positionAux = GameObject.add(position, speed);
            graphics.setColor(Color.YELLOW);
            graphics.fillOval(
                    (int) (positionAux.getWorldVar().x), (int) (positionAux.getWorldVar().y), (size), (size));

            //graphics.setColor(Color.RED);
            //graphics.drawOval((int) (bounds.getPosition().getWorldVar().x), (int) (bounds.getPosition().getWorldVar().y), size, size);
        }
    }
}
