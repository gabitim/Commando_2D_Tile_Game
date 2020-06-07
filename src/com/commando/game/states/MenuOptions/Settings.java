package com.commando.game.states.MenuOptions;

import com.commando.game.GamePanel;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameState;
import com.commando.game.states.GameStateManager;
import com.commando.game.util.hub.Define;
import com.commando.game.util.hub.Types;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.commando.game.states.GameStateManager.*;
import static com.commando.game.util.hub.Define.*;

/**
 * @author Timofti Gabriel
 */
public class Settings extends GameState {

    private Button buttonBack;
    private Button buttonMapPlains;
    private Button buttonMapDesert;
    private Button buttonVillager;
    private Button buttonWizard;

    private Font fontBig;
    private Font fontSmall;

    public static String SELECTED_MAP = Types.MAP_PLAINS;
    public static String SELECTED_CHARACTER = Types.CHARACTER_VILLAGER;
    public static int SELECTED_CHARACTER_SIZE = BASIC_HERO_SPRITE_SIZE;

    public Settings(GameStateManager gameStateManager) {
        super(gameStateManager);

        fontBig = new Font("resources\\font\\font.png", Font.BOLD, 48);
        fontSmall = new Font("resources\\font\\font.png", Font.BOLD, 24);


        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        buttonBack = new Button("BACK", imageButton, fontBig, new Vector2d(GamePanel.width - 150,  50), 32, 16);

        buttonMapPlains = new Button("PLAINS", imageButton, fontSmall, new Vector2d( 260,  230), 32, 16);
        buttonMapDesert = new Button("DESERT", imageButton, fontSmall, new Vector2d( 260,  290), 32, 16);
        buttonVillager = new Button("VILLAGER", imageButton, fontSmall, new Vector2d( 260,  510), 32, 16);
        buttonWizard = new Button("WIZARD", imageButton, fontSmall, new Vector2d( 260,  570), 32, 16);


        buttonBack.addHoverImage(buttonBack.createButton("BACK", imageHover, fontBig, buttonBack.getWidth(), buttonBack.getHeight(), 32, 20));

        buttonMapPlains.addHoverImage(buttonMapPlains.createButton("PLAINS", imageHover, fontSmall, buttonMapPlains.getWidth(), buttonMapPlains.getHeight(), 32, 20));
        buttonMapDesert.addHoverImage(buttonMapDesert.createButton("DESERT", imageHover, fontSmall, buttonMapDesert.getWidth(), buttonMapDesert.getHeight(), 32, 20));
        buttonVillager.addHoverImage(buttonVillager.createButton("VILLAGER", imageHover, fontSmall, buttonVillager.getWidth(), buttonVillager.getHeight(), 32, 20));
        buttonWizard.addHoverImage(buttonWizard.createButton("WIZARD", imageHover, fontSmall, buttonWizard.getWidth(), buttonWizard.getHeight(), 32, 20));


        buttonBack.addEvent(event -> { gameStateManager.pop(SETTINGS); gameStateManager.add(MENU);});
        buttonMapPlains.addEvent(event -> { SELECTED_MAP = Types.MAP_PLAINS; });
        buttonMapDesert.addEvent(event -> { SELECTED_MAP = Types.MAP_DESERT; });
        buttonVillager.addEvent(event -> { SELECTED_CHARACTER = Types.CHARACTER_VILLAGER; SELECTED_CHARACTER_SIZE = BASIC_HERO_SPRITE_SIZE; });
        buttonWizard.addEvent(event -> { SELECTED_CHARACTER = Types.CHARACTER_WIZARD; SELECTED_CHARACTER_SIZE = WIZARD_HERO_SPRITE_SIZE; });

    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
        buttonBack.input(mouse, key);
        buttonMapPlains.input(mouse, key);
        buttonMapDesert.input(mouse, key);
        buttonVillager.input(mouse, key);
        buttonWizard.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        Image menuCover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\Help_bg1.jpg");
        graphics.drawImage(menuCover, 0,0, GamePanel.width, GamePanel.height, null);

        SpriteSheet.drawArray(graphics, "SELECT A MAP", new Vector2d( 100, 100), 40, 40);
        SpriteSheet.drawArray(graphics, "SELECT A CHARACTER", new Vector2d( 100, 380), 40, 40);

        buttonBack.render(graphics);
        buttonMapPlains.render(graphics);
        buttonMapDesert.render(graphics);
        buttonVillager.render(graphics);
        buttonWizard.render(graphics);
    }
}
