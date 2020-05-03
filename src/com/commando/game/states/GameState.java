package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public abstract class GameState {

    private GameStatesManager gsm;

    public GameState(GameStatesManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
