package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.levels.LevelManager;
import com.commando.game.states.menuOptions.Help;
import com.commando.game.states.menuOptions.LoadGame;
import com.commando.game.states.menuOptions.SaveGame;
import com.commando.game.states.menuOptions.Settings;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.hub.Types;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

/**
 * @author Timofti Gabriel
 */
public class GameStateManager {

    private GameState states[];

    public static Vector2d map;

    public static final int LEVELS = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int WIN = 3;
    public static final int LOSE = 4;

    //Options
    public static final int LOAD  = 5;
    public static final int SETTINGS = 6;
    public static final int HELP = 7;
    public static final int SAVE = 8;


    public static com.commando.game.graphics.Font font;
    public static SpriteSheet button;
    public static Graphics2D graphics;

    public double currentTime;
    public double oldTime = 0;

    public GameStateManager(Graphics2D graphics) throws ParserConfigurationException {
        GameStateManager.graphics = graphics;
        map = new Vector2d(GamePanel.width, GamePanel.height);
        Vector2d.setWorldVar(map.x, map.y);

        states = new GameState[9];

        Types.loadPaths(); // we load all the paths
        font = new com.commando.game.graphics.Font("resources\\font\\font.png", 10, 10); //  my font
        SpriteSheet.currentFont = font;

        button = new SpriteSheet("resources\\gui\\buttons.png", 122, 57);

        states[MENU] = new MenuState(this);
    }

    public GameState getState(int state) {
        return states[state];
    }

    public boolean isStateActive(int state) {
        return states[state] != null;
    }

    public void pop(int state) {
        states[state] = null;
    }

    public void add(int state) throws ParserConfigurationException {
        if(states[state] != null) return;

        if(state == LEVELS) {
            states[LEVELS] = new LevelManager(this);
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

        if(state == LOAD) {
            states[LOAD] = new LoadGame(this);
        }
        if(state == SETTINGS) {
            states[SETTINGS] = new Settings(this);
        }
        if(state == HELP) {
            states[HELP] = new Help(this);
        }
        if(state == SAVE) {
            states[SAVE] = new SaveGame(this);
        }
    }

    public void addAndpop(int state) throws ParserConfigurationException {
       addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove) throws ParserConfigurationException {
        pop(remove);
        add(state);
    }

    public void update() throws ParserConfigurationException {
        for(int i = 0; i < states.length; i++){
            if(states[i] != null) {
                states[i].update();
            }
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
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
