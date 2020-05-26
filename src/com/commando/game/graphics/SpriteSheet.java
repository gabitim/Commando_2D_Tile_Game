package com.commando.game.graphics;

import com.commando.game.util.Vector2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class SpriteSheet {

    private final int TILE_SIZE = 32;

    public static Font currentFont;

    private Sprite SPRITESHEET = null;
    private Sprite[][] spriteArray;
    public int width;
    public int height;
    private int widthSprite;
    private int heightSprite;

    private String file;

    public SpriteSheet(String file) {
        this.file = file;
        width = TILE_SIZE;
        height = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = new Sprite(loadSprite(file));

        widthSprite = SPRITESHEET.image.getWidth() / width;
        heightSprite = SPRITESHEET.image.getHeight() / height;
        loadSpriteArray();
    }

    public SpriteSheet(String file, int width, int height) {
        this.width = width;
        this.height = height;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = new Sprite(loadSprite(file));

        widthSprite = SPRITESHEET.image.getWidth() / width;
        heightSprite = SPRITESHEET.image.getHeight() / height;
        loadSpriteArray();
    }

    public SpriteSheet(Sprite sprite, String name, int width, int height) {
        this.width = width;
        this.height = height;

        System.out.println("Loading: " + name + "...");
        SPRITESHEET = sprite;

        widthSprite = SPRITESHEET.image.getWidth() / width;
        heightSprite = SPRITESHEET.image.getHeight() / height;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        width = i;
        widthSprite = SPRITESHEET.image.getWidth()/ width;
    }

    public void setHeight(int i) {
        height = i;
        heightSprite = SPRITESHEET.image.getHeight() / height;
    }

    public void setEffect(Sprite.effect effect) {
        SPRITESHEET.setEffect(effect);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getRows() { return heightSprite; }
    public int getColumns() { return widthSprite; }
    public int getTotalTiles() { return widthSprite * heightSprite; }
    public String getFileName() { return file; }

    public Sprite getSpriteSheet() { return SPRITESHEET; }

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * width, y * height, width, height);
    }

    public Sprite getSprite(int x, int y, int width, int height) {
        return SPRITESHEET.getSubimage(x * width, y * height, width, height);
    }

    public Sprite getNewSprite(int x, int y) {
        return SPRITESHEET.getNewSubimage(x * width, y * height, width, height);
    }

    public BufferedImage getSubImage(int x, int y, int width, int height) {
        return SPRITESHEET.image.getSubimage(x, y, width, height);
    }

    public Sprite[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public Sprite[][] getSpriteArray() {
        return spriteArray;
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new Sprite[heightSprite][widthSprite];

        for (int y = 0; y < heightSprite; y++) {
            for (int x = 0; x < widthSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
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

        currentFont = font;

        float x = position.x;
        float y = position.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                graphics2D.drawImage(font.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            x += xOffSet;
            y += yOffSet;
        }
    }

    public static void drawArray(Graphics2D graphics, String word, Vector2d position, int size) {
        drawArray(graphics, currentFont, word, position, size, size, size, 0);
    }

    public static void drawArray(Graphics2D graphics, String word, Vector2d position, int size, int xOffset) {
        drawArray(graphics, currentFont, word, position, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D graphics, String word, Vector2d position, int width, int height, int xOffset) {
        drawArray(graphics, currentFont, word, position, width, height, xOffset, 0);
    }

    public static void drawArray(Graphics2D graphics, Font font, String word, Vector2d position, int size, int xOffset) {
        drawArray(graphics, font, word, position, size, size, xOffset, 0);
    }
}
