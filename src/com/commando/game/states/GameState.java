package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */
public abstract class GameState {

    protected GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void update() throws ParserConfigurationException, SQLException;
    public abstract void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException, SQLException;
    public abstract void render(Graphics2D graphics) throws SQLException;
}
