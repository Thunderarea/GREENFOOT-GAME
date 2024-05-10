import java.util.ArrayList;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Aircraft here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aircraft extends SmoothMover {
    private static final int changeImageDelay = 2;
    private static final int imageNewHeight = 70;
    private static final int changeHitImageDelay = 5;
    private static final int hitTransparency = 120;

    private ArrayList<GreenfootImage> images = new ArrayList<GreenfootImage>();
    private int currentImage;
    private int imagesLength;
    private int actCount;
    private int speed;
    private int life;
    private boolean hit;
    private int countHitImageDelay;
    private int level;

    public Aircraft(int level) {
        initializeImages(level);
        setImage(images.get(0));
    }

    public Aircraft(int life, int speed, int level, boolean rotateImg) {
        this.life = life;
        this.speed = speed;
        this.level = level;

        currentImage = 0;
        actCount = 0;
        hit = false;

        initializeImages(level);
        if (rotateImg) rotateImages();
        imagesLength = images.size();
        setImage(images.get(currentImage));
    }

    
    public void act()
    {
        if (hit) {
            if (countHitImageDelay == changeHitImageDelay) {
                hit = false;
                countHitImageDelay = 0;
                getImage().setTransparency(255);
            } else {
                countHitImageDelay++;
            }
        }
        if (actCount == changeImageDelay) {
            actCount = 0;
            setImage(images.get(currentImage));
            if (hit) getImage().setTransparency(hitTransparency);
            else getImage().setTransparency(255);
            currentImage = (currentImage + 1) % imagesLength;
        }

        actCount++;
    }
    
    public void updateImages(int level) {
        images = new ArrayList<GreenfootImage>();
        initializeImages(level);
        setImage(images.get(0));
    }

    public void initializeImages(int level) {
        images.add(new GreenfootImage("./airplanes/level" + level + "/1.png"));
        images.add(new GreenfootImage("./airplanes/level" + level + "/2.png"));
        images.add(new GreenfootImage("./airplanes/level" + level + "/3.png"));

        for (GreenfootImage image : images) {
            image.scale((image.getWidth() * imageNewHeight) / image.getHeight(), imageNewHeight);
        }
    }

    public void rotateImages() {
        for (GreenfootImage image : images) {
            image.rotate(180);
        }
    }

    public void hit(int damage) {
        life -= damage;
        if (life > 0) {
            hit = true;
            getImage().setTransparency(hitTransparency);
        }
    }

    public int getLife() {
        return life;
    }
    
    public void increaseLife() {
        life++;
    }

    public int getSpeed() {
        return speed;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
}
