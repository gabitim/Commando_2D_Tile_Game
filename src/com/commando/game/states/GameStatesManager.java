package com.commando.game.states;

import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class GameStatesManager {

    private ArrayList<GameState> states;

    public GameStatesManager() {
        states = new ArrayList<GameState>();

        states.add(new PlayState(this));
    }

    public void update() {
        for(int i = 0; i < states.size(); i++){
            states.get(i).update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for(int i = 0; i < states.size(); i++){
            states.get(i).input(mouse, key);
        }
    }

    public void render(Graphics2D g) {
        for(int i = 0; i < states.size(); i++){
            states.get(i).render(g);
        }
    }
}
