package entities.buildings;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;

public class Bridge extends Building {

    private static List<Player> positionList = new ArrayList();
    private static int activationMoney;
    private static final String name = "Bridge";

    public Bridge() {
    }

    public Bridge(String name) {
        super(name);
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
