package com.commando.game.util.hub;

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


    public static void loadPaths( ) {

        String queryMap = "SELECT Path FROM Map WHERE Name= ?";
        String queryHero = "SELECT Path FROM Hero WHERE Name= ?";
        String queryEnemy = "SELECT Path FROM Mob WHERE Name= ?";

        MAP_PLAINS = Database.query(queryMap, "MAP_PLAINS");
        MAP_DESERT = Database.query(queryMap, "MAP_DESERT");

        CHARACTER_VILLAGER = Database.query(queryHero, "CHARACTER_VILLAGER");
        CHARACTER_WIZARD = Database.query(queryHero, "CHARACTER_WIZARD");

        MOB_DWARF = Database.query(queryEnemy, "MOB_DWARF");
        MOB_GOBLIN = Database.query(queryEnemy, "MOB_GOBLIN");
        MOB_GIRL = Database.query(queryEnemy, "MOB_GIRL");
        MOB_MINOTAUR = Database.query(queryEnemy, "MOB_MINOTAUR");
        MOB_ORC = Database.query(queryEnemy, "MOB_ORC");
        MOB_SKELETON = Database.query(queryEnemy, "MOB_SKELETON");
    }

}
