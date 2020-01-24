package entities.buildings;

import caylus.Game;
import entities.Resources;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

public class StoneBuilding extends Building {

    private int buildPoints;
    private int buildFavors;
    private Resources buildResources;
    private int activationMoney;
    private int activationPoints;
    private Resources activationResources;
    private Resources activationRentResources;

    // constructor
    public StoneBuilding() {
    }

    // constructor
    public StoneBuilding(int buildPoints, int buildFavors, Resources buildResources,
            int activationMoney, int activationPoints, Resources activationResources,
            Resources activationRentResources, String name) {
        super(name);
        this.buildPoints = buildPoints;
        this.buildFavors = buildFavors;
        this.buildResources = buildResources;
        this.activationMoney = activationMoney;
        this.activationPoints = activationPoints;
        this.activationResources = activationResources;
        this.activationRentResources = activationRentResources;

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
    }

    public int getBuildFavors() {
        return buildFavors;
    }

    public void setBuildFavors(int buildFavors) {
        this.buildFavors = buildFavors;
    } // end of getters setters

    @Override
    public Building activate(List<Player> players, Scanner sc, Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
