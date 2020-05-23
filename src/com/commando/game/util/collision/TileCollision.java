package com.commando.game.util.collision;

import com.commando.game.entity.Entity;
import com.commando.game.states.PlayState;
import com.commando.game.tiles.TileMapSolid;
import com.commando.game.tiles.blocks.Block;
import com.commando.game.tiles.blocks.SolidCollideBlock;

/**
 * @author Timofti Gabriel
 */
public class TileCollision {

    private Entity entity;
    private Block block;

    public TileCollision(Entity entity) {
        this.entity = entity;
    }

    // we check if the collision happens for tile Map
    public boolean collisionTile(float ax, float ay) {
        for (int c = 0; c < 4; c++) {
            int xt = (int) ((entity.getBounds().getPosition().x + ax) + (c % 2) * entity.getBounds().getWidth() + entity.getBounds().getxOffSet()) / PlayState.HERO_SIZE;
            int yt = (int) ((entity.getBounds().getPosition().y + ay) + (c / 2) * entity.getBounds().getHeight() + entity.getBounds().getyOffSet()) / PlayState.HERO_SIZE;

            if(TileMapSolid.tilemapSolid_Blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapSolid.tilemapSolid_Blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if (block instanceof SolidCollideBlock) {
                    return collisionFalling(ax, ay, xt, yt, block);
                }

                return block.update(entity.getBounds());
            }
        }

        return false;
    }

    private boolean collisionFalling(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) ((( (entity.getBounds().getPosition().x + ax) + entity.getBounds().getxOffSet() ) / PlayState.HERO_SIZE ) + entity.getBounds().getWidth() / PlayState.HERO_SIZE);
        int nextYt = (int) ((((entity.getBounds().getPosition().y + ay) + entity.getBounds().getyOffSet() ) / PlayState.HERO_SIZE) + entity.getBounds().getHeight() / PlayState.HERO_SIZE);

        //when it is fully inside only one block
        if(block.isInside(entity.getBounds())) {
            entity.setFallen(true);
            return false;
        }
        //when it at the border between 2 blocks but still fully in water
        else if((nextXt == yt + 1 ) || (nextXt == xt + 1) || (nextYt == yt - 1) || (nextXt == xt - 1) ) {
            if(TileMapSolid.tilemapSolid_Blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))) {
                if( entity.getBounds().getPosition().x > block.getPosition().x) {
                    entity.setFallen(true);
                }
                return false;
            }
        }
        entity.setFallen(false);
        return false;
    }
}
