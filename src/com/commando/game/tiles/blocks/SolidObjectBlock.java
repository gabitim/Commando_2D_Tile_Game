package com.commando.game.tiles.blocks;

import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class SolidObjectBlock extends Block{


    public SolidObjectBlock(BufferedImage image, Vector2d position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB aabb) {
        return true;
    }

    @Override
    public void render(Graphics2D graphics) {
        super.render(graphics);
        graphics.setColor(Color.RED);
        graphics.drawRect((int)position.getWorldVar().x, (int)position.getWorldVar().y, width , height );
    }
}
