package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.states.MenuOptions.Settings;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.util.hub.Types;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.commando.game.states.GameStateManager.*;

/**
 * @author Timofti Gabriel
 */
public class MenuState extends GameState {

    private Button buttonStart;
    private Button buttonLoadGame;
    private Button buttonSettings;
    private Button buttonHelp;
    private Button buttonQuit;
    private Font font;



    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        font = new Font("resources\\font\\font.png", Font.BOLD, 48);
        buttonStart = new Button("START", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 - 192), 32, 16);
        buttonLoadGame = new Button("LOAD GAME", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 - 96), 32, 16);
        buttonSettings = new Button("SETTINGS", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 + 0), 32, 16);
        buttonHelp = new Button("HELP", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 + 96), 32, 16);
        buttonQuit = new Button("QUIT", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 + 192), 32, 16);

        buttonStart.addHoverImage(buttonStart.createButton("START", imageHover, font, buttonStart.getWidth(), buttonStart.getHeight(), 32, 20));
        buttonLoadGame.addHoverImage(buttonLoadGame.createButton("LOAD GAME", imageHover, font, buttonLoadGame.getWidth(), buttonLoadGame.getHeight(), 32, 20));
        buttonSettings.addHoverImage(buttonSettings.createButton("SETTINGS", imageHover, font, buttonSettings.getWidth(), buttonSettings.getHeight(), 32, 20));
        buttonHelp.addHoverImage(buttonHelp.createButton("HELP", imageHover, font, buttonHelp.getWidth(), buttonHelp.getHeight(), 32, 20));
        buttonQuit.addHoverImage(buttonQuit.createButton("QUIT", imageHover, font, buttonQuit.getWidth(), buttonQuit.getHeight(), 32, 20));

        buttonStart.addEvent(event ->
        { gameStateManager.pop(MENU);

        gameStateManager.add(PLAY);
        PlayState.pause = false; } );

        //buttonLoadGame.addEvent( event -> { gameStateManager.pop(MENU); gameStateManager.add(LOAD); } );
        buttonSettings.addEvent( event -> { gameStateManager.pop(MENU); gameStateManager.add(SETTINGS); } );
        buttonHelp.addEvent(event -> { gameStateManager.pop(MENU); gameStateManager.add(HELP); });

        buttonQuit.addEvent(event -> { System.exit(0); } );

        PlayState.CURRENT_MAP = Settings.SELECTED_MAP;
        PlayState.CURRENT_HERO = Settings.SELECTED_CHARACTER;
        PlayState.CURRENT_HERO_SIZE = Settings.SELECTED_CHARACTER_SIZE;
    }

    @Override
    public void update() { }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
        buttonStart.input(mouse, key);
        buttonLoadGame.input(mouse, key);
        buttonSettings.input(mouse, key);
        buttonHelp.input(mouse, key);
        buttonQuit.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(0,0,GamePanel.width, GamePanel.height);
        Image menuCover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\menuCover2.gif");
        graphics.drawImage(menuCover, 0,0, GamePanel.width, GamePanel.height, null);

        buttonStart.render(graphics);
        buttonLoadGame.render(graphics);
        buttonSettings.render(graphics);
        buttonHelp.render(graphics);
        buttonQuit.render(graphics);
    }
}
