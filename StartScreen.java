import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends Screen
{

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {
        super(); 
        createLogo();
        createInstructions();
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Level1());
        }
    }
    
    private void createLogo()
    {
        GreenfootImage background = getBackground();
        background.setColor(Color.WHITE);
        background.setFont(new Font("Arial", true, false, 52));
        background.drawString("DOGFIGHT", 170, 100);
    }
    
    private void createInstructions() {
        getBackground().setFont(new Font("Arial", true, false, 62));
        showText("Instructions \n Use left-rigth arrow to move your plane \n Use Space to shoot \n Kill all the enemy planes before they kill you", getScreenWidth()/2, 250);
        showText("Press Space to start", getScreenWidth()/2, 650);
    }
}
