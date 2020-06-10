package com.commando.game.states.menuOptions;

import com.commando.game.GamePanel;
import com.commando.game.entity.caracters.Hero;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.states.levels.Level;
import com.commando.game.states.levels.LevelManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.hub.Database;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.commando.game.states.GameStateManager.*;

/**
 * @author Timofti Gabriel
 */
public class SaveGame extends GameState {

    private Button buttonBack;
    private Button buttonSave;

    private Font font;

    private boolean isActive;
    String saveName;
    String enemyInfo = "";

    double currentTime;

    public SaveGame(GameStateManager gameStateManager) {
        super(gameStateManager);

        isActive = true;
        font = new Font("resources\\font\\font.png", Font.BOLD, 48);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0, 0, 121, 26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        buttonBack = new Button("BACK", imageButton, font, new Vector2d(GamePanel.width - 150, 50), 32, 16);
        buttonSave = new Button("SAVE GAME", imageButton, font, new Vector2d( 300, 150), 32, 16);

        buttonBack.addHoverImage(buttonBack.createButton("BACK", imageHover, font, buttonBack.getWidth(), buttonBack.getHeight(), 32, 20));
        buttonSave.addHoverImage(buttonSave.createButton("SAVE GAME", imageHover, font, buttonSave.getWidth(), buttonSave.getHeight(), 32, 20));

        buttonBack.addEvent(event -> { isActive = false; render(graphics); gameStateManager.pop(SAVE); });
        buttonSave.addEvent( event -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.now();
            saveName = "Save " + dateTimeFormatter.format(date);

            Database database = new Database();
            System.out.println(getEnemyInfo());

            currentTime = System.currentTimeMillis();

            database.saveState(
                    saveName,
                    LevelManager.CURRENT_LEVEL,
                    LevelManager.playState.hero.getHealth(),
                    (int)LevelManager.playState.hero.getPosition().x,
                    (int)LevelManager.playState.hero.getPosition().y,
                    LevelManager.playState.hero.getNoOfLifes(),
                    getEnemyInfo(),
                    Hero.totalDamage,
                    (int)((currentTime - LevelManager.timeOfStart ) / 1000),
                    LevelManager.playState.getCurrentMap(),
                    LevelManager.playState.getCurrentHero(),
                    LevelManager.playState.getCurrentHeroSize(),
                    (int)LevelManager.playState.getMapX(),
                    (int)LevelManager.playState.getMapY()
                    );
        } );


    }

    private String getEnemyInfo() {
        enemyInfo = "";
        for (int i = 0; i < LevelManager.playState.enemies.size(); i++) {
            enemyInfo += i + ";"
                    + LevelManager.playState.enemies.get(i).toString() + ";"
                    + LevelManager.playState.enemies.get(i).getHealth() + ";"
                    + (int)LevelManager.playState.enemies.get(i).getPosition().x + ";"
                    + (int)LevelManager.playState.enemies.get(i).getPosition().y + ";\n" ;
        }

        return enemyInfo;
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        buttonBack.input(mouse, key);
        buttonSave.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        Image menuCover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\HelpCover.jpg");
        graphics.drawImage(menuCover, 0, 0, GamePanel.width, GamePanel.height, null);

        if(isActive) {

            buttonBack.render(graphics);
            buttonSave.render(graphics);
        }

    }

}
