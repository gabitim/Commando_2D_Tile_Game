package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.entity.caracters.Hero;
import com.commando.game.entity.caracters.enemyTypes.MobGirl;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.TileCollision;
import com.commando.game.util.hub.DataForLoad;
import com.commando.game.util.hub.Types;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.commando.game.states.PlayState.*;
import static com.commando.game.states.levels.LevelManager.*;
import static com.commando.game.util.hub.Define.ENTITY_SIZE;
import static com.commando.game.util.hub.Define.MOB_GIRL_SPRITE_SIZE;

/**
 * @author Timofti Gabriel
 */
public class CustomLevel extends Level {

    private DataForLoad dataForLoad;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public CustomLevel(LevelManager levelManager, GameStateManager gameStateManager, DataForLoad dataForLoad) throws ParserConfigurationException {
        super(levelManager);

        this.dataForLoad = dataForLoad;
        init();
        playState = new PlayState(gameStateManager);
        playState.init(enemies);
    }

    @Override
    public void init() {
        this.enemies.add(new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE));
    }

    @Override
    public Enemy initOneEnemy() {
        return new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE);
    }

    @Override
    public void update(boolean canPassToNext) throws ParserConfigurationException {
        canPassPlayState = canPassToNext;

        //System.out.println("level"+ (CURRENT_LEVEL+1) + (5 - TileCollision.timePassed / 1000));
        if ((5 - TileCollision.timePassed / 1000) == 0) {
            noOfLives = Hero.noOfLifes;
            totalDamage = Hero.totalDamage;
            LevelManager.CURRENT_LEVEL = CURRENT_LEVEL + 1;
            PlayState.canPassPlayState = false;
            LevelManager.canPassLevel = false;
            TileCollision.timePassed = 0;
            levelManager.pop(LEVEL1);
            levelManager.add(LEVEL2);
        }

        playState.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException, SQLException {
        playState.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        playState.render(graphics);
    }
}
