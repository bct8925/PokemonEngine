package com.bri64.Pokeman;

import com.bri64.Pokeman.gameobjects.Player;
import com.bri64.Pokeman.gameobjects.Sprite;
import com.bri64.Pokeman.gameobjects.Tile;
import com.bri64.Pokeman.gameobjects.Zone;
import com.bri64.Pokeman.utils.AudioManager;
import com.bri64.Pokeman.utils.KeyReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.TimerTask;

@SuppressWarnings({"WeakerAccess"})
public class Pokeman {

    // Main variables
    private JFrame frame;
    private GamePanel gamePanel;
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    // Window Variables
    private int windowWidth = 640;
    private int windowHeight = 480;
    public int getWindowWidth() {
        return windowWidth;
    }
    public int getWindowHeight() {
        return windowHeight;
    }

    // Input variables
    private KeyReader upKey = new KeyReader();
    private KeyReader downKey = new KeyReader();
    private KeyReader leftKey = new KeyReader();
    private KeyReader rightKey = new KeyReader();
    private KeyReader runKey = new KeyReader();
    private KeyReader useKey = new KeyReader();

    // Objects
    private Player player;
    private ArrayList<Zone> linkedZones = new ArrayList<>();
    private Zone curZone;
    private Zone prevZone;
    private boolean zoneChosen = false;
    private boolean zonesLoaded = false;
    public Player getPlayer() {
        return player;
    }
    public Zone getCurZone() {
        return curZone;
    }
    public Zone getPrevZone() {
        return prevZone;
    }

    // 3D
    private int bgX;
    private int bgY;
    public int getBgX() {
        return bgX;
    }
    public int getBgY() {
        return bgY;
    }
    public void setBgX(int bgX) {
        this.bgX = bgX;
    }
    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    // Constructor
    private Pokeman() {
        gamePanel = new GamePanel(this);    // Create instance of drawing panel
        createFrame();  // Create frame
        keyInput(); // Setup key listener
        init(); // Setup
        frame.setVisible(true); // Make frame visible after setup
        loop(); // Start repaint loop
    }

    // Main
    public static void main( String[ ] args ) {
        SwingUtilities.invokeLater(() -> {
            // Set Look & Feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            // Launch Program
            new Pokeman();
        });
    }

    // Loop
    private void loop() {
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Logic
                if (gamePanel.isLoading()) {
                    loading();
                }
                movement();
                interaction();
            }
        }, 0, 10);  // Redraw every 10 milliseconds
    }

    // Moving the player
    private void movement() {
        if (!player.isAnimating() && upKey.keyDown()) {
            player.move(0, runKey.keyDown());
        }
        else if (!player.isAnimating() && downKey.keyDown()) {
            player.move(1, runKey.keyDown());
        }
        else if (!player.isAnimating() && leftKey.keyDown()) {
            player.move(2, runKey.keyDown());
        }
        else if (!player.isAnimating() && rightKey.keyDown()) {
            player.move(3, runKey.keyDown());
        }
        if (!player.isStopMoving() && !upKey.keyDown() && !downKey.keyDown() && !leftKey.keyDown() && !rightKey.keyDown()) {
            player.setStopMoving(true);
        }
    }

    // Interaction
    private void interaction() {
        if (!player.isAnimating()) {
            if (useKey.keyPressed() && !player.isFacing().isInteractable()) {
                // player.isFacing().interact();
                System.out.println(player.isFacing().getX() + " by " + player.isFacing().getY());
            }
        }
    }

    // Zone Utilities
    public void changeZone(Zone zone) {
        zoneChosen = false;
        gamePanel.setLoading(true);
        curZone = zone;
        curZone.drawZone();
        player.setLocation(curZone.getSpawnX(), curZone.getSpawnY());
        player.getSprite().changeSprite(curZone.getSpawnDir());
        zoneChosen = true;
    }
    public void doorZone(Tile tile) {
        long curTime = System.currentTimeMillis();
        zoneChosen = false;
        gamePanel.fOut();
        AudioManager.playMP3("/door.mp3", false);

        while (!gamePanel.isLoading() || !zonesLoaded) {
            System.out.print("");
        }

        prevZone = curZone;
        boolean found = false;
        for (Zone linkedZone : linkedZones) {
            if (linkedZone.getName().equals(tile.getDoorZone())) {
                curZone = linkedZone;
                found = true;
                break;
            }
        }
        if (!found) {
            curZone = new Zone(this, tile.getDoorZone(), getClass().getResourceAsStream("/zones/" + tile.getDoorZone()));
            curZone.drawZone();
        }

        Thread loadThread = new Thread(()-> {
            zonesLoaded = false;
            //System.out.println(curZone.getName() + " Linked Zones loading...");
            linkedZones = curZone.getLinkedZones();
            //System.out.println("Zones loaded.");
            zonesLoaded = true;
        });
        loadThread.start();

        Tile temp = null;
        for (int i = 0; i < curZone.getRow(); i++) {
            for (int j = 0; j < curZone.getCol(); j++) {
                if (curZone.getTile(i, j).getDoorTag().equals(tile.getDoorTag())) {
                    temp = curZone.getTile(i, j);
                    player.setLocation(i , j);

                    i = curZone.getRow();
                    break;
                }
            }
        }
        zoneChosen = true;

        while (gamePanel.isLoading()) {
            System.out.print("");
        }

        assert temp != null;
        if (temp.getDoorDir() != 4) {
            player.move(temp.getDoorDir(), false);
        }
        else {
            player.move(player.getDirection(), false);
        }
        System.out.println(System.currentTimeMillis() - curTime);
    }

    // Setup
    private void init() {
        try {
            player = new Player(this, new Sprite(ImageIO.read(getClass().getResourceAsStream("/player.png")), 24, 16));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.centerBox(windowWidth / 2, windowHeight / 2);    // Set player position

        curZone = new Zone(this, "littleroot.zone", getClass().getResourceAsStream("/zones/littleroot.zone"));
        prevZone = curZone;
        Thread loadThread = new Thread(()-> {
            zonesLoaded = false;
            linkedZones = curZone.getLinkedZones();
            zonesLoaded = true;
        });
        loadThread.start();
        player.setup(curZone);
        zoneChosen = true;
        AudioManager.setVolume(0.2F);
        AudioManager.playMP3("/music.mp3", true);
    }
    private void loading() {
        while (!zoneChosen) {
            System.out.print("");
        }
        //long time = System.currentTimeMillis();
        curZone.drawZone();
//                    if (System.currentTimeMillis() - time < 1500) {
//                        try {
//                            Thread.sleep(1500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
        linkedZones = curZone.getLinkedZones();
        gamePanel.fIn();
        gamePanel.setLoading(false);
    }

    // Create JFrame
    private void createFrame() {
        frame = new JFrame("Pokeman");
        frame.getContentPane().add(gamePanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        //frame.setLayout(null);
    }
    private void keyInput() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    upKey.setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    downKey.setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    leftKey.setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    rightKey.setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    runKey.setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    useKey.setDown(true);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    upKey.setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    downKey.setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    leftKey.setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    rightKey.setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    runKey.setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    useKey.setDown(false);
                }
            }
        });
    }
    public void repaint() {
        frame.repaint();
    }
}
