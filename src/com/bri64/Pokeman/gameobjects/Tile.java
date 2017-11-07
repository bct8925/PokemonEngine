package com.bri64.Pokeman.gameobjects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    public BufferedImage getImage() {
        return image;
    }

    private int x;
    private int y;
    private boolean solid;
    private int layer;
    private String property;
    private boolean door;
    private boolean interactable;
    private String doorZone;
    private String doorTag;
    private int doorDir;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isSolid() {
        return solid;
    }
    public int getLayer() {
        return layer;
    }
    public boolean isDoor() {
        return door;
    }
    public boolean isInteractable() {
        return interactable;
    }
    public String getDoorZone() {
        return doorZone;
    }
    public String getDoorTag() {
        if (doorTag != null) {
            return doorTag;
        }
        return "null";
    }
    public int getDoorDir() {
        return doorDir;
    }


    public Tile(int x, int y, boolean solid, int layer, String property, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.layer = layer;
        this.property = property;
        this.image = image;
        this.interactable = false;
        if (property != null) {
            setProperty();
        }
    }
    private void setProperty() {
        if (property.split(":")[0].equals("door")) {
            door = true;
            doorTag = property.split(":")[1];
            doorZone = property.split(":")[2] + ".zone";
            if (property.split(":").length > 3) {
                doorDir = Integer.parseInt(property.split(":")[3]);
            }
            else {
                doorDir = 4;
            }
        }
    }

}
