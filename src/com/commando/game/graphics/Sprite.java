package com.commando.game.graphics;

import com.commando.game.util.Vector2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class Sprite {
    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int width;
    public int height;
    private int widthSprite;
    private int heightSprite;

    public Sprite(String file) {
        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        widthSprite = SPRITESHEET.getWidth() / width;
        heightSprite = SPRITESHEET.getHeight() / height;
        loadSpriteArray();
    }

    public Sprite(String file, int w, int h) {
        this.width = w;
        this.height = h;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        widthSprite = SPRITESHEET.getWidth() / width;
        heightSprite = SPRITESHEET.getHeight() / height;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        width = i;
        widthSprite = SPRITESHEET.getWidth()/ width;
    }

    public void setHeight(int i) {
        height = i;
        heightSprite = SPRITESHEET.getHeight() / height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[widthSprite][heightSprite];

        for (int x = 0; x < widthSprite; x++) {
            for (int y = 0; y < heightSprite; y++) {
                spriteArray[x][y] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSpirteSheet() { return SPRITESHEET; }

    public BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2(int i) {
        return spriteArray;
    }

    public static void drawArray(Graphics2D graphics2D, ArrayList<BufferedImage> img, Vector2d position, int width, int height, int xOffSet, int yOffSet)  {
        float x = position.x;
        float y = position.y;

        for (int i = 0; i < img.size(); i++) {
            if(img.get(i) != null) {
                graphics2D.drawImage(img.get(i), (int)x, (int)y, width, height, null);
            }
            x += xOffSet;
            y += yOffSet;
        }
    }

    public static void drawArray(Graphics2D graphics2D, Font font, String word, Vector2d position, int width, int height, int xOffSet, int yOffSet) {
        float x = position.x;
        float y = position.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                graphics2D.drawImage(font.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            x += xOffSet;
            y += yOffSet;
        }
    }






}
