package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.states.levels.LevelManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.TileCollision;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.cert.CertPathValidatorException;
import java.sql.SQLException;

import static com.commando.game.states.GameStateManager.*;
import static com.commando.game.states.PlayState.canPassPlayState;
import static com.commando.game.states.levels.LevelManager.canPassLevel;

/**
 * @author Timofti Gabriel
 */
public class PauseState extends GameState {

    private Button buttonResume;
    private Button buttonSaveGame;
    private Button buttonMenu;
    private Button buttonExit;
    private Font font;

    public PauseState(GameStateManager gameStateManager) {
        super(gameStateManager);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        font = new Font("resources\\font\\font.png", Font.BOLD, 48);
        buttonResume = new Button("RESUME", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 - 96), 32, 16);
        buttonSaveGame = new Button("SAVE GAME", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 + 0), 32, 16);
        buttonMenu = new Button("MENU", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 + 96), 32, 16);
        buttonExit = new Button("EXIT", imageButton, font, new Vector2d(GamePanel.width / 2, GamePanel.height / 2 + 192), 32, 16);


        buttonResume.addHoverImage(buttonResume.createButton("RESUME", imageHover, font, buttonResume.getWidth(), buttonResume.getHeight(), 32, 20));
        buttonSaveGame.addHoverImage(buttonSaveGame.createButton("SAVE GAME", imageHover, font, buttonSaveGame.getWidth(), buttonSaveGame.getHeight(), 32, 20));
        buttonMenu.addHoverImage(buttonMenu.createButton("MENU", imageHover, font, buttonMenu.getWidth(), buttonMenu.getHeight(), 32, 20));
        buttonExit.addHoverImage(buttonExit.createButton("EXIT", imageHover, font, buttonExit.getWidth(), buttonExit.getHeight(), 32, 20));


        buttonResume.addEvent(event -> { PlayState.pause = false; gameStateManager.pop(PAUSE);  } );
        buttonMenu.addEvent( event -> {
            PlayState.canPassPlayState = false;
            LevelManager.canPassLevel = false;
            TileCollision.timePassed = 0;
            gameStateManager.pop(LEVELS);
            gameStateManager.pop(PAUSE);
            gameStateManager.add(MENU); } );
        buttonSaveGame.addEvent(event -> { gameStateManager.add(SAVE);  });
        buttonExit.addEvent(event -> { System.exit(0); } );
    }

    @Override
    public void update() {
        //System.out.println("PAUSEd");
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException, SQLException {
        buttonResume.input(mouse, key);
        buttonSaveGame.input(mouse, key);
        buttonExit.input(mouse, key);
        buttonMenu.input(mouse, key);

    }

    @Override
    public void render(Graphics2D graphics) {
        buttonResume.render(graphics);
        buttonSaveGame.render(graphics);
        buttonMenu.render(graphics);
        buttonExit.render(graphics);

    }
}
