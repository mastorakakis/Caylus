package entities.buildings;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

public class Bridge {

    private static final String name = "Bridge";
    private static List<Player> positionList = new ArrayList();
    private static int activationMoney;

    public Bridge() {
    }

    public static List<Player> getPositionList() {
        return positionList;
    }

    public static void setPositionList(List<Player> positionList) {
        Bridge.positionList = positionList;
    }

    public static int getActivationMoney() {
        return activationMoney;
    }

    public static void setActivationMoney(int activationMoney) {
        Bridge.activationMoney = activationMoney;
    }

}
