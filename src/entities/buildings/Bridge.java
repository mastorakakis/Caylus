package entities.buildings;

import caylus.Game;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton pattern
public class Bridge extends Building {

    private static Bridge bridgeInstance = new Bridge("Bridge");
    public static List<Player> positionList = new ArrayList(); // TODO change to private
    private static int activationMoney = Bridge.getPositionList().size() + 1;

    // constructor private
    private Bridge(String name) {
        super(name);
    }

    // getters setters
    public static Bridge getBridgeInstance() {
        return bridgeInstance;
    }

    public static List<Player> getPositionList() {
        return positionList;
    }

    public static void setPositionList(List<Player> positionList) {
        if (positionList.size() < 0) {
            throw new IllegalArgumentException("Bridge position list cannot be negative");
        }
        if (positionList.size() > 5) {
            throw new IllegalArgumentException("Bridge position list cannot be over 5");
        }
        Bridge.positionList = positionList;
    }

    public static int getActivationMoney() {
        return activationMoney;
    }

    public static void setActivationMoney(int activationMoney) {
        if (activationMoney < 0) {
            throw new IllegalArgumentException("Bridge activation ammount cannot be negative");
        }
        if (activationMoney > 5) {
            throw new IllegalArgumentException("Bridge activation ammount cannot be over 5");
        }
        Bridge.activationMoney = activationMoney;
    }// end of getters setters

    @Override
    public Building activate(Game game, List<Player> players, Scanner sc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
