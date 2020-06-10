package com.commando.game.states;

import com.commando.game.GamePanel;
import com.commando.game.entity.caracters.Enemy;
import com.commando.game.entity.caracters.Hero;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.graphics.playerUI.PlayerUI;
import com.commando.game.states.levels.LevelManager;
import com.commando.game.tiles.TileManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.TileCollision;


import static com.commando.game.util.hub.Define.*;
import static com.commando.game.states.GameStateManager.*;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * @author Timofti Gabriel
 */
public class PlayState extends GameState {

    private static int currentLevel;

    public static String CURRENT_MAP;
    public static String CURRENT_HERO;
    public static int CURRENT_HERO_SIZE;

    public static final int SPAWN_POSITION_OFFSET_X = 350;
    public static final int SPAWN_POSITION_OFFSET_Y = 550;

    public static final int MIDDLE_OF_MAP_X = (GamePanel.width / 2) - 32;
    public static final int MIDDLE_OF_MAP_Y = (GamePanel.height / 2) - 32;

    public static final int HERO_SPAWN_POSITION_X = MIDDLE_OF_MAP_X + SPAWN_POSITION_OFFSET_X;
    public static final int HERO_SPAWN_POSITION_Y = MIDDLE_OF_MAP_Y + SPAWN_POSITION_OFFSET_Y;

    public static final int ENEMY_POSITION_X = MIDDLE_OF_MAP_X + SPAWN_POSITION_OFFSET_X +  250;
    public static final int ENEMY_POSITION_Y = MIDDLE_OF_MAP_Y + SPAWN_POSITION_OFFSET_Y +  250;

    public Hero hero;
    public ArrayList<Enemy> enemies;
    private TileManager tileManager;
    private PlayerUI heroUI;

    public static Vector2d map; // for camera
    public static boolean pause = false;

    public static boolean canPassPlayState = false;


    public PlayState(GameStateManager gameStateManager) throws ParserConfigurationException {
        super(gameStateManager);

        map = new Vector2d(); //create the map
        Vector2d.setWorldVar(map.x, map.y); // for camera movement

        tileManager = new TileManager(CURRENT_MAP); // my map
        hero = new Hero(new SpriteSheet(CURRENT_HERO, CURRENT_HERO_SIZE, CURRENT_HERO_SIZE), new Vector2d(HERO_SPAWN_POSITION_X, HERO_SPAWN_POSITION_Y), ENTITY_SIZE); // the hero
        heroUI = new PlayerUI(hero);

    }

    public void init(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }


    @Override
    public void update() {

        Vector2d.setWorldVar(map.x, map.y); //camera movement

        hero.update(enemies, pause);

        try {
            for (int i = 0; i < enemies.size(); i++) {

                if (!enemies.get(i).getDeath()) {
                    enemies.get(i).update(hero, pause);
                } else {
                    enemies.remove(enemies.get(i));
                    currentLevel = LevelManager.CURRENT_LEVEL;
                    System.out.println(currentLevel);
                    enemies.add(LevelManager.getEnemyByLevel(currentLevel));
                }
            }
        }
        catch (Exception e) {
            System.out.println("ERROR IN PLAYSTATE UPDATE ENEMIES");
        }
        heroUI.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException, SQLException {
        //System.out.println( mouse.getX() + ", " + mouse.getY());

        key.escape.tick();

        hero.input(mouse, key);
        heroUI.input(mouse, key);
        if(key.escape.clicked) {
            System.out.println("ESC Pressed");
            if (gameStateManager.isStateActive(PAUSE)) {
                gameStateManager.pop(PAUSE);
                pause = false;
            }
            else {
                gameStateManager.add(PAUSE);
                pause = true;
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        if(!pause) {

            tileManager.render(graphics);

            //the FPS counter
            SpriteSheet.drawArray(graphics, "DAMAGE: " + Hero.totalDamage, new Vector2d(GamePanel.width - 200, 660), 24, 15);
            SpriteSheet.drawArray(graphics, "LEVEL: " + (LevelManager.CURRENT_LEVEL + 1), new Vector2d(GamePanel.width - 200, 690), 24, 15);
            SpriteSheet.drawArray(graphics, GamePanel.oldFrameCount + " FPS", new Vector2d(GamePanel.width - 100, 20), 12, 12);
            if(canPassPlayState) {
                SpriteSheet.drawArray(graphics, "NEXT LEVEL IN  " + (5 - TileCollision.timePassed / 1000) , new Vector2d(80, 560), 40, 28);
            }


            hero.render(graphics);
            for(Enemy enemy : enemies) {
                if (!enemy.getDeath())
                    enemy.render(graphics);
                }
            heroUI.render(graphics);
        }
    }
}
