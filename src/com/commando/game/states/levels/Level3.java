package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class Level3 extends Level {
    public Level3(LevelManager levelManager, GameStateManager gameStateManager) throws ParserConfigurationException {
        super(levelManager);

        LevelManager.playState = new PlayState(gameStateManager);
    }

    @Override
    public void init() {

    }

    @Override
    public Enemy initOneEnemy() {
        return null;
    }

    @Override
    public void update() {
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

