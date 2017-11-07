package com.bri64.Pokeman.utils;

@SuppressWarnings({"WeakerAccess", "unused"})
public class KeyReader {
    private boolean key = false;
    private boolean state = true;
    public boolean keyDown() {
        return key;
    }
    public void setDown(boolean key) {
        this.key = key;
    }

    public boolean keyPressed() {
        if (key && state) {
            state = false;
            return true;
        }
        else if (!key) {
            state = true;
        }
        return false;
    }
}
