package com.commando.game.util.hub;

/**
 * @author Timofti Gabriel
 */
public class Types {
    public static String MAP_PLAINS;
    public static String MAP_DESERT;

    public static String CHARACTER_VILLAGER;
    public static String CHARACTER_WIZARD;


    public static void loadPaths( ) {

        String queryMap = "SELECT Path FROM Map WHERE Name= ?";
        String queryHero = "SELECT Path FROM Hero WHERE Name= ?";

        MAP_PLAINS = Database.query(queryMap, "MAP_PLAINS");
        MAP_DESERT = Database.query(queryMap, "MAP_DESERT");

        CHARACTER_VILLAGER = Database.query(queryHero, "CHARACTER_VILLAGER");
        CHARACTER_WIZARD = Database.query(queryHero, "CHARACTER_WIZARD");
    }

}
