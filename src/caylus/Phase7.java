package caylus;
// end of turn

import entities.players.Player;
import interfaces.Phase;
import java.util.Scanner;

public class Phase7 implements Phase {

    @Override
    public void play(Game game, Scanner sc) {
        System.out.println("\nPhase 7: End of turn");
        System.out.println("--------------------");
        moveBailiff(game, sc);
    }

    // move bailiff
    public static void moveBailiff(Game game, Scanner sc) {
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
        System.out.println("");
        GameEnd.checkSectionScoring(game, sc);
        System.out.println("");
        for (Player player : game.getPlayerList()) {
            player.newFavorTableIndex();
            System.out.println(player);
        }
    }
}
