package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.collision.TileCollision;
import com.commando.game.util.hub.DataForLoad;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.sql.SQLException;

import static com.commando.game.states.GameStateManager.*;

/**
 * @author Timofti Gabriel
 */
public class LevelManager extends GameState {

    private DataForLoad dataForLoad;

    private static Level levels[];

    public static final int LEVEL1 = 0;
    public static final int LEVEL2 = 1;
    public static final int LEVEL3 = 2;
    public static final int CUSTOM_LEVEL = 3;

    public static int CURRENT_LEVEL;

    public static PlayState playState;
    public static boolean pause = true;

    public static boolean canPassLevel = false;
    public static boolean lose = false;
    public static boolean win = false;

    public static int noOfLives;
    public static int totalDamage;

    public static double timeOfStart;

    public LevelManager(GameStateManager gameStateManager) throws Exception {
        super(gameStateManager);

        win = false;
        lose = false;
        noOfLives = 4;
        totalDamage = 0;
        TileCollision.timePassed = 0;

        levels = new Level[4];

        CURRENT_LEVEL = LEVEL1;
        PlayState.pause = false;
        add(CURRENT_LEVEL);
        timeOfStart = System.currentTimeMillis();
    }

    public LevelManager(GameStateManager gameStateManager, DataForLoad dataForLoad) throws Exception {
        super(gameStateManager);
        this.dataForLoad = dataForLoad;

        win = false;
        lose = false;
        noOfLives = dataForLoad.getNoOfLifes();
        totalDamage = dataForLoad.getTotalDamage();
        TileCollision.timePassed = 0;

        levels = new Level[4];

        CURRENT_LEVEL = CUSTOM_LEVEL;
        PlayState.pause = false;
        add(CURRENT_LEVEL);
        timeOfStart = dataForLoad.getTimePassed();
    }

    public static Enemy getEnemyByLevel(int level) {
        try {
            System.out.println(level);
            return levels[level].initOneEnemy();
        }
        catch (Exception e) {
            System.out.println("ERROR IN LEVEL MANAGER -> ENEMY CREATION ");
            return levels[level].initOneEnemy();
        }
    }

    public boolean isLevelActive(int level) { return levels[level] != null; }

    public void pop(int level) { levels[level] = null; }

    public void add(int level) throws Exception {
        if(levels[level] != null ) return;

        if (level == LEVEL1 ) {
            levels[LEVEL1] = new Level1(this, gameStateManager);
        }

        if (level == LEVEL2 ) {
            levels[LEVEL2] = new Level2(this, gameStateManager);
        }

        if (level == LEVEL3 ) {
            levels[LEVEL3] = new Level3(this, gameStateManager);
        }

        if(level == CUSTOM_LEVEL) {
            levels[CUSTOM_LEVEL] = new CustomLevel(this, gameStateManager, dataForLoad);
        }
    }

    public void addAndPop(int level, int remove) throws Exception {
        add(level);
        pop(remove);
    }

    @Override
    public void update() throws Exception {
        if(lose) {
            gameStateManager.pop(LEVELS);
            gameStateManager.add(LOSE);
        }

        if(win) {
            gameStateManager.pop(LEVELS);
            gameStateManager.add(WIN);
        }

        for(int i = 0; i < levels.length; i++){
            if(levels[i] != null) {
                levels[i].update(canPassLevel);
            }
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        for(int i = 0; i < levels.length; i++){
            if(levels[i] != null) {
                levels[i].input(mouse, key);
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        for(int i = 0; i < levels.length; i++){
            if(levels[i] != null) {
                levels[i].render(graphics);
            }
        }
    }
}
