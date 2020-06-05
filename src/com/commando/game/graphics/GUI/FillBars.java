package com.commando.game.graphics.GUI;

import com.commando.game.entity.caracters.Entity;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class FillBars {
    private BufferedImage[] bar;

    private Entity entity;
    private Vector2d position;
    private int size;
    private int length;

    private int barWidthRatio;
    private int barHeightRatio;
    private int energyLength;
    private int energyWidthRatio;

    public FillBars(Entity entity, BufferedImage[] sprite, Vector2d position, int size, int length) {
        this.entity = entity;
        this.bar = sprite;
        this.position = position;
        this.size = size;
        this.length = length;

        //we need 2 sprites

        this.energyLength = (int) ((bar[0].getWidth() + size) * (length * entity.getHealthPercent()));
        this.barWidthRatio = (bar[0].getWidth() + size) * length / (bar[0].getWidth());
        this.energyWidthRatio =  energyLength / (bar[0].getWidth());
        this.barHeightRatio = (bar[0].getHeight() + size) / bar[0].getHeight();
    }

    public void render(Graphics2D graphics) {
        int endsWidth = 0;
        int centerHeight = (int) (position.y - barHeightRatio - bar[0].getHeight() / 2);

        this.energyLength = (int) ((bar[0].getWidth() + size) * (length * entity.getHealthPercent()));
        this.energyWidthRatio = this.energyLength / (bar[0].getWidth());

        if(bar[2] != null) {
            endsWidth = bar[2].getWidth() + size;

            graphics.drawImage(bar[2], (int) (position.x), (int) (position.y), endsWidth, bar[2].getHeight() + size, null);
            graphics.drawImage(bar[2], (int) (position.x + endsWidth * 2 + (bar[0].getWidth() + size) * length) - this.barWidthRatio, (int) (position.y), -(endsWidth), bar[2].getHeight() + size, null);
            centerHeight += bar[2].getHeight() / 2 + (bar[2].getHeight() - bar[0].getHeight()) / 2;
        }

        graphics.drawImage(bar[0], (int) (position.x + endsWidth - this.barWidthRatio), centerHeight, (bar[0].getWidth() + size) * length, bar[0].getHeight() + size, null);
        graphics.drawImage(bar[1], (int) (position.x + endsWidth - this.energyWidthRatio), centerHeight, this.energyLength, (int) (bar[0].getHeight() + size), null);
    }
}
