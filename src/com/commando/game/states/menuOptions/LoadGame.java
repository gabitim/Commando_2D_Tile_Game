package com.commando.game.states.menuOptions;

import com.commando.game.GamePanel;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.hub.DataForLoad;
import com.commando.game.util.hub.Database;
import com.commando.game.graphics.GUI.Button;

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
public class LoadGame extends GameState {

    private ArrayList<DataForLoad> savedStates;

    private Button[] loadButtons;
    private Button buttonBack;

    private Font largeFont;
    private Font smallFont;

    int supportedNoOfSaves;

    public LoadGame(GameStateManager gameStateManager) {
        super(gameStateManager);

        largeFont = new Font("resources\\font\\font.png", Font.BOLD, 48);
        smallFont = new Font("resources\\font\\font.png", Font.BOLD, 32);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        Database database = new Database();
        savedStates = database.loadState(); // we separate creation of data object from its use
        Collections.reverse(savedStates);

        if(savedStates.size() < 10) {
            supportedNoOfSaves = savedStates.size();
        }
        else {
            supportedNoOfSaves = 10;
        }

        loadButtons = new Button[supportedNoOfSaves];

        for(int i = 0; i < supportedNoOfSaves; i++) {
            String buttonName = savedStates.get(i).getName();
            loadButtons[i] = new Button(buttonName, imageButton, smallFont, new Vector2d(250, 100 + (i * 60)), 32, 16);
            loadButtons[i].addHoverImage(loadButtons[i].createButton(buttonName, imageHover, smallFont, loadButtons[i].getWidth(), loadButtons[i].getHeight(), 32, 20 ));
        }

        buttonBack = new Button("BACK", imageButton, largeFont, new Vector2d(GamePanel.width - 150,  50), 32, 16);
        buttonBack.addHoverImage(buttonBack.createButton("BACK", imageHover, largeFont, buttonBack.getWidth(), buttonBack.getHeight(), 32, 20));

        buttonBack.addEvent(event -> { gameStateManager.pop(LOAD); gameStateManager.add(MENU);});

        for(int i = 0; i < supportedNoOfSaves; i++) {
            int finalI = i;
            loadButtons[i].addEvent(event -> {
                gameStateManager.pop(LOAD);
                gameStateManager.setDataForLoad(savedStates.get(finalI)); // we load the selected save!
                gameStateManager.add(LEVELS);

            } );
        }

    }

    @Override
    public void update() {  }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        buttonBack.input(mouse, key);

        for(int i = 0; i < supportedNoOfSaves; i++) {
            loadButtons[i].input(mouse, key);
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        Image menuCover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\HelpCover.jpg");
        graphics.drawImage(menuCover, 0,0, GamePanel.width, GamePanel.height, null);

        buttonBack.render(graphics);
        for(int i = 0; i < supportedNoOfSaves; i++) {
            loadButtons[i].render(graphics);
        }
    }
}
