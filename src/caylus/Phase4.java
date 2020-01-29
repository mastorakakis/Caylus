package caylus;

import static caylus.Game.WARNING;
import entities.players.Player;
import entities.players.UserPlayer;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

// the provost's move
public class Phase4 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 4: The Provost's Move");
        List<Player> playerList = game.getBridge().getPositionList();
        // for every player in bridge
        for (Player player : playerList) {
            while (true) {
                int provostPosition = game.getProvost().getPosition();
                try {// ask player to move provost
                    String message = player.getColor()
                            + " move Provost\n1)Forward 2)Backwards 3)Don't move";
                    String message2 = "How many steps\n1)1 2)2 3)3";
                    int choice = Functions.inputValidation(1, 3, message, player, sc);
                    // if move provost
                    if (choice != 3) {
                        int choice2 = Functions.inputValidation(1, 3, message2,
                                player, sc);
                        // if not enough money
                        if (choice2 > player.getMoney()) {
                            System.out.println(player.getColor()
                                    + " doen't have enough money");
                            continue; // ask again
                        }// move provost
                        if (choice == 1) {
                            game.getProvost().setPosition(game.getProvost().getPosition()
                                    + choice2);
                        } else {
                            game.getProvost().setPosition(game.getProvost().getPosition()
                                    - choice2);
                        }// pay money
                        player.setMoney(player.getMoney() - choice2);
                        break;
                    } else {
                        break;
                    }// catch provost out of limit
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    // cancel provost movement
                    game.getProvost().setPosition(provostPosition);
                }
            } //end of while
            System.out.println("Provost new position = "
                    + (game.getProvost().getPosition()));
        }// end of for
    }
}
