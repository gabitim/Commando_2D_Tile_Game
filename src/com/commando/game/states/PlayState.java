package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.entity.Enemy;
import com.commando.game.entity.Hero;
import com.commando.game.graphics.Font;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.tiles.TileManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

import static com.commando.game.states.GameStateManager.PAUSE;

/**
 * @author Timofti Gabriel
 */
public class PlayState extends GameState {

    public static final int SPAWN_POSITION_OFFSET_X = 350;
    public static final int SPAWN_POSITION_OFFSET_Y = 550;

    public static final int MIDDLE_OF_MAP_X = (GamePanel.width / 2) - 32;
    public static final int MIDDLE_OF_MAP_Y = (GamePanel.height / 2) - 32;
    public static final int HERO_SIZE = 64;

    public static final int ENEMY_POSITION_X = MIDDLE_OF_MAP_X + SPAWN_POSITION_OFFSET_X +  250;
    public static final int ENEMY_POSITION_Y = MIDDLE_OF_MAP_Y + SPAWN_POSITION_OFFSET_Y +  250;


    private Font font;
    private Hero hero;
    private Enemy enemy;
    //private Enemy enemy1;
    private TileManager tileManager;

    public static Vector2d map;

    public PlayState(GameStateManager gameStateManager) throws ParserConfigurationException {
        super(gameStateManager);
        map = new Vector2d(); //create the map
        Vector2d.setWorldVar(map.x, map.y); // for camera movement

        tileManager = new TileManager("resources\\map\\map1v2_plains.xml"); // my map
        hero = new Hero(new SpriteSheet("resources\\entity\\hero\\Hero1.png"), new Vector2d(MIDDLE_OF_MAP_X + SPAWN_POSITION_OFFSET_X, MIDDLE_OF_MAP_Y + SPAWN_POSITION_OFFSET_Y), HERO_SIZE); // the hero
        enemy = new Enemy(new SpriteSheet("resources\\entity\\enemy\\littlegirl.png", 48, 48), new Vector2d(ENEMY_POSITION_X, ENEMY_POSITION_Y), HERO_SIZE); // the enemy
        //enemy1 = new Enemy(new Sprite("resources\\entity\\enemy\\littlegirl.png", 48, 48), new Vector2d(MIDDLE_OF_MAP_X + 200, MIDDLE_OF_MAP_Y + 200), HERO_SIZE); // the enemy

    }

    @Override
    public void update() {
        Vector2d.setWorldVar(map.x, map.y); //camera movement
        hero.update(enemy);
        enemy.update(hero);
        //enemy1.update(hero.getBounds());
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
        key.escape.tick();

        hero.input(mouse, key);

        if(key.escape.clicked) {
            System.out.println("PPP");
            if (gameStateManager.isStateActive(PAUSE)) {
                gameStateManager.pop(PAUSE);
            }
            else {
                gameStateManager.add(PAUSE);
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {

        //render the map
        tileManager.render(graphics);

        //the FPS counter
        SpriteSheet.drawArray(graphics, GamePanel.oldFrameCount + " FPS", new Vector2d(GamePanel.width - 100, 20), 12,  12 );

        //render the hero
        hero.render(graphics);

        //render the enemy
        enemy.render(graphics);
        //enemy1.render(graphics);
    }
}
