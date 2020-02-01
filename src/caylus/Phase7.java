package caylus;
// end of turn

import entities.players.Player;
import java.util.Scanner;

public class Phase7 {

    public static void play(Game game, Scanner sc) {
        System.out.println("\nPhase 7: End of turn");
        moveBailiff(game);

    }

    // move bailiff
    public static void moveBailiff(Game game) {
        int bailiffPosition = game.getBailiff().getPosition();
        int provostPosition = game.getProvost().getPosition();
        if (bailiffPosition < provostPosition) {
            game.getBailiff().setPosition(bailiffPosition + 2);
        } else {
            game.getBailiff().setPosition(bailiffPosition + 1);
        }
        game.getProvost().setPosition(game.getBailiff().getPosition());
        System.out.println("Bailiff new position=" + game.getBailiff().getPosition());
        System.out.println("Provost new position=" + game.getProvost().getPosition());

        EndOfGame.checkSectionScoring(game);
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
    }
}
