package com.commando.game.states.levels;

import com.commando.game.GamePanel;
import com.commando.game.entity.caracters.Enemy;
import com.commando.game.entity.caracters.Hero;
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
import java.util.ArrayList;

import static com.commando.game.states.PlayState.*;
import static com.commando.game.states.levels.LevelManager.*;
import static com.commando.game.util.hub.Define.*;

/**
 * @author Timofti Gabriel
 */

public class Level1 extends Level {

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Level1(LevelManager levelManager, GameStateManager gameStateManager) throws ParserConfigurationException {
        super(levelManager);

        init();

        LevelManager.playState = new PlayState(gameStateManager);
        LevelManager.playState.init(enemies);
    }

    public void init() {
        this.enemies.add(new MobSkeleton(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE));
    }

    public Enemy initOneEnemy() {
        return new MobSkeleton(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE);
    }

    @Override
    public void update(boolean canPassToNext) throws ParserConfigurationException {
        canPassPlayState = canPassToNext;

        System.out.println("level"+ (CURRENT_LEVEL+1) + (5 - TileCollision.timePassed / 1000));
        if ((5 - TileCollision.timePassed / 1000) == 0) {
            noOfLives = Hero.noOfLifes;
            totalDamage = Hero.totalDamage;
            LevelManager.CURRENT_LEVEL = LEVEL2;
            PlayState.canPassPlayState = false;
            LevelManager.canPassLevel = false;
            TileCollision.timePassed = 0;
            levelManager.pop(LEVEL1);
            levelManager.add(LEVEL2);
        }

        LevelManager.playState.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
        LevelManager.playState.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        LevelManager.playState.render(graphics);
    }
}
