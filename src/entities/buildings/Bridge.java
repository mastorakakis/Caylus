package entities.buildings;

import entities.players.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Singleton pattern
public class Bridge implements Serializable {

    private static Bridge bridgeInstance = new Bridge("Bridge");
    private String name; // TODO maybe delete name
    private static List<Player> positionList = new ArrayList();

    // constructor private
    private Bridge(String name) {
        this.name = name;
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
    }// end of getters setters

    public static void playerPass(Player player) {
        // if coming from gate player already passed
        if (!Bridge.getPositionList().contains(player)) {
            Bridge.getPositionList().add(player); // add player to bridge
            if (Bridge.getPositionList().size() == 1) { // if player is first
                player.setMoney(player.getMoney() + 1); // player earns 1 denier
            }
        }
        System.out.println(player.getColor() + " passes");
    }
}
