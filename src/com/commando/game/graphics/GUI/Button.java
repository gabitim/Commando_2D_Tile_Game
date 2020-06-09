package com.commando.game.graphics.GUI;

/**
 * @author Timofti Gabriel
 */

import com.commando.game.GamePanel;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameStateManager;
import com.commando.game.util.ClickedEvent;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.AABB;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;

public class Button {

    private String label;
    private int buttonWidth;
    private int buttonHeight;

    private BufferedImage image;
    private BufferedImage hoverImage;
    private BufferedImage pressedImage;

    private Vector2d iPos;
    private Vector2d lbPos;

    private AABB bounds;
    private boolean hovering = false;
    private int hoverSize;
    private ArrayList<ClickedEvent> events;
    private ArrayList<SlotEvent> slotevents;

    private boolean clicked = false;
    private boolean pressed = false;
    private boolean canHover = true;
    private boolean drawString = true;

    private float pressedtime;
    private Slots slot; // temp fix

    private static double currentTime;
    private static double oldTime = 0;

    // ******************************************** ICON CUSTOM POS *******************************************

    public Button(BufferedImage icon, BufferedImage image, Vector2d pos, int width, int height, int iconsize) {
        this.image = createIconButton(icon, image, width + iconsize, height + iconsize, iconsize);
        this.iPos = pos;
        this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());

        events = new ArrayList<ClickedEvent>();
        slotevents = new ArrayList<SlotEvent>();
        this.canHover = false;
        this.drawString = false;
    }

    private BufferedImage createIconButton(BufferedImage icon, BufferedImage image, int width, int height, int iconsize) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        if(image.getWidth() != width || image.getHeight() != height) {
            image = resizeImage(image, width, height);
        }

        if(icon.getWidth() != width - iconsize || icon.getHeight() != height - iconsize) {
            icon = resizeImage(icon, width - iconsize, height - iconsize);
        }

        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        g.drawImage(icon,
                image.getWidth() / 2 - icon.getWidth() / 2,
                image.getHeight() / 2 - icon.getHeight() / 2,
                icon.getWidth(), icon.getHeight(), null);

        g.dispose();

        return result;
    }

    // ******************************************** LABEL TTF CUSTOM MIDDLE POS *******************************************

    public Button(String label, BufferedImage image, Font font, Vector2d pos, int buttonSize) {
        this(label, image, font, pos, buttonSize, -1);
    }

    public Button(String label, BufferedImage image, Font font, Vector2d pos, int buttonWidth, int buttonHeight) {
        GameStateManager.graphics.setFont(font);
        FontMetrics met = GameStateManager.graphics.getFontMetrics(font);
        int height = met.getHeight();
        int width = met.stringWidth(label);

        if(buttonWidth == -1) buttonWidth = buttonHeight;

        this.label = label;

        this.image = createButton(label, image, font, width + buttonWidth, height + buttonHeight, buttonWidth, buttonHeight);
        this.iPos = new Vector2d(pos.x - this.image.getWidth() / 2, pos.y - this.image.getHeight() / 2);
        this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());


        events = new ArrayList<ClickedEvent>();
        this.canHover = false;
        this.drawString = false;
    }

    public BufferedImage createButton(String label, BufferedImage image, Font font, int width, int height, int buttonWidth, int buttonHeight) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        if(image.getWidth() != width || image.getHeight() != height) {
            image = resizeImage(image, width, height);
        }

        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        g.setFont(font);
        g.drawString(label, buttonWidth / 2, (height - buttonHeight));

        g.dispose();

        return result;
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();

        g.drawImage(temp, 0, 0, null);
        g.dispose();

        return result;
    }

    // ******************************************** LABEL PNG GAMEPANEL POS *******************************************

    public Button(String label, int buttonWidth, int buttonHeight, BufferedImage image, int iWidth, int iHeight, Vector2d offset) {
        this(label, buttonWidth, buttonHeight, image, iWidth, iHeight);

        iPos = new Vector2d((GamePanel.width / 2 - iWidth / 2 + offset.x) , (GamePanel.height / 2 - iHeight / 2 + offset.y));
        lbPos = new Vector2d((iPos.x + iWidth / 2 + buttonWidth / 2) - ((label.length()) * buttonWidth / 2), iPos.y + iHeight / 2 - buttonHeight / 2 - 4);

        this.bounds = new AABB(iPos, iWidth, iHeight);
    }

    public Button(String label, int buttonWidth, int buttonHeight, BufferedImage image, int iWidth, int iHeight) {
        this.label = label;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.image = image;
        this.hoverSize = 20;

        iPos = new Vector2d((GamePanel.width / 2 - iWidth / 2) , (GamePanel.height / 2 - iHeight / 2));
        lbPos = new Vector2d((iPos.x + iWidth / 2 + buttonWidth / 2) - ((label.length()) * buttonWidth / 2), iPos.y + iHeight / 2 - buttonHeight / 2 - 4);

        this.bounds = new AABB(iPos, iWidth, iHeight);

        events = new ArrayList<ClickedEvent>();
    }

    // ******************************************** LABEL PNG CUSTOM POS *******************************************

    public Button(String label, int buttonWidth, int buttonHeight, BufferedImage image, Vector2d iPos, int iWidth, int iHeight) {
        this(label, new Vector2d((iPos.x + iWidth / 2 + buttonWidth / 2) - ((label.length()) * buttonWidth / 2), iPos.y + iHeight / 2 - buttonHeight / 2 - 4), buttonWidth, buttonHeight, image, iPos, iWidth, iHeight);
    }

    public Button(String label, Vector2d lbPos, int buttonWidth, int buttonHeight, BufferedImage image, Vector2d iPos, int iWidth, int iHeight) {
        this(label, buttonWidth, buttonHeight, image, iWidth, iHeight);

        this.iPos = iPos;
        this.lbPos = lbPos;

        this.bounds = new AABB(iPos, iWidth, iHeight);
    }

    // ******************************************** END ************************************************************

    public void addHoverImage(BufferedImage image) {
        this.hoverImage = image;
        this.canHover = true;
    }

    public void addPressedImage(BufferedImage image) {
        this.pressedImage = image;
    }

    public void setHoverSize(int size) { this.hoverSize = size; }
    public boolean getHovering() { return hovering; }
    public void setHover(boolean b) { this.canHover = b; }
    public void addEvent(ClickedEvent e) { events.add(e);}
    public void addSlotEvent(SlotEvent e) { slotevents.add(e); }
    public void setSlot(Slots slot) { this.slot = slot;} // temp fix

    public int getWidth() { return (int) bounds.getWidth(); }
    public int getHeight() { return (int) bounds.getHeight(); }
    public Vector2d getPos() { return bounds.getPosition(); }

    public void update() {
        if(pressedImage != null && pressed ) {
            pressed = false;
        }
    }

    private void hover(int value) {
        if(hoverImage == null) {
            iPos.x -= value / 2;
            iPos.y -= value / 2;
            float iWidth = value + bounds.getWidth();
            float iHeight = value + bounds.getHeight();
            this.bounds = new AABB(iPos, (int) iWidth, (int) iHeight);

            lbPos.x -= value / 2;
            lbPos.y -= value / 2;
            buttonWidth += value / 3;
            buttonHeight += value / 3;

        }

        hovering = true;
    }

    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException, SQLException {
        if(bounds.inside(mouse.getX(), mouse.getY())) {
            //System.out.println("isInside" + mouse.getX() + " " + mouse.getY());
            if(canHover && !hovering) {
                hover(hoverSize);
            }

            currentTime = System.currentTimeMillis();

            if(mouse.getButton() == 1 && !clicked && (currentTime - oldTime > 500)) {

                oldTime = System.currentTimeMillis();
                System.out.println("Button Pressed");

                clicked = true;
                pressed = true;


                for(int i = 0; i < events.size(); i++) {
                    events.get(i).action(1);
                }
                if(slotevents == null) return;
                for(int i = 0; i < slotevents.size(); i++) {
                    slotevents.get(i).action(slot);
                }
            } else if(mouse.getButton() == -1) {
                clicked = false;
            }
        } else if(canHover && hovering) {
            hover(-hoverSize);
            hovering = false;
        }
    }

    public void render(Graphics2D g) {
        if(drawString) {
            SpriteSheet.drawArray(g, label, lbPos, buttonWidth, buttonHeight);
        }

        if(canHover && hoverImage != null && hovering) {
            g.drawImage(hoverImage, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        } else if(pressedImage != null && pressed) {
            g.drawImage(pressedImage, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        } else {
            g.drawImage(image, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        }

    }

    public interface SlotEvent {
        void action(Slots slot);
    }

}
