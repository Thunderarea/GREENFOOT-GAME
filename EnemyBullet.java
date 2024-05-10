import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBullet extends Bullet
{
    
    public EnemyBullet(int rotation, int level, Actor owner) {
        super(rotation, level, owner);
    }
    
    @Override
    public void checkHit() {
        Hittable actor = (Hittable) getOneIntersectingObject(Hittable.class);
        
        if (actor != null) {
            Class hitClass = actor.getClass();
            if (hitClass != Bullet.class && hitClass != getOwner().getClass()) {
                addHitEffect();
                getWorld().removeObject(this);
                actor.hit(getDamage());
            }
        }
    }
    
    @Override
    public int calculateBulletDamage() {
        int damage = (getLevel() / 2) - 1;
        
        return (damage < 1) ? 1 : damage;
    }
}
