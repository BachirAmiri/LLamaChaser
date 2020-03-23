package org.example.models;

import org.example.enums.Orientation;

import java.util.List;

import static org.example.enums.Orientation.*;

public class Adventurer {
    private int x;
    private int y;
    private Orientation orientation;
    private int maxX;
    private int maxY;
    private String name;
    private int treasureCount = 0;
    private List<Character> instructions;


    public void turnLeft() {
        switch (this.orientation) {
            case E:
                this.orientation = N;
                break;
            case N:
                this.orientation = W;
                break;
            case S:
                this.orientation = E;
                break;
            case W:
                this.orientation = S;
                break;
        }
    }

    public void turnRight() {
        switch (this.orientation) {
            case E:
                this.orientation = S;
                break;
            case N:
                this.orientation = E;
                break;
            case S:
                this.orientation = W;
                break;
            case W:
                this.orientation = N;
                break;
        }
    }

    public void advance(Map[][] map) {
        System.out.println(this);
        switch (this.orientation) {
            case S:
                if (y + 1 <= this.maxY - 1 && !map[x][y + 1].blocked()) {
                    map[x][y + 1].lock(this.name);
                    map[x][y].unlock();
                    this.y++;
                    if (map[x][y].getTreasure()) {
                        this.treasureCount++;
                    }
                }
                break;
            case N:
                if (y - 1 >= 0 && !map[x][y - 1].blocked()) {
                    map[x][y - 1].lock(this.name);
                    map[x][y].unlock();
                    this.y--;
                    if (map[x][y].getTreasure()) {
                        this.treasureCount++;
                    }
                }
                break;
            case E:
                if (x + 1 <= this.maxX - 1 && !map[x + 1][y].blocked()) {
                    map[x + 1][y].lock(this.name);
                    map[x][y].unlock();
                    this.x++;
                    if (map[x][y].getTreasure()) {
                        this.treasureCount++;
                    }
                }
                break;
            case W:
                if (x - 1 >= 0 && !map[x - 1][y].blocked()) {
                    map[x - 1][y].lock(this.name);
                    map[x][y].unlock();
                    this.x--;
                    if (map[x][y].getTreasure()) {
                        this.treasureCount++;
                    }
                }
                break;
        }
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

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public void setTreasureCount(int treasureCount) {
        this.treasureCount = treasureCount;
    }

    public List<Character> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Character> instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adventurer(int x, int y, Orientation orientation, int maxX, int maxY, String name,
                      List<Character> instructions) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.maxX = maxX;
        this.maxY = maxY;
        this.name = name;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Adventurer {" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                ", name='" + name + '\'' +
                ", treasureCount=" + treasureCount +
                ", instructions=" + instructions +
                '}';
    }
}
