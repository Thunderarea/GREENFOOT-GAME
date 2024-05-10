import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends Level
{
    private static final int PLAYER_LIFE = 6;
    private static final int ENEMIES_LINES = 3;

    private Player player;
    private int enemiesLevel;
    private int playerLevel;
    private int shootsAtOnce = 1;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    /**
     * Constructor for objects of class Level2.
     * 
     */
    public Level2()
    {
        super(PLAYER_LIFE); 

        enemiesLevel = 1;
        playerLevel = 1;
        
        prepare();
    }
    
    private void prepare()
    {
        player = addPlayer(PLAYER_LIFE, playerLevel);
        addEnemies(enemiesLevel, ENEMIES_LINES);     
    }
    
    @Override
    public void handleZeroEnemies() {
        System.out.println("handle2");
        if (enemiesLevel < 7) {
            nextLevel();
        } else Greenfoot.setWorld(new EndScreen(true, player.getScore()));
    }
    
    private void nextLevel() {
        enemiesLevel++;
        playerLevel = enemiesLevel;
        player.update(playerLevel);
        addEnemies(enemiesLevel, ENEMIES_LINES);
        setShootDelay(getShootDelay() - enemiesLevel * 2);
    }
}
