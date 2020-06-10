package com.commando.game.states.levels;

import com.commando.game.entity.caracters.Enemy;
import com.commando.game.entity.caracters.Hero;
import com.commando.game.entity.caracters.enemyTypes.*;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.states.GameStateManager;
import com.commando.game.states.PlayState;
import com.commando.game.states.menuOptions.Settings;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;
import com.commando.game.util.collision.TileCollision;
import com.commando.game.util.hub.DataForLoad;
import com.commando.game.util.hub.Types;


import java.awt.*;
import java.util.ArrayList;
import static com.commando.game.states.PlayState.*;
import static com.commando.game.states.levels.LevelManager.*;
import static com.commando.game.util.hub.Define.*;


/**
 * @author Timofti Gabriel
 */
public class CustomLevel extends Level {

    private DataForLoad dataForLoad;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public static int levelFromLoad;

    public CustomLevel(LevelManager levelManager, GameStateManager gameStateManager, DataForLoad dataForLoad) throws Exception {
        super(levelManager);

        this.dataForLoad = dataForLoad;

        CURRENT_HERO = dataForLoad.getHeroType();
        CURRENT_HERO_SIZE = dataForLoad.getHeroSize();
        CURRENT_MAP = dataForLoad.getMapType();
        levelFromLoad = dataForLoad.getLevel();

        init();
        playState = new PlayState(gameStateManager, dataForLoad.getMapType(), dataForLoad.getHeroLife(), dataForLoad.getX_Pos_Hero(), dataForLoad.getY_Pos_Hero(),
                dataForLoad.getHeroType(), dataForLoad.getHeroSize(), dataForLoad.getMapX(), dataForLoad.getMapY());
        playState.init(enemies);
    }

    @Override
    public void init() throws Exception {
        String[] enemiesData = dataForLoad.getEnemyInfo().split("\n");

        for (int i = 0; i < enemiesData.length; i++) {
            String[] info = enemiesData[i].split(";");

            Enemy enemy = getMobByNameAndInfo(info[1], Integer.parseInt(info[3]), Integer.parseInt(info[4]));

            enemy.setHealth(Integer.parseInt(info[2]), 0);
            this.enemies.add(enemy);
        }
    }

    private Enemy getMobByNameAndInfo(String dataFromDB, int xPos, int yPos) throws Exception {
        System.out.println(dataFromDB);

        switch (dataFromDB) {
            case "MobGirl" : { return new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(xPos, yPos), ENTITY_SIZE); }
            case "MobDwarf" : { return new MobDwarf(new SpriteSheet(Types.MOB_DWARF, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(xPos, yPos), ENTITY_SIZE); }
            case "MobGoblin" : { return new MobGoblin(new SpriteSheet(Types.MOB_GOBLIN, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(xPos, yPos), ENTITY_SIZE); }
            case "MobOrc" : { return new MobOrc(new SpriteSheet(Types.MOB_ORC, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(xPos, yPos), ENTITY_SIZE); }
            case "MobSkeleton" : { return new MobSkeleton(new SpriteSheet(Types.MOB_SKELETON, MOB_SPRITE_SIZE, MOB_SPRITE_SIZE), new Vector2d(xPos, yPos), ENTITY_SIZE); }
            default: throw new Exception("ERROR LOADING MOBS");
        }

    }

    @Override
    public Enemy initOneEnemy() {
        System.out.println("initOne");
        return new MobGirl(new SpriteSheet(Types.MOB_GIRL, MOB_GIRL_SPRITE_SIZE, MOB_GIRL_SPRITE_SIZE), new Vector2d(ENEMY_POSITION_X + 1000, ENEMY_POSITION_Y + 950), ENTITY_SIZE);
    }

    @Override
    public void update(boolean canPassToNext) throws Exception {
        canPassPlayState = canPassToNext;

        //System.out.println("level"+ (CURRENT_LEVEL+1) + (5 - TileCollision.timePassed / 1000));
        if ((5 - TileCollision.timePassed / 1000) == 0) {
            noOfLives = Hero.noOfLifes;
            totalDamage = Hero.totalDamage;
            LevelManager.CURRENT_LEVEL = dataForLoad.getLevel() + 1;
            PlayState.canPassPlayState = false;
            LevelManager.canPassLevel = false;
            TileCollision.timePassed = 0;
            levelManager.pop(CUSTOM_LEVEL);
            levelManager.add(CURRENT_LEVEL);
        }

        playState.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) throws Exception {
        playState.input(mouse, key);
    }

    @Override
    public void render(Graphics2D graphics) {
        playState.render(graphics);
    }
}
