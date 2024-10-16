package com.bullethell.game.utilities;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class PopUp extends JFrame implements ActionListener {

    protected JFrame frame;

    protected PopUp(String title, int width, int height)
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void close()
    {
        frame.dispose();
    }
}
