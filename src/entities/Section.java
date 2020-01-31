package entities;

import entities.players.Player;
import java.io.Serializable;
import java.util.List;

public class Section implements Serializable {

    private String name;
    private List<Player> buildSpaces;
    private int buildPoints;
    private boolean scored = false;

    // constructor
    public Section(String name, List<Player> buildSpaces, int buildPoints) {
        this.name = name;
        this.buildSpaces = buildSpaces;
        this.buildPoints = buildPoints;
    }

    // getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getBuildSpaces() {
        return buildSpaces;
    }

    public void setBuildSpaces(List<Player> buildSpaces) {
        this.buildSpaces = buildSpaces;
    }

    public int getBuildPoints() {
        return buildPoints;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public void setBuildPoints(int buildPoints) {
        this.buildPoints = buildPoints;
    }// end of getters setters
}
