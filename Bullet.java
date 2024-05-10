import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends SmoothMover
{
    private static final int BASE_SPEED = 3;
    private int level;
    private int damage;
    private int speed;
    private Actor owner;
    
    public Bullet(int rotation, int level, Actor owner) {
        this.level = level;
        this.owner = owner;
        
        damage = calculateBulletDamage();
        speed = BASE_SPEED + level;
        
        setImage("./bullets/bullet2.png");
        GreenfootImage image = getImage();
        //image.scale(6, 6);
        image.rotate(90);
        setRotation(rotation);
    }
    
    public void act()
    {
        // Add your action code here.
        move(speed);
        checkHit();
        removeAtEdge();
    }
    
    public void checkHit() {
        Hittable actor = (Hittable) getOneIntersectingObject(Hittable.class);
        if (actor != null && actor.getClass() != owner.getClass() && actor.getClass() != this.getClass()) 
        {
            Hittable hittableActor = (Hittable) actor;
            addHitEffect();
            getWorld().removeObject(this);
            hittableActor.hit(damage);
        }
    }
    
    public void addHitEffect() {
        HitEffect hitEffect = new HitEffect();
        getWorld().addObject(hitEffect, getX(), getY());
    }
    
    private void removeAtEdge() {
        // If the world is null the object has been removed
        if (getWorld() != null) {
            if ((getImage().getHeight()/2 + getY()) > getWorld().getHeight()) {
                getWorld().removeObject(this);
            }
            else if ((getY() - getImage().getHeight()/2) < 0) {
                getWorld().removeObject(this);
            }
        }
    }
    
    public int calculateBulletDamage() {
        int damage = level / 2;
        
        return (damage == 0) ? 1 : damage;
    }
    
    public Actor getOwner() {
        return owner;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public int getLevel() {
        return level;
    }
}
