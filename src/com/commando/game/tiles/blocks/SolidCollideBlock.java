package com.commando.game.tiles.blocks;

import com.commando.game.graphics.Sprite;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import javax.imageio.ImageTranscoder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class SolidCollideBlock extends Block {

    public SolidCollideBlock(Sprite image, Vector2d position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB player) {

        System.out.println(" I am falling in water");

        return false;
    }

    @Override
    public void render(Graphics2D graphics) {
        super.render(graphics);
        graphics.setColor(Color.GREEN);
        graphics.drawRect((int)position.getWorldVar().x, (int)position.getWorldVar().y, width, height);
    }

    public boolean isInside(AABB player) {
        if ( player.getPosition().x + player.getxOffSet() < position.x) return false;
        if ( player.getPosition().y + player.getyOffSet() < position.y) return false;

        if ( width + position.x < player.getWidth() + (player.getPosition().x + player.getxOffSet()) ) return false;
        if ( height + position.y < player.getHeight() + (player.getPosition().y + player.getyOffSet()) ) return false;

        return true;
    }

}
