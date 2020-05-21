package com.commando.game.tiles;

import com.commando.game.graphics.Sprite;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class TileManager {

    public static ArrayList<TileMap> tileMap;

    public TileManager() {
        tileMap = new ArrayList<TileMap>();
    }

    public TileManager(String path) throws ParserConfigurationException {
        tileMap = new ArrayList<TileMap>();
        addTileMap(path, 64, 64);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) throws ParserConfigurationException {
        String imagePath;

        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        int tileColumns;
        int nr_of_layers = 0;
        Sprite sprite;

        String[] data = new String[4];

        // basically we are parsing the xml file (where we store all the info about the map )
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse((new File(path)));
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element element = (Element) node;

            imagePath = element.getAttribute("name");
            tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
            tileCount = Integer.parseInt(element.getAttribute("tilecount"));
            tileColumns = Integer.parseInt(element.getAttribute("columns"));
            sprite = new Sprite("resources\\tile\\" + imagePath + ".png", tileWidth, tileHeight);

            list = document.getElementsByTagName("layer");
            nr_of_layers = list.getLength();

            for(int i = 0; i < nr_of_layers; i++) {
                node = list.item(i);
                element = (Element)node;
                if(i <= 0) {
                    width = Integer.parseInt(element.getAttribute("width"));
                    height = Integer.parseInt(element.getAttribute("height"));
                }

                data[i] = element.getElementsByTagName("data").item(0).getTextContent();
                //System.out.println("......................\n" + data[i]);  // printing the data

                if(i >= 1) {
                    tileMap.add(new TileMapNormal(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                } else {
                    tileMap.add(new TileMapSolid(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }

            }

        }
        catch (Exception e) {
            System.out.print("Error: TILEMANAGER: Can't read tile map \n");
        }
    }

    public void render(Graphics2D graphics) {
        for(int i = 0; i < tileMap.size(); i++) {
            tileMap.get(i).render(graphics);
        }
    }
}
