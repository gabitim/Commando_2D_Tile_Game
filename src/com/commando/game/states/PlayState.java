package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.entity.Hero;
import com.commando.game.graphics.Font;
import com.commando.game.graphics.Sprite;
import com.commando.game.tiles.TileManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

/**
 * @author Timofti Gabriel
 */
public class PlayState extends GameState {

    private Font font;
    private Hero hero;
    private TileManager tileManager;

    public PlayState(GameStatesManager gameStatesManager) throws ParserConfigurationException {
        super(gameStatesManager);

        tileManager = new TileManager("resources\\map\\map1_plains.xml");
        font = new Font("resources\\font\\font.png", 10, 10);
        hero = new Hero(new Sprite("resources\\entity\\Hero1.png"), new Vector2d(300, 300), 64);
    }

    @Override
    public void update() {
        hero.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        hero.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {

        //render the map
        tileManager.render(graphics);

        //the FPS counter
        Sprite.drawArray(graphics, font, GamePanel.oldFrameCount + " FPS", new Vector2d(GamePanel.width - 100, 20), 12, 12   , 12 ,  0);

        //render the hero
        hero.render(graphics);
    }
}
