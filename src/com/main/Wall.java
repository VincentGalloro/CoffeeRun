
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall {
        
    public double x, y;
    public int width, height;
    
    public Wall(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean isColliding(Player p){
        return Math.abs(x-p.x)<=(width+p.width)/2 && Math.abs(y-p.y)<=(height+p.height)/2;
    }
    
    public void render(Graphics2D g, Camera cam){
        g.setColor(new Color(240, 240, 240));
        g.fillRect((int)(x-cam.xPos)-width/2, (int)(y-cam.yPos)-height/2, width, height);
        g.setColor(Color.BLACK);
        g.drawRect((int)(x-cam.xPos)-width/2, (int)(y-cam.yPos)-height/2, width, height);
    }
}
