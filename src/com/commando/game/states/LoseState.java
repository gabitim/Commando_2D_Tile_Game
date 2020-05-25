package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import java.awt.*;

import static com.commando.game.states.GameStatesManager.PAUSE;

/**
 * @author Timofti Gabriel
 */
public class LoseState extends GameState {
    public LoseState(GameStatesManager gameStatesManager) {
        super(gameStatesManager);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();

        if(key.escape._down) {
            gameStatesManager.pop(PAUSE);
        }
    }

    @Override
    public void render(Graphics2D g) {

    }
}
