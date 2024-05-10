import greenfoot.*;
import java.util.ArrayList;

/**
 * Write a description of class HitEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HitEffect extends Actor  
{
    private static final int EFFECT_HEIGHT = 30;
    private static final int changeImageDelay = 1;
    
    private ArrayList<GreenfootImage> images = new ArrayList<GreenfootImage>();
    private int actCount;
    private int currentImage;
    private int imagesLength;
    
    public HitEffect()
    {
        initializeImages();
        setImage(images.get(0));
        actCount = 0;
        currentImage = 0;
        imagesLength = images.size();
    }
    
    public void act()
    {
        if (actCount == changeImageDelay) {
            if (currentImage >= imagesLength) getWorld().removeObject(this);
            else {
                actCount = 0;
                setImage(images.get(currentImage));
                currentImage++;
            }
        }

        actCount++;
    }
    
    private void initializeImages() {
        for (int i = 0; i < 9; i++) {
            images.add(new GreenfootImage("./hit/hit_000" + i + ".png"));
        }
        
        for (GreenfootImage image : images) {
            image.scale((image.getWidth() * EFFECT_HEIGHT) / image.getHeight(), EFFECT_HEIGHT);
        }
    }

    
}
