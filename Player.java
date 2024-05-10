import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Aircraft
{
    private int totalScore;
    private int scoreCounter;
    private int initialLife;
    private boolean canShoot;
    
    public Player(int life, int gunReloadTime, int speed, int level) {
        super(life, speed, level, false);
        initialLife = life;
        totalScore = 0;
        scoreCounter = 0;
        
        canShoot = true;
    }
    
    public void update(int level) {
        updateImages(level);
        setLevel(level);
    }
    
    public void act()
    {
        super.act();
        checkKeys();
        keepInside();
    }

    public void hit(int damage) {
        super.hit(damage);
        decreaseScore();
        Level world = (Level) getWorld();
        world.updateLives(getLife());
        if (getLife() <= 0) {
            world.removeObject(this);
            world.stopped();
            Greenfoot.setWorld(new EndScreen(false, totalScore));
        }
    }
    
    private void keepInside() {
        if ((getImage().getWidth()/2 + getX()) > getWorld().getWidth()) {
            setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
        }
        else if ((getX() - getImage().getWidth()/2) < 0) {
            setLocation(getImage().getWidth()/2, getY());
        }
    }
    
    private void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            move(-getSpeed());
        }
        else if (Greenfoot.isKeyDown("right")) {
            move(getSpeed());
        }
        
        if (Greenfoot.isKeyDown("space")) fire();
        else canShoot = true;
    }
    
    private void fire() {
        if (canShoot) {
            PlayerBullet bullet = new PlayerBullet(270, getLevel(), this);
            getWorld().addObject (bullet, getX(), getY() - getImage().getHeight()/2 - bullet.getImage().getHeight()/2);
            Greenfoot.playSound("blast.mp3");
            canShoot = false;
        }
    }
    
    public void increaseScore() {
        totalScore++;
        scoreCounter++;
        Level world = (Level) getWorld();
        world.updateScore(totalScore);
        if (scoreCounter == 10) {
            scoreCounter = 0;
            if (getLife() < initialLife) {
                increaseLife();
                world.updateLives(getLife());
            }
        }
    }
    
    public void decreaseScore() {
        totalScore--;
        ((Level) getWorld()).updateScore(totalScore);
    }
    
    public int getScore() {
        return totalScore;
    }
}
