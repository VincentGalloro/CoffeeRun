package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Item {
    
    public double xPos, yPos;
    public int width, height;
    public boolean dead;
    public Animation animation;
    
    public Item(double xPos, double yPos, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    public void update(){
        animation.update();
    }
    
    public boolean collision(Player p){
        boolean colliding = Math.abs(xPos-p.x)<=(width+p.width)/2 && Math.abs(yPos-p.y)<=(height+p.height)/2;
        
        if (colliding){
            onCollision(p);
            dead = true;
        }
        
        return colliding;
    }
    
    public void onCollision(Player p){
    }
    
    public void render(Graphics2D g, Camera cam){
        cam.render(g, animation.getImg(), xPos, yPos, width, height);
    }
}
