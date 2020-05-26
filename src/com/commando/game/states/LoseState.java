package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import java.awt.*;

import static com.commando.game.states.GameStateManager.PAUSE;

/**
 * @author Timofti Gabriel
 */
public class LoseState extends GameState {
    public LoseState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();

        if(key.escape._down) {
            gameStateManager.pop(PAUSE);
        }
    }

    @Override
    public void render(Graphics2D g) {

    }
}
