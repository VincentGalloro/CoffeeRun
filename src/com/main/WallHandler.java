
package com.main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class WallHandler {
    
    public static final Random random = new Random();
    
    public ArrayList<Wall> walls;
    
    public WallHandler(){
        walls = new ArrayList<>();
    }
    
    public boolean isColliding(Player p){
        for(Wall w : walls){
            if(w.isColliding(p)){ return true; }
        }
        return false;
    }
    
    public void render(Graphics2D g, Camera cam){
        for(Wall w : walls){
            w.render(g, cam);
        }
    }
}
