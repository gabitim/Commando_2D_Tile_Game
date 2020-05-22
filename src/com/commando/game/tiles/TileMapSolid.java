package com.commando.game.tiles;

import com.commando.game.graphics.Sprite;
import com.commando.game.tiles.blocks.Block;
import com.commando.game.tiles.blocks.SolidObjectBlock;
import com.commando.game.tiles.blocks.SolidCollideBlock;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Timofti Gabriel
 */
public class TileMapSolid extends TileMap {

    public static final int WATER = 392;
    public static final int SPIKES = 583;

    public static HashMap<String, Block> tilemapSolid_Blocks;

    public TileMapSolid(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tilemapSolid_Blocks = new HashMap<String, Block>();

        String[] block = data.split(",");
        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                if(temp == SPIKES) { // the borders (for collision)
                    tempBlock = new SolidCollideBlock(
                            sprite.getSprite((int)((temp - 1) % tileColumns) , (int) ((temp - 1) / tileColumns) ) ,
                            new Vector2d((int)((i % width) * tileWidth), (int)((i / height) * tileHeight) ) ,
                            tileWidth,
                            tileHeight
                    );
                } else  {
                    tempBlock = new SolidObjectBlock(
                            sprite.getSprite((int)((temp - 1) % tileColumns) , (int)((temp - 1) / tileColumns) ) ,
                            new Vector2d((int)((i % width) * tileWidth), (int)((i / height) * tileHeight) ) ,
                            tileWidth,
                            tileHeight
                    );
                }
                tilemapSolid_Blocks.put(String.valueOf((int)(i % width)) + "," + String.valueOf((int)(i / height)), tempBlock);

            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        for (Block block: tilemapSolid_Blocks.values()) {
            block.render(graphics);
        }
    }
}
