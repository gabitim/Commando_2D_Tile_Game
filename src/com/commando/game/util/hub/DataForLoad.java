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

    public DataForLoad(String name, int level, int heroLife, int x_Pos_Hero, int y_Pos_Hero, int noOfLifes, String enemyInfo, int totalDamage, int timePassed) {
        this.name = name;
        this.level = level;
        this.heroLife = heroLife;
        this.noOfLifes = noOfLifes;
        this.totalDamage = totalDamage;
        this.timePassed = timePassed;
        this.x_Pos_Hero =  x_Pos_Hero;
        this.y_Pos_Hero = y_Pos_Hero;
        this.enemyInfo = enemyInfo;
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

}
