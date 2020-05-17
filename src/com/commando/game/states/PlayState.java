package com.commando.game.states;

import com.commando.game.graphics.Font;
import com.commando.game.graphics.Sprite;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.io.File;

/**
 * @author Timofti Gabriel
 */
public class PlayState extends GameState {
    private Font font;

    public PlayState(GameStatesManager gameStatesManager) {
        super(gameStatesManager);
        font = new Font("E:\\JAVA_GAME\\Commando\\resources\\font\\ZeldaFont.png", 16, 16);

    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(key.up._down) {
            System.out.println("WAAAAAAAAA");
        }
    }

    @Override
    public void render(Graphics2D g) {
        // wirte words on the screen ! works only letters
        Sprite.drawArray(g, font, "Abcd01A", new Vector2d(100, 100), 16, 16, 16 ,  0);
    }
}
