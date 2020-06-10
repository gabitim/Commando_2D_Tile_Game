package com.commando.game.states.menuOptions;

import com.commando.game.GamePanel;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import static com.commando.game.states.GameStateManager.*;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */
public class Help extends GameState {

    private Button buttonBack;

    private Font font;

    public Help(GameStateManager gameStateManager) {
        super(gameStateManager);

        font = new Font("resources\\font\\font.png", Font.BOLD, 48);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        buttonBack = new Button("BACK", imageButton, font, new Vector2d(GamePanel.width - 150,  50), 32, 16);
        buttonBack.addHoverImage(buttonBack.createButton("BACK", imageHover, font, buttonBack.getWidth(), buttonBack.getHeight(), 32, 20));

        buttonBack.addEvent(event -> { gameStateManager.pop(HELP); gameStateManager.add(MENU);});
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        buttonBack.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        //graphics.setColor(Color.cyan);
        //graphics.fillRect(0,0, GamePanel.width, GamePanel.height);
        Image menuCover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\HelpCover.jpg");
        graphics.drawImage(menuCover, 0,0, GamePanel.width, GamePanel.height, null);

        buttonBack.render(graphics);

        SpriteSheet.drawArray(graphics, "INSTRUCTIONS", new Vector2d( 100, 100), 25, 25);
        SpriteSheet.drawArray(graphics, "FIND THE PORTAL TO THE NEXT LEVEL", new Vector2d( 50, 250), 20, 15);
        SpriteSheet.drawArray(graphics, "YOU CAN SHOOT BULLETS AND USE YOUR SPECIAL ABILITY WHEN PRESS SPACE", new Vector2d( 50, 290), 20, 15);
        SpriteSheet.drawArray(graphics, "EVERYONE WILL TRY TO STOP YOU AND THERE ARE ALSO TRAPS ALONG THE WAY! ", new Vector2d( 50, 330), 20, 15);
        SpriteSheet.drawArray(graphics, "     GOOD LUCK ! ", new Vector2d( 50, 390), 25, 20);
    }
}
