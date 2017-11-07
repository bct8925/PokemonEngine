package com.bri64.Pokeman.gameobjects;

import com.bri64.Pokeman.Pokeman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Zone {

    private Pokeman main;
    private String name;
    private int row;
    private int col;
    private int tileSize = 16;
    private double zoom = 2.3;
    public String getName() {
        return name;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getTileZoom() {
        return (int)(tileSize * zoom);
    }

    /*private ArrayList<Zone> linkedZones = new ArrayList<>();
    public ArrayList<Zone> getLinkedZones() {
        return linkedZones;
    }*/

    private BufferedImage tileset;
    private BufferedImage image;
    private Tile[][] tile;
    public BufferedImage getImage() {
        return image;
    }
    public Tile getTile(int x, int y) {
        return tile[x][y];
    }

    private int spawnX;
    private int spawnY;
    private int spawnDir;
    public int getSpawnX() {
        return spawnX;
    }
    public int getSpawnY() {
        return spawnY;
    }
    public int getSpawnDir() {
        return spawnDir;
    }

    public Zone(Pokeman main, String name, InputStream in) {
        this.main = main;
        this.name = name;
        BufferedReader br;
        String save = null;
        try {
            br = new BufferedReader(new InputStreamReader(in));
            save = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String header = save.split("\\?")[0];

        String tileMap = header.split(":")[0];
        try {
            this.tileset = ImageIO.read(getClass().getResourceAsStream("/tilesets/" + tileMap));
        } catch (IOException e) {
            System.exit(1);
        }

        this.row = Integer.parseInt(header.split(":")[1]);
        this.col = Integer.parseInt(header.split(":")[2]);
        tile = new Tile[row][col];

        String spawnLocation = header.split(":")[3];
        spawnX = Integer.parseInt(spawnLocation.split(",")[0]) / tileSize;
        spawnY = Integer.parseInt(spawnLocation.split(",")[1]) / tileSize;
        spawnDir = Integer.parseInt(spawnLocation.split(",")[2]);

        int x = 0;
        int y = 0;
        String zoneCells = save.split("\\?")[1];
        // Load ZoneCells
        for (String cell : zoneCells.split(";")) {

            int imgX = Integer.parseInt(cell.split(",")[0]);
            int imgY = Integer.parseInt(cell.split(",")[1]);
            int layer = Integer.parseInt(cell.split(",")[2]);
            boolean solid = cell.split(",")[3].equals("1");
            String property = null;
            boolean door = false;
            String doorTag = null;
            String doorZone = null;
            int doorDir = 4;
            if (cell.split(",").length > 4) {
                property = cell.split(",")[4];
            }

            if (y / tileSize == col) {
                x += tileSize;
                y = 0;
            }

            tile[x / tileSize][y / tileSize] = new Tile(x / tileSize, y / tileSize, solid, layer, property, tileset.getSubimage(imgX, imgY, tileSize, tileSize));

            y += tileSize;
        }
    }

    public void drawZone() {
        image = new BufferedImage(row * getTileZoom(), col * getTileZoom(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bg = (Graphics2D) image.getGraphics();
        //bg.fillRect(0, 0, image.getWidth(), image.getHeight());   // Fill image
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bg.drawImage(tile[i][j].getImage().getScaledInstance(getTileZoom(), getTileZoom(), Image.SCALE_SMOOTH), i * getTileZoom(), j * getTileZoom(), main.getGamePanel());
            }
        }
    }
    public ArrayList<Zone> getLinkedZones() {
        ArrayList<Zone> linkedZones = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (tile[i][j].isDoor() && !tile[i][j].getDoorZone().equals(main.getPrevZone().getName())) {
                    linkedZones.add(new Zone(main, tile[i][j].getDoorZone(), getClass().getResourceAsStream("/zones/" + tile[i][j].getDoorZone())));
                }
            }
        }
        linkedZones.forEach(Zone::drawZone);
        if (main.getPrevZone() != null) {
            linkedZones.add(main.getPrevZone());
        }
        return linkedZones;
    }


}
