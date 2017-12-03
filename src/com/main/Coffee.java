
package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Coffee extends Item {
    
    public double energy;
    
    public Coffee(double xPos, double yPos, int width, int height, double energy){
        super(xPos, yPos, width, height);
        this.energy = energy;
        
        try {
            int num = Level.random.nextInt(7)+1;
            BufferedImage[][] imgs = new BufferedImage[1][2];
            imgs[0][0] = ImageIO.read(new File("res/Coffee Cups/coffee "+num+".1.png"));
            imgs[0][1] = ImageIO.read(new File("res/Coffee Cups/coffee "+num+".2.png"));
            animation = new Animation(imgs, 0.1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onCollision(Player p){
        p.setEnergy(energy);
    }
}
