package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public abstract class GameState {

    protected GameStatesManager gameStatesManager;

    public GameState(GameStatesManager gameStatesManager) {
        this.gameStatesManager = gameStatesManager;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException;
    public abstract void render(Graphics2D g);
}
