package com.commando.game.util.hub;

/**
 * @author Timofti Gabriel
 */
public class DataForLoad {
    private String name;
    private int level;
    private int heroLife;
    private int noOfLifes;
    private int totalDamage;
    private int timePassed;
    private int x_Pos_Hero;
    private int y_Pos_Hero;
    private String enemyInfo;
    private String mapType;
    private String heroType;
    private int heroSize;
    private int mapX;
    private int mapY;

    public DataForLoad(String name, int level, int heroLife, int x_Pos_Hero, int y_Pos_Hero, int noOfLifes, String enemyInfo, int totalDamage, int timePassed,
                       String mapType, String heroType, int heroSize, int mapX, int mapY ) {
        this.name = name;
        this.level = level;
        this.heroLife = heroLife;
        this.noOfLifes = noOfLifes;
        this.totalDamage = totalDamage;
        this.timePassed = timePassed;
        this.x_Pos_Hero =  x_Pos_Hero;
        this.y_Pos_Hero = y_Pos_Hero;
        this.enemyInfo = enemyInfo;
        this.mapType = mapType;
        this.heroType = heroType;
        this.heroSize = heroSize;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHeroLife() { return heroLife; }
    public int getNoOfLifes() { return noOfLifes; }
    public int getTotalDamage() { return totalDamage; }
    public int getTimePassed() { return timePassed; }
    public int getX_Pos_Hero() { return x_Pos_Hero; }
    public int getY_Pos_Hero() { return y_Pos_Hero; }
    public String getEnemyInfo() { return enemyInfo; }
    public String getMapType() { return mapType; }
    public String getHeroType() { return heroType; }
    public int getHeroSize() { return heroSize; }
    public int getMapX() { return mapX; }
    public int getMapY() { return mapY; }

}
