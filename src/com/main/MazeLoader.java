
package com.main;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class MazeLoader {
    
    public static void loadMaze(BufferedImage img, Level l){
        double scaleX = 9*(Main.WIDTH-Camera.BORDER)/(double)img.getWidth();
        double scaleY = 4*(Main.HEIGHT-Camera.BORDER)/(double)img.getHeight();
        int[] pix = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
        boolean[][] wall = new boolean[img.getWidth()][img.getHeight()];
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                int col = 0;
                for(int i = 0; i < 3; i++){
                    col += (pix[x+y*img.getWidth()]>>(i*16))&0xFF;
                }
                if(col/3 <= 128){
                    wall[x][y] = true;
                }
            }
        }
        double thicc = 1;
        for(int y = 0; y < img.getHeight(); y++){
            int x = 0;
            while(x < img.getWidth()){
                if(wall[x][y]){
                    int s = 1;
                    while(x+s < img.getWidth() && wall[x+s][y]){ s++; }
                    if(s > 1){
                        l.wh.walls.add(new Wall(x*scaleX + (s*scaleX)/2, (y+thicc/2)*scaleY, (int)(s*scaleX), (int)(scaleY*thicc)));
                    }
                    x += s;
                }
                x++;
            }
        }
        for(int x = 0; x < img.getWidth(); x++){
            int y = 0;
            while(y < img.getHeight()){
                if(wall[x][y]){
                    int s = 1;
                    while(y+s < img.getHeight() && wall[x][y+s]){ s++; }
                    if(s > 1){
                        l.wh.walls.add(new Wall((x+thicc/2)*scaleX, y*scaleY + (s*scaleY)/2, (int)(scaleX*thicc), (int)(s*scaleY)));
                    }
                    y += s;
                }
                y++;
            }
        }
    }
}
