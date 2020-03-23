package org.example.models;

import java.util.List;

public class Instance {
    private List<Adventurer> adventurerList;
    private Map[][] map;

    public List<Adventurer> getAdventurerList() {
        return adventurerList;
    }

    public void setAdventurerList(List<Adventurer> adventurerList) {
        this.adventurerList = adventurerList;
    }

    public Map[][] getMap() {
        return map;
    }

    public void setMap(Map[][] map) {
        this.map = map;
    }
}
