package com.commando.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */
public class Font {
    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int width;
    public int height;
    private int widthLetter;
    private int heightLetter;

    public Font(String file) {
        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        widthLetter = FONTSHEET.getWidth() / width;
        heightLetter = FONTSHEET.getHeight() / height;
        loadSpriteArray();
    }

    public Font(String file, int w, int h) {
        this.width = w;
        this.height = h;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        widthLetter = FONTSHEET.getWidth() / width;
        heightLetter  = FONTSHEET.getHeight() / height;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        width = i;
        widthLetter = FONTSHEET.getWidth()/ width;
    }

    public void setHeight(int i) {
        height = i;
        heightLetter = FONTSHEET.getHeight() / height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private BufferedImage loadFont(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File(file));  // modified ! Commit 10 for old
            // System.out.println(sprite);
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[widthLetter][heightLetter];

        for (int x = 0; x < widthLetter; x++) {
            for (int y = 0; y < heightLetter; y++) {
                spriteArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getFontSheet() { return FONTSHEET; }

    public BufferedImage getLetter(int x, int y) {
        return FONTSHEET.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage getFont(char letter) {
        int value = letter-65;

        int x = value % widthLetter;
        int y = value / widthLetter;
        System.out.println(x + ", " + y);

        return getLetter(x,y);
    }
}
