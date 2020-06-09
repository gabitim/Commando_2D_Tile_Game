package com.commando.game.tiles.blocks;

import com.commando.game.graphics.Sprite;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class SolidNextLevelBlock extends Block {
    public SolidNextLevelBlock(Sprite image, Vector2d position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB player) {
        return false;
    }

    @Override
    public void render(Graphics2D graphics) {
        super.render(graphics);


    }

    @Override
    public boolean isInside(AABB player) {
        if ( player.getPosition().x + player.getxOffSet() < position.x) return false;
        if ( player.getPosition().y + player.getyOffSet() < position.y) return false;

        if ( width + position.x < player.getWidth() + (player.getPosition().x + player.getxOffSet()) ) return false;
        if ( height + position.y < player.getHeight() + (player.getPosition().y + player.getyOffSet()) ) return false;

        return true;
    }
}
