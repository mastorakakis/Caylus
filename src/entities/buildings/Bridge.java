package entities.buildings;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

public class Bridge {

    private static final String name = "Bridge";
    private static List<Player> positionList = new ArrayList();
    private static int activationMoney;

    // constructor
    public Bridge() {
    }

    // getters setters
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

}
