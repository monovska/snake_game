package com.example.bishkoti.snake_game.operations;

/**
 * Created by Bishkoti on 15.2.2018 г..
 */

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        com.example.bishkoti.snake_game.operations.Coordinates that = (com.example.bishkoti.snake_game.operations.Coordinates) o;

        if (getX() != that.getX()) return false; // checks if the x coordinates are the same
        return getY() == that.getY(); // checks if the y coordinates are the same
    }
}