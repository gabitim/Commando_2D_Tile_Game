package com.commando.game.tiles.blocks;

import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class NormalBlock extends Block {

    public NormalBlock(BufferedImage image, Vector2d position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB aabb) {
        return false;
    }

    @Override
    public void render(Graphics2D graphics) {
        super.render(graphics);
    }
}
