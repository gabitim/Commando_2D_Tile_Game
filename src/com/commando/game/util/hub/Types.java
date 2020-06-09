package com.commando.game.util.hub;

import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */
public class Types {
    public static String MAP_PLAINS;
    public static String MAP_DESERT;

    public static String CHARACTER_VILLAGER;
    public static String CHARACTER_WIZARD;

    public static String MOB_DWARF;
    public static String MOB_GOBLIN;
    public static String MOB_GIRL;
    public static String MOB_MINOTAUR;
    public static String MOB_ORC;
    public static String MOB_SKELETON;


    public static void loadPaths( ) throws SQLException {

        Database database = new Database();

        String queryMap = "SELECT Path FROM Map WHERE Name= ?";
        String queryHero = "SELECT Path FROM Hero WHERE Name= ?";
        String queryEnemy = "SELECT Path FROM Mob WHERE Name= ?";

        MAP_PLAINS = database.queryPath(queryMap, "MAP_PLAINS");
        MAP_DESERT = database.queryPath(queryMap, "MAP_DESERT");

        CHARACTER_VILLAGER = database.queryPath(queryHero, "CHARACTER_VILLAGER");
        CHARACTER_WIZARD = database.queryPath(queryHero, "CHARACTER_WIZARD");

        MOB_DWARF = database.queryPath(queryEnemy, "MOB_DWARF");
        MOB_GOBLIN = database.queryPath(queryEnemy, "MOB_GOBLIN");
        MOB_GIRL = database.queryPath(queryEnemy, "MOB_GIRL");
        MOB_MINOTAUR = database.queryPath(queryEnemy, "MOB_MINOTAUR");
        MOB_ORC = database.queryPath(queryEnemy, "MOB_ORC");
        MOB_SKELETON = database.queryPath(queryEnemy, "MOB_SKELETON");
    }

}
