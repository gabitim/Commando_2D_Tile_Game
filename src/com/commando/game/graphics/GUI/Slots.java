package com.commando.game.graphics.GUI;


import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */
public class Slots {

    private Vector2d[] pos;
    private BufferedImage[] slot; // 1: slot, 2: front || top, 3: back || bottom
    private Slots[] childSlots;
    private Button button;

    private int width;
    private int height;

    private int size = 0;
    private boolean visibility = true;
    private boolean showChildren = false;

    // Once we have items we will create an items and inventory class
    public Slots(BufferedImage[] sprite, Vector2d[] pos, int size) {
        this.slot = sprite;
        this.pos = pos;
        this.size = size;

        if(sprite[0] != null) {
            this.width = sprite[0].getWidth() + size;
            this.height = sprite[0].getHeight() + size;
        }
    }

    public Slots(Button button, BufferedImage[] sprite, Vector2d[] pos, int size) {
        this(sprite, new Vector2d[]{ button.getPos(), pos[0], pos[1] }, size); // is this okay?
        this.button = button;

        this.width = button.getWidth() + size;
        this.height = button.getHeight() + size;

        this.button.setSlot(this); // temp fix
    }

    public Slots(Button button, Slots[] childSlots, BufferedImage[] sprite, Vector2d[] pos, int size) {
        this(sprite, new Vector2d[]{ button.getPos(), pos[0], pos[1] }, size); // is this okay?
        this.button = button;

        this.width = button.getWidth() + size;
        this.height = button.getHeight() + size;
        this.childSlots = childSlots;

        this.button.setSlot(this); // temp fix
    }

    public void setVisible(boolean b) { visibility = b; }
    public void showChildren(boolean b) { showChildren = (childSlots != null) ? b : false; }

    public Button getButton() { return button; }
    public boolean isVisibleChildren() { return showChildren; }

    public void update(double time) {
        button.update();

        if(showChildren) {
            for(int i = 0; i < childSlots.length; i++) {
                childSlots[i].update(time);
            }
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        button.input(mouse, key);

        if(showChildren) {
            for(int i = 0; i < childSlots.length; i++) {
                childSlots[i].input(mouse, key);
            }
        }
    }

    public void render(Graphics2D g) {

        if(!visibility) return;

        if(button != null) {
            button.render(g);
        } else {
            g.drawImage(slot[0], (int) pos[0].x, (int) pos[0].y, width, height, null);
        }
        for(int i = 1; i < slot.length; i++) {
            if(slot[i] == null) continue;
            g.drawImage(slot[i],
                    (int) pos[i].x - ((slot[i].getWidth() + size) / slot[i].getWidth()) + 1,
                    (int) pos[i].y - ((slot[i].getHeight() + size) / slot[i].getHeight()) + 1,
                    slot[i].getWidth() + size,
                    slot[i].getHeight() + size, null);
        }

        if(showChildren) {
            for(int i = 0; i < childSlots.length; i++) {
                childSlots[i].render(g);
            }
        }

    }

}
