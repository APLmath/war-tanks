package me.andrewlee.wartanks;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by andrewlee on 5/17/15.
 */
public class Game {
    private GameListener listener;

    private Random die;
    private Set<Tank> tanks;
    private int currentPlayer;
    private int turnPointsLeft;

    public Game(GameListener listener) {
        this.listener = listener;

        die = new Random();
        tanks = new HashSet<>();
        tanks.add(new Tank(0, 6, 0, 0));
        tanks.add(new Tank(0, 6, 0, 2));
        tanks.add(new Tank(0, 6, 0, 4));
        tanks.add(new Tank(0, 6, 2, 2));
        tanks.add(new Tank(1, 6, 7, 1));
        tanks.add(new Tank(1, 6, 7, 3));
        tanks.add(new Tank(1, 6, 7, 5));
        tanks.add(new Tank(1, 6, 5, 3));

        currentPlayer = 0;
        newTurn();
    }

    private void newTurn() {
        turnPointsLeft = die.nextInt(6) + 1;
    }

    private void maybeAdvancePlayer() {
        if (turnPointsLeft == 0) {
            currentPlayer = (currentPlayer + 1) % 2;
            newTurn();
        }
    }

    public void move(Tank tank, int x, int y) {
        if (tank.getPlayer() == currentPlayer &&
                x >= 0 && x < 8 &&
                y >= 0 && y < 6 &&
                (Math.abs(tank.getX() - x) == 1 || Math.abs(tank.getY() - y) == 1 ) &&
                getTankAtPosition(x, y) == null) {
            tank.setX(x);
            tank.setY(y);
            turnPointsLeft--;
            maybeAdvancePlayer();

            listener.refresh();
        }
    }

    public int getTurnPointsLeftForPlayer(int player) {
        return player == currentPlayer ? turnPointsLeft : 0;
    }

    public Tank getTankAtPosition(int x, int y) {
        for (Tank tank : tanks) {
            if (tank.getX() == x && tank.getY() == y) {
                return tank;
            }
        }
        return null;
    }
}
