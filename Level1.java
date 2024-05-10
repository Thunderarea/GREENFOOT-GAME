import greenfoot.*;
import java.util.List;

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends Level
{
    private static final int PLAYER_LIFE = 2;
    private static final int ENEMIES_LEVEL = 1;
    private static final int PLAYER_LEVEL = 1;
    private static final int ENEMIES_LINES = 5;
    private static final int MAX_LEVEL = 3;

    private int levelCounter;
    
    public Level1()
    {
        super(PLAYER_LIFE); 
        prepareBackground();
        prepare();
    }
    
    private void prepare()
    {
        addPlayer(PLAYER_LIFE, PLAYER_LEVEL);
        addEnemies(ENEMIES_LEVEL, ENEMIES_LINES);    
        levelCounter = 1;
    }
    
    private void prepareBackground() {
        GreenfootImage background = getBackground();
        background.setColor(Color.WHITE);
        background.fill();
    }
    
    private void nextLevel() {
        List<Bullet> bullets = getObjects(Bullet.class);
        removeObjects(bullets);
        addEnemies(ENEMIES_LEVEL, ENEMIES_LINES);
        levelCounter++;
    }
    
    @Override
    public void handleZeroEnemies() {
        if (levelCounter < MAX_LEVEL) {
            nextLevel();
        } else {
            Greenfoot.setWorld(new Level2());
        }
    }
}
