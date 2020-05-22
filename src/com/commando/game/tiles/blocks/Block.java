package com.commando.game.tiles.blocks;

import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public abstract class Block {
    protected int width;
    protected int height;

    protected BufferedImage image;
    protected Vector2d position;

    public Block(BufferedImage image, Vector2d position, int width, int height) {
        this.image = image;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public abstract boolean update(AABB player);

    public void render(Graphics2D graphics) {
        graphics.drawImage(image, (int)position.getWorldVar().x, (int)position.getWorldVar().y, width, height, null);
    }

    public abstract boolean isInside(AABB player);

}
