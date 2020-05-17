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

    // check collision method
    public boolean collides(AABB box) {
        float ax = ((position.getWorldVar().x + (xOffSet)) + (width / 2));  // this object
        float ay = ((position.getWorldVar().y + (yOffSet)) + (height / 2));  // this object

        float bx = ((box.position.getWorldVar().x + (box.xOffSet / 2)) + (width / 2));    // box object
        float by = ((box.position.getWorldVar().y + (box.yOffSet / 2)) + (height / 2));    // box object

        if(Math.abs(ax - bx) < (this.width / 2) + (box.width / 2)) {
            if(Math.abs(ay - by) < (this.height / 2) + (box.height / 2)) {
                return true;
            }
        }

        return false;
    }

    public boolean collisionCircleBox(AABB box) {
        //this object
        float cx = (float) (position.getWorldVar().x + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
        float cy = (float) (position.getWorldVar().y + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));

        float xDelta = cx - Math.max(box.position.getWorldVar().x + (box.getWidth() / 2 ),
                Math.min(cx, box.position.getWorldVar().x));

        float yDelta = cy - Math.max(box.position.getWorldVar().y + (box.getWidth() / 2 ),
                Math.min(cy, box.position.getWorldVar().y));

        if((xDelta * xDelta + yDelta * yDelta) < ((this.radius / Math.sqrt(2)) * (this.radius / Math.sqrt(2))) ) {
            return true;
        }

        return false;

    }
}

