package entities.buildings;

import entities.players.Player;
import java.util.Scanner;
import otherClasses.Resources;

public interface Building {

    int getPoints();

    Resources getCostResources();

    Resources getProductionResources();

    public void activation(Player player, Scanner sc);

//    TODO
}
