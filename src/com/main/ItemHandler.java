
package com.main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class ItemHandler {
    
    public static final Random random = new Random();
    
    public ArrayList<Item> items;
    
    public ItemHandler(){
        items = new ArrayList<>();
        
        for(int i = 0; i < 20*9*4; i++){
            items.add(new Coffee(random.nextInt(1500*9), random.nextInt(832*4), 50, 50, 30));
        }
        for(int i = 0; i < 30; i++){
            items.add(new Note(random.nextInt(1500*9), random.nextInt(832*4), 50, 50));
        }
    }
    
    public void update(Keyboard keyboard){
        for(Item i : items){
            i.update();
        }
        for(int i = items.size()-1; i >= 0; i--){
            if(items.get(i).dead){ items.remove(i); }
        }
    }
    
    public void checkCollision(Player p){
        for(Item i : items){
            i.collision(p);
        }
    }
    
    public void render(Graphics2D g, Camera cam){
        for(Item i : items){
            i.render(g, cam);
        }
    }
}
