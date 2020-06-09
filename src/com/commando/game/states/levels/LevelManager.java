package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class LevelManager extends GameState {

    private static Level levels[];

    public static final int LEVEL1 = 0;
    public static final int LEVEL2 = 1;
    public static final int LEVEL3 = 2;

    public static int CURRENT_LEVEL;

    public static PlayState playState;
    public static boolean pause = true;

    public static boolean canPassLevel = false;

    public LevelManager(GameStateManager gameStateManager) throws ParserConfigurationException {
        super(gameStateManager);

        levels = new Level[3];

        CURRENT_LEVEL = 0;
        levels[LEVEL1] = new Level1(this, gameStateManager);
        PlayState.pause = false;
    }

    public static Enemy getEnemyByLevel(int level) {
        return levels[level].initOneEnemy();
    }

    public boolean isLevelActive(int level) { return levels[level] != null; }

    public void pop(int level) { levels[level] = null; }

    public void add(int level) throws ParserConfigurationException {
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
    }

    public void addAndPop(int level, int remove) throws ParserConfigurationException {
        add(level);
        pop(remove);
    }

    @Override
    public void update() throws ParserConfigurationException {
        for(int i = 0; i < levels.length; i++){
            if(levels[i] != null) {
                levels[i].update(canPassLevel);
            }
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
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
