package com.commando.game.states.menuOptions;

import com.commando.game.GamePanel;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.hub.Database;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static com.commando.game.states.GameStateManager.*;

/**
 * @author Timofti Gabriel
 */
public class LeaderBoard extends GameState {
    private Button buttonBack;

    private Font font;

    ArrayList<Integer> allScores;

    int noOfScores;

    public LeaderBoard(GameStateManager gameStateManager) throws SQLException {
        super(gameStateManager);

        font = new Font("resources\\font\\font.png", Font.BOLD, 48);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        buttonBack = new Button("BACK", imageButton, font, new Vector2d(GamePanel.width - 150,  50), 32, 16);
        buttonBack.addHoverImage(buttonBack.createButton("BACK", imageHover, font, buttonBack.getWidth(), buttonBack.getHeight(), 32, 20));

        buttonBack.addEvent(event -> { gameStateManager.pop(LEADER_BOARD); gameStateManager.add(MENU);});

        Database database = new Database();
        allScores = database.loadScore();
        allScores.sort(Collections.reverseOrder());

        for (Integer i : allScores) {
            System.out.println(i);
        }
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

        Image menuCover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\HelpCover.jpg");
        graphics.drawImage(menuCover, 0,0, GamePanel.width, GamePanel.height, null);

        SpriteSheet.drawArray(graphics, "Rank" , new Vector2d(   120, 150), 40, 25);
        SpriteSheet.drawArray(graphics, "Score" , new Vector2d(   260, 150), 40, 25);

        if (allScores. size() < 10) {
            noOfScores = allScores.size();
        }
        else {
            noOfScores = 10;
        }
        for (int i = 0; i < noOfScores; i++) {
            SpriteSheet.drawArray(graphics, i+1 + "      " + allScores.get(i), new Vector2d( 120, (200 + 40 * i)), 30, 20);

        }


        buttonBack.render(graphics);
    }
}
