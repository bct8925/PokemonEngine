package com.bri64.Pokeman;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    // Variables
    private Pokeman main;
    private BufferedImage buffer;
    private Graphics2D g2;

    private boolean loading = true;
    public boolean isLoading() {
        return loading;
    }
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    private Rectangle2D.Double fadeRect;
    private boolean fadeIn = false;
    private boolean fadeOut = false;
    private int fade = 0;
    public void fIn() {
        fadeIn = true;
        fade = 255;
    }
    public void fOut() {
        fadeOut = true;
        fade = 0;
    }

    // Constructor
    public GamePanel(Pokeman main) {
        this.main = main;
        fadeRect = new Rectangle2D.Double(1, 1, 1, 1);
        setOpaque(false);
    }

    // Custom paint function
    protected void paintComponent(Graphics g) {

        // Setup
        super.paintComponent(g);
        buffer = new BufferedImage(main.getWindowWidth(), main.getWindowHeight(), BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) buffer.getGraphics();

        if (!loading) {
            // Background
            g2.setColor(new Color(255, 255, 255, 255));   // Set color
            g2.fillRect(0, 0, main.getWindowWidth(), main.getWindowHeight());   // Fill background

            // Zone
            g2.drawImage(main.getCurZone().getImage(), main.getBgX(), main.getBgY(), this);

            // Player
            g2.drawImage(main.getPlayer().getImage().getScaledInstance(main.getPlayer().getZoomWidth(), main.getPlayer().getZoomHeight(), Image.SCALE_SMOOTH), main.getPlayer().getX(), main.getPlayer().getY(), this);

            // Text box

            if (fadeIn) {
                fadeRect.setRect(0, 0, main.getWindowWidth(), main.getWindowHeight());
                g2.setColor(new Color(0, 0, 0, fade));   // Set color
                g2.fill(fadeRect);   // Fill
                fade -= 5;
                if (fade <= 0) {
                    fade = 0;
                    fadeIn = false;
                }
            }
            else if (fadeOut) {
                fadeRect.setRect(0, 0, main.getWindowWidth(), main.getWindowHeight());
                g2.setColor(new Color(0, 0, 0, fade));   // Set color
                g2.fill(fadeRect);   // Fill
                fade += 5;
                if (fade >= 255) {
                    fade = 0;
                    fadeOut = false;
                    loading = true;
                }
            }

        }
        else {
            g2.setColor(new Color(0, 0, 0, 255));   // Set color
            g2.fillRect(0, 0, main.getWindowWidth(), main.getWindowHeight());   // Fill background
        }

        // End
        g.drawImage(buffer, 0, 0, getWidth(), getHeight(), this);
        g2.dispose();
        g.dispose();

    }

    // Needed, idk man
    public Dimension getPreferredSize() {
        return new Dimension(main.getWindowWidth(), main.getWindowHeight());
    }
}
