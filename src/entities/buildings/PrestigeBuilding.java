package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

public class PrestigeBuilding extends Building {

    private int buildPoints;
    private int buildFavors;
    private Resources buildResources;
    private int incomeMoney;

    // constructor
    public PrestigeBuilding() {
    }

    // constructor
    public PrestigeBuilding(int buildPoints, int buildFavors, Resources buildResources,
            int activationMoney, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildFavors = buildFavors;
        this.buildResources = buildResources;
        this.incomeMoney = activationMoney;
    }

    // getters setters
    public int getBuildPoints() {
        return buildPoints;
    }

    public void setBuildPoints(int buildPoints) {
        this.buildPoints = buildPoints;
    }

    public Resources getBuildResources() {
        return buildResources;
    }

    public void setBuildResources(Resources buildResources) {
        this.buildResources = buildResources;
    }

    public int getBuildFavors() {
        return buildFavors;
    }

    public void setBuildFavors(int buildFavors) {
        this.buildFavors = buildFavors;
    }

    public int getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(int incomeMoney) {
        this.incomeMoney = incomeMoney;
    }// end of getters setters

    @Override
    public Building activate(Game game, List<Player> players, Scanner sc) {
        return null;
    }

}
