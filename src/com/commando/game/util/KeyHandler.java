package com.commando.game.util;

import com.commando.game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timofti Gabriel
 */
public class KeyHandler implements KeyListener  {

    public static List<Key> keys = new ArrayList<Key>();

    public class Key {
        public int presses, absorbs;
        public boolean _down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {  // for keyboard
            if(pressed != _down) {
                _down = pressed;
            }
            if(pressed) {
                presses++;
            }
        }

        public void tick() {  // for mouse
            if(absorbs < presses) {
                absorbs++;
                clicked = true;
            }
            else {
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();

    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    public void releaseAll() {
        for(int i = 0; i < keys.size(); i++){
            keys.get(i)._down = false;
        }
    }

    public void tick() {
        for(int i = 0; i < keys.size(); i++){
            keys.get(i).tick();
        }
    }

    public void toggle(KeyEvent keyEvent, boolean pressed) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //nothing
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        toggle(keyEvent, true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        toggle(keyEvent, false);
    }
}
