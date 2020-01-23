package caylus;

import static caylus.Phase2.getAvailableBuildings;
import static caylus.Phase2.printOptions;
import static caylussetup.CreateBuildings.*;
import entities.Block;
import entities.players.Player;
import interfaces.Phases;
import java.util.List;
import java.util.Scanner;

public class Phase3 implements Phases {

    @Override
    public void play(Game game, Scanner sc) {
        for (int i = 0; i < 6; i++) { // activate special buildings
            Block block = game.getRoad().get(i);
            if ((block.getBuilding() != null) // if building is not null and has worker or is library or hotel
                    && (block.getWorkers().size() > 0
                    || block.getBuilding() == hotel
                    || block.getBuilding() == library)) {
                block.getBuilding().activate(block.getWorkers().get(0));
            }
        }
    }
}
