import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends World
{
    private static final int SCREEN_HEIGHT = 700;
    private static final int SCREEN_WIDTH = 600;
    
    public Screen()
    {
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1); 
    }
    
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }
    
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }
}
