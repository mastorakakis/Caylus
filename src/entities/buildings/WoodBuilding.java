package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

public class WoodBuilding extends Building {

    private int buildPoints;
    private Resources buildResources;
    private int activationMoney;
    private Resources activationResources;

    public WoodBuilding() {
    }

    public WoodBuilding(int buildPoints, Resources buildResources,
            int activationMoney, Resources activationResources, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildResources = buildResources;
        this.activationMoney = activationMoney;
        this.activationResources = activationResources;
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

    public int getIncomeMoney() {
        return activationMoney;
    }

    public void setActivationMoney(int activationMoney) {
        this.activationMoney = activationMoney;
    }

    public Resources getActivationResources() {
        return activationResources;
    }

    public void setActivationResources(Resources activationResources) {
        this.activationResources = activationResources;
    } // end of getters setters

    @Override
    public Building activate(List<Player> players, Scanner sc, Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
