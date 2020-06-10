package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */
public abstract class Level {

    protected LevelManager levelManager;

    public Level(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public abstract void init() throws Exception;
    public abstract Enemy initOneEnemy();
    public abstract void update(boolean canPassToNext) throws Exception;
    public abstract void input(MouseHandler mouse, KeyHandler key) throws Exception;
    public abstract void render(Graphics2D graphics);
}
