package com.commando.game.tiles.blocks;

import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class SolidCollideBlock extends Block {

    public SolidCollideBlock(BufferedImage image, Vector2d position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB aabb) {
        return false;
        //yeah
    }

    @Override
    public void render(Graphics2D graphics) {
        super.render(graphics);
        graphics.setColor(Color.GREEN);
        graphics.drawRect((int)position.getWorldVar().x, (int)position.getWorldVar().y, width, height);
    }
}