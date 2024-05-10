import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerBullet extends Bullet
{
    Player player;
    
    public PlayerBullet(int rotation, int level, Actor owner) {
        super(rotation, level, owner);
        player = (Player) owner;
    }
    
    @Override
    public void checkHit() {
        Hittable actor = (Hittable) getOneIntersectingObject(Hittable.class);
        
        if (actor != null) {
            Class hitClass = actor.getClass();
            if (hitClass != Bullet.class && hitClass == Enemy.class) {
                addHitEffect();
                getWorld().removeObject(this);
                actor.hit(getDamage());
                player.increaseScore();
            }
        }
    }
}
