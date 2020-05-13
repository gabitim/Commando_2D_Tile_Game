package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class GameStatesManager {

    private ArrayList<GameState> states;

    public static Vector2d map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int WIN = 3;
    public static final int LOSE = 4;

    public GameStatesManager() {
        map = new Vector2d(GamePanel.width, GamePanel.height);
        Vector2d.setWorldVar(map.x, map.y);
        states = new ArrayList<GameState>();

        states.add(new PlayState(this));
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void add(int state) {
        if(state == PLAY) {
            states.add(new PlayState(this));
        }
        if(state == MENU) {
            states.add(new MenuState(this));
        }
        if(state == PAUSE) {
            states.add(new PauseState(this));
        }
        if(state == WIN) {
            states.add(new WinState(this));
        }
        if(state == LOSE) {
            states.add(new LoseState(this));
        }

    }

    public void addAndpop(int state) {
        states.remove(0);
        add(state);
    }

    public void update() {
        Vector2d.setWorldVar(map.x, map.y);

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
