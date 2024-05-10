import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Aircraft
{
    
    private int score;

    public Enemy(int level) {
        super(level);
    }

    public Enemy(int speed, int level) {
        super(level, speed, level, true);
    }
    
    public void act()
    {
        super.act();
    }

    public void hit(int damage) {
        super.hit(damage);
        if (getLife() <= 0) {
            World world = getWorld();
            if (world instanceof Level) {
                Level myWorld = (Level) world;
                myWorld.handleEnemyDeath(this);
            }
            world.removeObject(this);
        }
    }

    public void fire() {
        EnemyBullet bullet = new EnemyBullet(90, getLevel(), this);
        getWorld().addObject (bullet, getX(), getY() + getImage().getHeight()/2 + bullet.getImage().getHeight()/2);
    }
}
