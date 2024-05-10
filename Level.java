import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends Screen
{
    private GreenfootSound airplaneSound;
    
    private GreenfootImage lives;
    
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private int shootDelayCount;
    private int shootsAtOnce = 1;
    private int shootDelay = 30;
    private int initialShootDelay;
    
    public Level(int playerLife)
    {
        super(); 
        
        initialShootDelay = shootDelay;
        createSound();
        updateLives(playerLife);
        updateScore(0);
    }
    
    public void act() {
        int enemiesNumber = enemies.size();
        if (enemiesNumber > 0 && shootDelayCount >= shootDelay) {
            for (int i = 0; i < shootsAtOnce; i++) {
                try {
                    enemies.get(Greenfoot.getRandomNumber(enemiesNumber)).fire();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            shootDelayCount = 0;
        }
        shootDelayCount++;
    }
    
    private void createSound() {
        airplaneSound = new GreenfootSound("airplane.mp3");
        airplaneSound.setVolume(50);
        airplaneSound.playLoop();
    }
    
    public Player addPlayer(int playerLife, int playerLevel) {
        Player player = new Player(playerLife, 25, 5, playerLevel);
        addObject(player, getWidth()/2, getHeight() - 60);
        return player;
    }
    
    public void addEnemies(int enemiesLevel, int numberOfLines) {
        shootDelay = initialShootDelay;
        int worldWidth = getWidth(); 
        Enemy enemy = new Enemy(enemiesLevel);
        int enemyWidth = enemy.getImage().getWidth();
        int gap = 15;
        int numberOfEnemies = (worldWidth - gap) / (enemyWidth + gap);
        
        int yPosition = enemy.getImage().getHeight()/2 + 5;
        for (int i = 0; i < numberOfLines; i++) {
            addEnemiesLine(enemiesLevel, numberOfEnemies - i, enemyWidth, worldWidth, yPosition);
            yPosition += enemy.getImage().getHeight() + 5;
        }
    }
    
    private void addEnemiesLine(int enemiesLevel, int numberOfEnemies, int enemyWidth, int worldWidth, int yPosition) {
        int totalWidthOfObjects = numberOfEnemies * enemyWidth;
        int spacing = (worldWidth - totalWidthOfObjects) / (numberOfEnemies + 1);
           
        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy(1, enemiesLevel);
            enemies.add(enemy);
            addObject(enemy, spacing + (i * (enemyWidth + spacing)) + (enemyWidth / 2), yPosition);
        }
    }
    
    public void handleEnemyDeath(Enemy enemy) {
        enemies.remove(enemy);
        if (enemies.size() == 1) shootDelay = 55; // remember to update the value when new enemies are added
        else if (enemies.size() == 0) {
            handleZeroEnemies();
        }
    }
    
    public void endScreen() {
        
    }
    
    
    public void handleZeroEnemies() {
        // show win screen
    }
    
    public void updateLives(int life) {
        showText("HEALTH: " + life, 50, getScreenHeight() - 10);
    }
    
    public void updateScore(int score) {
        showText("SCORE: " + score, getWidth()/2, getScreenHeight() - 10);
    }
    
    public void started() {
        airplaneSound.playLoop();
    }
    
    public void stopped() {
        airplaneSound.stop();
    }
    
    public int getShootDelay() {
        return shootDelay;
    }
    
    public void setShootDelay(int newShootDelay) {
        if (newShootDelay < 10) shootDelay = 10;
        else shootDelay = newShootDelay;
    }
}
