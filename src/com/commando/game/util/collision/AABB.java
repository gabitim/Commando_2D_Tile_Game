package com.commando.game.util.collision;

import com.commando.game.entity.Entity;
import com.commando.game.util.Vector2d;


/**
 * @author Timofti Gabriel
 */
public class AABB {

    private Vector2d position;
    private float width;
    private float height;
    private float radius;
    private int size;
    private Entity entity;

    private float surfaceArea;

    private float xOffSet = 0;
    private float yOffSet = 0;

    public AABB(Vector2d position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;

        size = Math.max(width, height);
    }

    public AABB(Vector2d position, int radius, Entity entity) {
        this.position = position;
        this.radius = radius;
        this.size = radius;
        this.entity = entity;
    }

    public AABB(Vector2d position, int radius) {
        this.position = position;
        this.radius = radius;
        this.surfaceArea = (float) Math.PI * (radius * radius);
        size = radius;
    }

    public Vector2d getPosition() { return position; }

    public float getRadius() { return radius; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }

    public void setBox(Vector2d position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;

        size = Math.max(width, height);
    }

    public void setCircle(Vector2d position, int radius) {
        this.position = position;
        this.radius = radius;

        size = radius;
    }

    public void setWidth(float i) { width = i; }
    public void setHeight(float i) { height = i; }

    public void setxOffSet(float f) { xOffSet = f; }
    public void setyOffSet(float f) { yOffSet = f; }

    public float getxOffSet() { return xOffSet; }
    public float getyOffSet() { return yOffSet; }

    // check collision method
    public boolean collides(AABB box) {
        float ax = ((position.getWorldVar().x + (xOffSet)) + (width / 2));  // this object
        float ay = ((position.getWorldVar().y + (yOffSet)) + (height / 2));  // this object

        float bx = ((box.position.getWorldVar().x + box.xOffSet ) + (box.getWidth() / 2));    // box object
        float by = ((box.position.getWorldVar().y + box.yOffSet ) + (box.getHeight() / 2));    // box object

        if(Math.abs(ax - bx) < (this.width / 2) + (box.getWidth() / 2)) {
            if(Math.abs(ay - by) < (this.height / 2) + (box.getHeight() / 2)) {
                return true;
            }
        }

        return false;
    }

    public boolean collisionCircleBox(AABB box) {
        float dx = Math.max(
                box.getPosition().getWorldVar().x + box.getxOffSet(),
                Math.min(
                        position.getWorldVar().x + (radius / 2 ),
                        box.getPosition().getWorldVar().x + box.getxOffSet() + box.getWidth()
                        )
                            );

        float dy = Math.max(
                box.getPosition().getWorldVar().y + box.getyOffSet(),
                Math.min(
                        position.getWorldVar().y + (radius / 2 ),
                        box.getPosition().getWorldVar().y + box.getyOffSet() + box.getHeight()
                )
        );

        dx = position.getWorldVar().x + (radius / 2) - dx;
        dy = position.getWorldVar().y + (radius / 2) - dy;

        if( Math.sqrt(dx * dx + dy * dy) < radius / 2) {
            return true;
        }

        return false;
    }
}

