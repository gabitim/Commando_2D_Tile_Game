package com.commando.game.entity.caracters.enemyTypes;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.Vector2d;

/**
 * @author Timofti Gabriel
 */
public class MobGoblin extends Enemy {
    public MobGoblin(SpriteSheet spriteSheet, Vector2d origin, int size) {
        super(spriteSheet, origin, size);
    }

    @Override
    public String toString() { return "MobGoblin"; }
}
