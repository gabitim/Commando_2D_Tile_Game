package com.commando.game.util.collision;

import com.commando.game.entity.caracters.Entity;
import com.commando.game.states.levels.LevelManager;
import com.commando.game.tiles.TileMapSolid;
import com.commando.game.tiles.blocks.Block;
import com.commando.game.tiles.blocks.SolidFallingBlock;
import com.commando.game.tiles.blocks.SolidNextLevelBlock;

import static com.commando.game.util.hub.Define.*;

/**
 * @author Timofti Gabriel
 */
public class TileCollision {

    private Entity entity;

    private double timeEntered;
    public static int timePassed;

    public TileCollision(Entity entity) {
        this.entity = entity;
    }

    // we check if the collision happens for tile Map
    public boolean collisionTile(float ax, float ay) {
        for (int c = 0; c < 4; c++) {
            int xt = (int) ((entity.getBounds().getPosition().x + ax) + (c % 2) * entity.getBounds().getWidth() + entity.getBounds().getxOffSet()) / ENTITY_SIZE;
            int yt = (int) ((entity.getBounds().getPosition().y + ay) + (c / 2) * entity.getBounds().getHeight() + entity.getBounds().getyOffSet()) / ENTITY_SIZE;

            if(TileMapSolid.tilemapSolid_Blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapSolid.tilemapSolid_Blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if (block instanceof SolidFallingBlock) {
                    return collisionFalling(ax, ay, xt, yt, block);
                } else if( block instanceof SolidNextLevelBlock) {
                    return collisionFalling(ax, ay, xt, yt, block);
                }

                return block.update(entity.getBounds());
            }
        }

        return false;
    }

    private boolean collisionFalling(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) ((( (entity.getBounds().getPosition().x + ax) + entity.getBounds().getxOffSet() ) / ENTITY_SIZE ) + entity.getBounds().getWidth() / ENTITY_SIZE);
        int nextYt = (int) ((((entity.getBounds().getPosition().y + ay) + entity.getBounds().getyOffSet() ) / ENTITY_SIZE) + entity.getBounds().getHeight() / ENTITY_SIZE);

        //when it is fully inside only one block
        if(block.isInside(entity.getBounds())) {
            if(block instanceof SolidFallingBlock) {
                entity.setFallen(true);
            }
            else {
                LevelManager.canPassLevel = true;
                System.out.println(timePassed + ", " +timeEntered);
                if(timeEntered == 0 )
                    timeEntered = System.currentTimeMillis();
                timePassed = (int)(System.currentTimeMillis() - timeEntered)  ;

                return false;
            }
            return false;

        }
        //when it at the border between 2 blocks; still fully in trap
        else if((nextXt == yt + 1 ) || (nextXt == xt + 1) || (nextYt == yt - 1) || (nextXt == xt - 1) ) {
            if(TileMapSolid.tilemapSolid_Blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))) {
                if( entity.getBounds().getPosition().x > block.getPosition().x) {
                    entity.setFallen(true);

                }
                return false;
            }
        }
        entity.setFallen(false);
        timeEntered = 0;
        LevelManager.canPassLevel = false;
        return false;
    }
}
