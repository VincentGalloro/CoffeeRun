
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Player {
    
    public static final double SPEED = 2.4, FRICTION = 0.8;
    
    //public static final Font FONT = new Font("")
    
    public double x, y, vx, vy;
    public int width, height, notes, textPosX=65, textPosY=20, energyBarX = 10, textOffset = 10;
    public Animation animation;
    public double energy = 100, targetEnergy = 100, speedMulti;
    public boolean moving, facingRight, isAlive = true;
    
    public Player(double x, double y){
        this.x = x;
        this.y = y;
        width = 48;
        height = 54;
        
        try {
            BufferedImage ss = ImageIO.read(new File("res/player character.png"));
            BufferedImage[][] imgs = new BufferedImage[2][3];
            for(int i = 0; i < 3; i++){
                imgs[0][i] = ss.getSubimage(i*134, 0, 117, 143);
            }
            for(int i = 0; i < 3; i++){
                imgs[1][i] = ss.getSubimage(i*134, 146, 117, 143);
            }
            animation= new Animation(imgs, 0.2);
            
        } catch (IOException ex) {}
    }
    
    public void update(Level l, Keyboard keyboard){
        if (isAlive)
            move(l, keyboard);
        animation.track = facingRight ? 1 : 0;
        if(moving){ animation.update(); }
        else{ animation.frame = 1; }
        
        // Energy bar
        setEnergy((-(100d/8)/60d)*(1+speedMulti));
        if (targetEnergy <= 0)
             isAlive = false;
    }

    public void setEnergy(double energy){
//        this.energy += energy;
//        
//        // Cap energy
//        if(this.energy > 100){
//            this.energy = 100;
//        }
//        if(this.energy < 0){
//            this.energy = 0;
//        }

        targetEnergy += energy;
        
        // Cap energy
        if(targetEnergy > 100){
            speedMulti = Math.log(targetEnergy-99)/Math.log(10);
        }
        else if(targetEnergy < 0){
            targetEnergy = 0;
        }
        else{
            speedMulti = 0;
        }
    }
    
    public void move(Level l, Keyboard keyboard){
        moving = false;
        double s = SPEED * (1+speedMulti);
        if(keyboard.isKeyDown(Key.UP)){ vy -= s; moving = true; facingRight = false; }
        if(keyboard.isKeyDown(Key.DOWN)){ vy += s; moving = true; facingRight = true; }
        if(keyboard.isKeyDown(Key.RIGHT)){ vx += s; moving = true; facingRight = true; }
        if(keyboard.isKeyDown(Key.LEFT)){ vx -= s; moving = true; facingRight = false; }
    
        double oldX = x;
        x += vx;
        if(l.wh.isColliding(this)){ x = oldX; vx = 0; }
        double oldY = y;
        y += vy;
        if(l.wh.isColliding(this)){ y = oldY; vy = 0; }
        
        vx *= FRICTION;
        vy *= FRICTION;
        
        l.ih.checkCollision(this);
    }
    
    public void render(Graphics2D g, Camera cam){
        energy = energy*0.98 + targetEnergy*0.02;
        if (isAlive)
            cam.render(g, animation.getImg(), x, y, width, height);
        else
            cam.render(g, animation.getImg(), x, y, width, height); // CHANGE LATER!!!
        g.setColor(Color.MAGENTA);
        g.drawString("Energy", textPosX, textPosY);
        g.setColor(Color.BLACK);
        g.drawRect(energyBarX, textPosY+textOffset, 150, 20);
        
        if(speedMulti == 0){
            g.setColor(Color.RED);
        }
        else{
            float f = System.currentTimeMillis()%10000;
            g.setColor(new Color(Color.HSBtoRGB(f*0.0015f, 1, 1)));
        }
        g.fillRect(energyBarX, textPosY+textOffset, (int) (150 * (energy/100)), 20);
        
        g.setColor(Color.GREEN);
        g.drawString("Notes: " + notes, energyBarX, textPosY+textOffset+40);
    }
}
