package com.commando.game.states.levels;

import com.commando.game.states.GameStateManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public abstract class Level {

    protected LevelManager levelManager;

    public Level(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException;
    public abstract void render(Graphics2D graphics);
}
