package com.commando.game.graphics.playerUI;

import com.commando.game.GamePanel;
import com.commando.game.entity.caracters.Hero;
import com.commando.game.graphics.GUI.FillBars;
import com.commando.game.graphics.GUI.Slots;
import com.commando.game.graphics.SpriteSheet;
import com.commando.game.util.KeyHandler;
import com.commando.game.util.MouseHandler;
import com.commando.game.util.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Timofti Gabriel
 */
public class PlayerUI {
        private FillBars healthBar;
        private Slots[] itemSlots;

        public PlayerUI(Hero hero) {
            SpriteSheet bars = new SpriteSheet("resources\\gui\\fillbars.png");
            BufferedImage[] barSprites = {
                    bars.getSubImage(12, 2, 7, 16),
                    bars.getSubImage(39, 0, 7, 14), //the health bar
                    bars.getSubImage(0, 0, 12, 20),
            };

            Vector2d position = new Vector2d(32, GamePanel.height - 52);
            this.healthBar = new FillBars(hero, barSprites, position, 16,16);
        }

        public void update() {
        }

        public void input(MouseHandler mouse, KeyHandler key) {

        }

        public void render(Graphics2D graphics) {
            healthBar.render(graphics);
        }
}
