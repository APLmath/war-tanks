package me.andrewlee.wartanks.game;

/**
 * Created by andrewlee on 5/17/15.
 */
public class Tank {

    private int player;
    private int health;
    private int x;
    private int y;

    public Tank(int player, int health, int x, int y) {
        this.player = player;
        this.health = health;
        this.x = x;
        this.y = y;
    }

    public int getPlayer() {
        return player;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
