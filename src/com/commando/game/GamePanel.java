package com.commando.game;

import com.commando.game.states.GameStateManager;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class GamePanel extends JPanel implements Runnable{


    public static int width;
    public static int height;
    public static int oldFrameCount;

    private Thread thread;
    public static boolean running = false;

    private BufferedImage image;
    private Graphics2D graphics;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gameStateManager;

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

    public void initGraphics() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D)image.getGraphics();
    }

    public void init() throws ParserConfigurationException {
        running = true;

        initGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gameStateManager = new GameStateManager(graphics);
    }

    public void update() throws ParserConfigurationException {
         gameStateManager.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) throws ParserConfigurationException {
        gameStateManager.input(mouse, key);
    }

    public void render() {
        if( graphics!= null) {
            gameStateManager.render(graphics);
        }
    }

    public void draw() {
        Graphics g2 = (Graphics)this.getGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
    }
    
    @Override
    public void run() {
        try {
            init();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        final double GAME_FPS = 60.0 ;
        final double TIME_BEFORE_UPDATE = 1_000_000_000 / GAME_FPS;
        final int UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TOTAL_TIME_BEFORE_RENDER = 1_000_000_000 / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1_000_000_000);
        oldFrameCount = 0;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while (( (now - lastUpdateTime) > TIME_BEFORE_UPDATE) && (updateCount < UPDATES_BEFORE_RENDER) ){
                try {
                    update();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

                try {
                    input(mouse, key);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }

            if(now - lastUpdateTime > TIME_BEFORE_UPDATE) {
                lastUpdateTime = now - TIME_BEFORE_UPDATE;
            }

            try {
                input(mouse, key);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            render();
            draw();

            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1_000_000_000);
            if(thisSecond > lastSecondTime) {
                if(frameCount != oldFrameCount) {
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

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
