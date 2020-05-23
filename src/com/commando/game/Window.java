package com.commando.game;

import javax.swing.*;

/**
 * @author Timofti Gabriel
 */

public class Window extends JFrame{

    JButton button = new JButton("Click to Close!");

    public Window() {
        setTitle("Commando");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        /*this.add(button);
        button.addActionListener(e -> {this.dispose();});*/

    }
}
