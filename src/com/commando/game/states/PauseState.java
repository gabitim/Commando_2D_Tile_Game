package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class PauseState extends GameState {
    public PauseState(GameStatesManager gameStatesManager) {
        super(gameStatesManager);
    }

    @Override
    public void update() {
        System.out.println("PAUSEd");
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g) {

    }
}
