package com.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Camera {
    public static final int ROOM_SIZE_X = 1300;
    public static final int ROOM_SIZE_Y = 700;
    public static final int BORDER = 8;
    
    public double xPos, yPos, targetPosX, targetPosY;
    
    public void update(){
        xPos = xPos*0.9 + targetPosX*0.1;
        yPos = yPos*0.9 + targetPosY*0.1;
    }
    
    public void track(Player p){
        targetPosX = (Math.floor(p.x / ROOM_SIZE_X)) * (ROOM_SIZE_X-BORDER*1.4);
        targetPosY = (Math.floor(p.y / ROOM_SIZE_Y)) * (ROOM_SIZE_Y-BORDER*1.4);
    }
    
    public void render(Graphics2D g, BufferedImage img, double x, double y, int width, int height){
        g.drawImage(img, (int)(x-xPos)-width/2, (int)(y-yPos)-height/2, width, height, null);
    }
}
