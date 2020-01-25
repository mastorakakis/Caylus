package caylus;

import static caylus.Game.WARNING;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class Phase4 {

    public static void play(Game game, Scanner sc) {
        List<Player> playerList = game.bridge.getPositionList();
        for (Player player : playerList) {
            boolean askAgain = true;
            while (askAgain == true) {
                try {// ask player to move provost
                    String message = player.getColor()
                            + " move Provost\n1)Forward 2)Backwards 3)Don't move";
                    String message2 = "How many steps\n1)1 2)2 3)3";
                    int choice = Functions.inputValidation(1, 3, message,
                            WARNING, sc);
                    // if move provost
                    if (choice != 3) {
                        int choice2 = Functions.inputValidation(1, 3, message2,
                                WARNING, sc);
                        // if doensn't have enough money
                        if (choice2 > player.getMoney()) {
                            System.out.println(player.getColor()
                                    + " doen't have enough money");
                            continue; // ask again
                        }// move provost
                        if (choice == 1) {
                            game.provost.setPosition(game.provost.getPosition()
                                    + choice2);
                        } else {
                            game.provost.setPosition(game.provost.getPosition()
                                    - choice2);
                        }// pay money
                        player.setMoney(player.getMoney() - choice2);
                        break;
                    } // exit while
                    askAgain = false;
                } catch (IllegalArgumentException e) { // catch provost out of limit
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Provost new position = "
                    + (game.provost.getPosition() + 1));
        }
    }
}
