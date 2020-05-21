package com.commando.game.tiles;

import com.commando.game.graphics.Sprite;
import com.commando.game.tiles.blocks.Block;
import com.commando.game.tiles.blocks.NormalBlock;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class TileMapNormal extends TileMap {

    private ArrayList<Block> blocks;

    public TileMapNormal(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        blocks = new ArrayList<Block>();

        String[] block = data.split(",");
        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                blocks.add(new NormalBlock(
                        sprite.getSprite((int)((temp - 1) % tileColumns ), (int) ((temp - 1) / tileColumns) ) ,
                        new Vector2d((int)((i % width) * tileWidth), (int)((i / height) * tileHeight)) ,
                        tileWidth,
                        tileHeight
                ));
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        for(int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(graphics);
        }
    }
}
