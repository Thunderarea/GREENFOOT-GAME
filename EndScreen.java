import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends Screen
{
    public EndScreen(boolean win, int score) {
        super();
        GreenfootImage background = getBackground();
        background.setFont(new Font("Arial", true, false, 36));
        if (!win) {
            background.setColor(Color.RED); 
            background.drawString("YOU LOST", 210, 100);
        } else {
            background.setColor(Color.GREEN); 
            background.drawString("GAME OVER", 210, 100);
        }
        
        
        background.setColor(Color.BLUE); 
        background.drawString("SCORE", 250, 300);
        background.drawString(String.valueOf(score), 300, 340);
        
        background.setColor(Color.WHITE);
        background.setFont(new Font("Arial", true, false, 14));  
        background.drawString("Press ENTER to return to the home page", 160, 550);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
