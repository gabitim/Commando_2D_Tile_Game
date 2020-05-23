package com.commando.game.util;

import static com.commando.game.states.PlayState.SPAWN_POSITION_OFFSET_X;
import static com.commando.game.states.PlayState.SPAWN_POSITION_OFFSET_Y;

/**
 * @author Timofti Gabriel
 */
public class Vector2d {
    public float x;
    public float y;

    public static float worldX;
    public static float worldY;

     public Vector2d() {
        x = 0;
        y = 0;
    }

    public Vector2d(Vector2d vec) {
        new Vector2d(vec.x, vec.y);
    }

    public Vector2d(float x, float y) {
         this.x = x;
         this.y = y;
    }
    public void addX(float f) { x += f; }
    public void addY(float f) { y += f; }

    public void setX(float f) { x = f; }
    public void setY(float f) { y = f; }

    public void setVector(Vector2d vec) {
         this.x = vec.x;
         this.y = vec.y;
    }

    public void setVector(float x, float y) {
         this.x = x;
         this.y = y;
    }

    public static void setWorldVar(float x, float y) {
         worldX = x + SPAWN_POSITION_OFFSET_X;
         worldY = y + SPAWN_POSITION_OFFSET_Y;
    }

    public Vector2d getWorldVar() {
         return new Vector2d(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
         return x + ", " + y;
    }
}
