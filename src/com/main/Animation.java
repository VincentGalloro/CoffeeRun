
package com.main;

import java.awt.image.BufferedImage;

public class Animation {
    
    public BufferedImage[][] imgs;
    public double animSpeed, frame;
    public int track;
    
    public Animation(BufferedImage[][] imgs, double animSpeed){
        this.imgs = imgs;
        this.animSpeed = animSpeed;
    }
    
    public void update(){
        frame = (frame + animSpeed) % imgs[track].length;
    }
    
    public BufferedImage getImg(){
        return imgs[track][(int)frame];
    }
}
