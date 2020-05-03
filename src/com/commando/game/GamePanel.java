package com.commando.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if(thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D)img.getGraphics();
    }

    private int x = 0;

    public void input() {

    }

    public void update() {
        x ++;
        System.out.println(x);
    }

    public void render() {
        if( g!= null) {
            g.setColor(new Color(107, 213, 244));
            g.fillRect(0,0, width, height);
        }
    }

    public void draw() {
        Graphics g2 = (Graphics)this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }


    @Override
    public void run() {
        init();

        final double GAME_FPS = 60.0 ;
        final double TIME_BEFORE_UPDATE = 1_000_000_000 / GAME_FPS;
        final int UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TOTAL_TIME_BEFORE_RENDER = 1_000_000_000 / TARGET_FPS;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while (( (now - lastUpdateTime) > TIME_BEFORE_UPDATE) && (updateCount < UPDATES_BEFORE_RENDER) ){
                update();
                input();
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }

            if(now - lastUpdateTime > TIME_BEFORE_UPDATE) {
                lastUpdateTime = now - TIME_BEFORE_UPDATE;
            }

            input();
            render();
            draw();

            lastRenderTime = now;

            while (now - lastRenderTime < TOTAL_TIME_BEFORE_RENDER && now - lastUpdateTime < TIME_BEFORE_UPDATE) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }

        }
    }
}
