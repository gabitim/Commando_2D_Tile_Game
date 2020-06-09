package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.entity.caracters.Hero;
import com.commando.game.graphics.GUI.Button;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.commando.game.states.GameStateManager.*;

/**
 * @author Timofti Gabriel
 */
public class LoseState extends GameState {

    private com.commando.game.graphics.GUI.Button buttonMenu;
    private Button buttonPlayAgain;

    private Font font;
    public LoseState(GameStateManager gameStateManager) {
        super(gameStateManager);

        font = new Font("resources\\font\\font.png", Font.BOLD, 32);

        BufferedImage imageButton = GameStateManager.button.getSubImage(0,0,121,26);
        BufferedImage imageHover = GameStateManager.button.getSubImage(0, 29, 122, 28);

        buttonMenu = new Button("BACK TO MENU", imageButton, font, new Vector2d(450,  450), 32, 16);
        buttonPlayAgain = new Button("PLAY AGAIN", imageButton, font, new Vector2d(900, 450), 32, 16);

        buttonMenu.addHoverImage(buttonMenu.createButton("BACK TO MENU", imageHover, font, buttonMenu.getWidth(), buttonMenu.getHeight(), 32, 20));
        buttonPlayAgain.addHoverImage(buttonPlayAgain.createButton("PLAY AGAIN", imageHover, font, buttonPlayAgain.getWidth(), buttonPlayAgain.getHeight(), 32, 20));

        buttonMenu.addEvent( event -> { gameStateManager.pop(LOSE); gameStateManager.add(MENU); } );
        buttonPlayAgain.addEvent( event -> { gameStateManager.pop(LOSE); gameStateManager.add(LEVELS); } );

    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
        buttonMenu.input(mouse, key);
        buttonPlayAgain.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        Image cover = Toolkit.getDefaultToolkit().getImage("resources\\covers\\LoseCover.jpg");
        graphics.drawImage(cover, 0,0, GamePanel.width, GamePanel.height, null);

        SpriteSheet.drawArray(graphics, "YOU DIED !", new Vector2d( GamePanel.width / 2 - 200, GamePanel.height / 2 - 150), 80, 50);

        buttonMenu.render(graphics);
        buttonPlayAgain.render(graphics);
    }
}
