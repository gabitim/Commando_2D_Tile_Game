package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.entity.caracters.enemyTypes.MobGirl;
import com.commando.game.entity.caracters.enemyTypes.MobOrc;
import com.commando.game.entity.caracters.enemyTypes.MobSkeleton;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.TileCollision;
import com.commando.game.util.hub.Types;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.commando.game.states.GameStateManager.*;
import static com.commando.game.states.PlayState.*;
import static com.commando.game.states.levels.LevelManager.*;
import static com.commando.game.util.hub.Define.*;

/**
 * @author Timofti Gabriel
 */
public class Level3 extends Level {

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private int lastMobType = 0;


    public Level3(LevelManager levelManager, GameStateManager gameStateManager) throws ParserConfigurationException {
        super(levelManager);

        init();

        LevelManager.playState = new PlayState(gameStateManager);
        LevelManager.playState.init(enemies);

    }

    @Override
    public void init() {
        this.enemies.add(new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE));
        this.enemies.add(new MobSkeleton(new SpriteSheet(Types.MOB_SKELETON, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1200, ENEMY_POSITION_Y + 1000), ENTITY_SIZE));
        this.enemies.add(new MobOrc(new SpriteSheet(Types.MOB_ORC, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 700, ENEMY_POSITION_Y + 1300), ENTITY_SIZE));
    }

    @Override
    public Enemy initOneEnemy() {
        switch (lastMobType) {
            case 0: {
                lastMobType = 1;
                return new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE);
            }

            case 1: {
                lastMobType = 2;
                return new MobSkeleton(new SpriteSheet(Types.MOB_SKELETON, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1200, ENEMY_POSITION_Y + 1000), ENTITY_SIZE);
            }

            case 2: {
                lastMobType = 0;
                return new MobOrc(new SpriteSheet(Types.MOB_ORC, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 700, ENEMY_POSITION_Y + 1300), ENTITY_SIZE);
            }

            default: return new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X, ENEMY_POSITION_Y), ENTITY_SIZE);
        }
    }

    @Override
    public void update(boolean canPassToNext) throws ParserConfigurationException {
        canPassPlayState = canPassToNext;

        //System.out.println("level"+ (CURRENT_LEVEL+1) + (5 - TileCollision.timePassed / 1000));
        if ((5 - TileCollision.timePassed / 1000) == 0) {
            win = true;
            PlayState.canPassPlayState = false;
            LevelManager.canPassLevel = false;
            TileCollision.timePassed = 0;
            levelManager.pop(LEVEL3);
        }

        LevelManager.playState.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException, SQLException {
        LevelManager.playState.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        LevelManager.playState.render(graphics);
    }
}

