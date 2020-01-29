package caylus;

import static caylus.Game.WARNING;
import entities.players.Player;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

// the provost's move
public class Phase4 {

    public static void play(Game game, Scanner sc) {
        System.out.println("Phase 4: The Provost's Move");
        List<Player> playerList = game.getBridge().getPositionList();
        for (Player player : playerList) {
            boolean askAgain = true;
            while (askAgain == true) {
                try {// ask player to move provost
                    String message = player.getColor()
                            + " move Provost\n1)Forward 2)Backwards 3)Don't move";
                    String message2 = "How many steps\n1)1 2)2 3)3";
                    int choice = Functions.inputValidation(1, 3, message,
                            WARNING, sc);
                    // if don't move provost
                    if (choice != 3) {
                        int choice2 = Functions.inputValidation(1, 3, message2,
                                WARNING, sc);
                        // if not enough money
                        if (choice2 > player.getMoney()) {
                            System.out.println(player.getColor()
                                    + " doen't have enough money");
                            continue; // ask again
                        }// if move provost
                        if (choice == 1) {
                            game.getProvost().setPosition(game.getProvost().getPosition()
                                    + choice2);
                        } else {
                            game.getProvost().setPosition(game.getProvost().getPosition()
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
                    + (game.getProvost().getPosition() + 1));
        }
    }
}
