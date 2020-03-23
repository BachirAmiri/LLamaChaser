package org.example.models;

public class Map {

    String adventurer = "";
    boolean mountain = false;
    int treasureCount = 0;

    public boolean blocked() {
        return (adventurer != null && !adventurer.equals("")) || this.mountain;
    }

    public void lock(String name) {
        this.adventurer = name;
    }

    public void unlock() {
        this.adventurer = "";
    }

    public boolean getTreasure() {
        if (this.treasureCount > 0) {
            this.treasureCount--;
            return true;
        }

        return false;
    }


    public String getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(String adventurer) {
        this.adventurer = adventurer;
    }

    public boolean isMountain() {
        return mountain;
    }

    public void setMountain(boolean mountain) {
        this.mountain = mountain;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public void setTreasureCount(int treasureCount) {
        this.treasureCount = treasureCount;
    }



    @Override
    public String toString() {
        if (adventurer != null && !adventurer.equals("")) {
            return "A(" + this.adventurer+")";
        }

        if (mountain){
            return "M";
        }

        if (treasureCount > 0) {
            return "T("+treasureCount+")";
        }

        return ".";
    }
}
