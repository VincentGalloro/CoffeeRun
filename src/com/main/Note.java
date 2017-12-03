package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Note extends Item {
    
    public Note(double xPos, double yPos, int width, int height){
        super(xPos, yPos, width, height);
        
        try {
            BufferedImage[][] imgs = new BufferedImage[1][1];
            imgs[0][0] = ImageIO.read(new File("res/note.png"));
            animation = new Animation(imgs, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onCollision(Player p){
        p.notes += 1;
    }
}
