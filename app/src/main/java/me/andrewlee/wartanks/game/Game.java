package me.andrewlee.wartanks.game;

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
        if (getWinner() < 0 && turnPointsLeft == 0) {
            currentPlayer = (currentPlayer + 1) % 2;
            newTurn();
        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getWinner() {
        int player0Size = 0;
        int player1Size = 0;
        for (Tank tank : tanks) {
            if (tank.getPlayer() == 0) {
                player0Size++;
            } else {
                player1Size++;
            }
        }
        if (player0Size > 0 && player1Size == 0) {
            return 0;
        } else if (player0Size == 0 && player1Size > 0) {
            return 1;
        } else{
            return -1;
        }
    }

    public void move(Tank tank, int x, int y) {
        if (getWinner() >= 0) {
            return;
        }
        if (tank.getPlayer() == currentPlayer &&
                x >= 0 && x < 8 &&
                y >= 0 && y < 6 &&
                (Math.abs(tank.getX() - x) + Math.abs(tank.getY() - y) == 1) &&
                getTankAtPosition(x, y) == null) {
            tank.setX(x);
            tank.setY(y);
            turnPointsLeft--;

            maybeAdvancePlayer();
            listener.refresh();
        }
    }

    public void attack(Tank attacker, Tank defender) {
        if (getWinner() >= 0) {
            return;
        }
        if (attacker.getPlayer() == currentPlayer &&
                defender.getPlayer() != currentPlayer) {
            int turnPointsNeeded = 2 * (Math.abs(attacker.getX() - defender.getX()) + Math.abs(attacker.getY() - defender.getY()));
            if (turnPointsLeft >= turnPointsNeeded) {
                defender.setHealth(defender.getHealth() - 1);
                if (defender.getHealth() <= 0) {
                    tanks.remove(defender);
                }
                turnPointsLeft -= turnPointsNeeded;

                maybeAdvancePlayer();
                listener.refresh();
            }
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
