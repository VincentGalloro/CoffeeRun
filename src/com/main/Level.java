
package com.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Level {
    
    public static final Random random = new Random();
    
    public Player player;
    public WallHandler wh;
    public ItemHandler ih;
    public Camera cam;
    public BufferedImage gameOver;
    public BufferedImage victory;
    
    public Level(){
        player = new Player(400, 200);
        wh = new WallHandler();
        ih = new ItemHandler();
        cam = new Camera();
        
        try {
            gameOver = ImageIO.read(new File("res/GameOver.png"));
            victory = ImageIO.read(new File("res/Victory.jpg"));
        } catch (IOException ex) {}
    }
    
    public void init(){
        try {
            BufferedImage data = ImageIO.read(new File("res/map final wooooo.png"));
            BufferedImage img = new BufferedImage(data.getWidth(), data.getHeight(), BufferedImage.TYPE_INT_ARGB);
            img.createGraphics().drawImage(data, 0, 0, null);
            MazeLoader.loadMaze(img, this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(Keyboard keyboard){
        
        player.update(this, keyboard);
        ih.update(keyboard);
        cam.track(player);
        cam.update();

    }
    
    public void render(Graphics2D g){
        if(!player.isAlive || player.notes >= 10){
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        }
        else if(player.speedMulti > 0){
            float f = System.currentTimeMillis()%10000;
            g.setColor(new Color(Color.HSBtoRGB(f*0.0015f, 0.2f, 1)));
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        }
        ih.render(g, cam);
        if(player.isAlive){
            wh.render(g, cam);
        }
        player.render(g, cam);
        if(!player.isAlive){
            g.drawImage(gameOver, 200, 100, 900, 500, null);
        }
        if (player.notes >= 10){
            g.drawImage(victory, 200, 100, 900, 500, null);
        }
    }
}
