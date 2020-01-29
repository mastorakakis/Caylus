package entities;

import entities.buildings.Building;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

public class Block {

    private Building building;
    private Building tempBuilding;

    private List<Player> workers = new ArrayList();
    private Player house;

    // constructor
    public Block() {
    }

    // constructor
    public Block(Building buildingBlock) {
        this.building = buildingBlock;
    }

    // getters setters
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Player> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Player> workers) {
        this.workers = workers;
    }

    public Building getTempBuilding() {
        return tempBuilding;
    }

    public void setTempBuilding(Building tempBuilding) {
        this.tempBuilding = tempBuilding;
    }

    public Player getHouse() {
        return house;
    }

    public void setHouse(Player house) {
        this.house = house;
    } // end of getters setters

}
