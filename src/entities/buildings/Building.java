package entities.buildings;

import caylus.Game;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;

public abstract class Building {

//    private String name = "";
    private String name;

    // constructor
    public Building() {
    }

    // constructor
    public Building(String name) {
        this.name = name;

    }

    // getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }// end of getters setters

    public abstract Building activate(Game game, List<Player> workers, Scanner sc);
}
