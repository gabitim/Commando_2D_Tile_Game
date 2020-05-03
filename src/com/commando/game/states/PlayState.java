package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class PlayState extends GameState {
    public PlayState(GameStatesManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(100, 100, 64, 64);
    }
}
