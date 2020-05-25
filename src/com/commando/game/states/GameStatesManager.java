package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.graphics.Sprite;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class GameStatesManager {

    private GameState states[];

    public static Vector2d map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int WIN = 3;
    public static final int LOSE = 4;

    public int onTopState = 0;

    public static com.commando.game.graphics.Font font;

    public GameStatesManager() throws ParserConfigurationException {
        map = new Vector2d(GamePanel.width, GamePanel.height);
        Vector2d.setWorldVar(map.x, map.y);

        states = new GameState[5];

        font = new com.commando.game.graphics.Font("resources\\font\\font.png", 10, 10); //  my font
        Sprite.currentFont = font;

        states[PLAY] = new PlayState(this);
    }

    public void pop(int state) {
        states[state] = null;
    }

    public void add(int state) throws ParserConfigurationException {
        if(states[state] != null) return;

        if(state == PLAY) {
            states[PLAY] = new PlayState(this);
        }
        if(state == MENU) {
            states[MENU] = new MenuState(this);
        }
        if(state == PAUSE) {
            states[PAUSE] = new PauseState(this);
        }
        if(state == WIN) {
            states[WIN] = new WinState(this);
        }
        if(state == LOSE) {
            states[LOSE] = new LoseState(this);
        }

    }

    public void addAndpop(int state) throws ParserConfigurationException {
       addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove) throws ParserConfigurationException {
        pop(state);
        add(state);
    }

    public void update() {
        for(int i = 0; i < states.length; i++){
            if(states[i] != null) {
                states[i].update();
            }
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for(int i = 0; i < states.length; i++){
            if(states[i] != null) {
                states[i].input(mouse, key);
            }
        }
    }

    public void render(Graphics2D graphics) {
        for(int i = 0; i < states.length; i++){
            if(states[i] != null) {
                states[i].render(graphics);
            }
        }
    }
}
