package caylus;

import caylussetup.SetUpGame;
import java.util.Scanner;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class CaylusMain {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("C A Y L U S");
//        int choice
//                = Functions.inputValidation(1, 2, "1)Start a new game\n2)Load game",
//                        Game.WARNING, sc);
        Game game = null;
//        if (choice == 1) {
        game = new Game();
        game.setPlayerList(SetUpGame.players(sc));
        game.setRoad(SetUpGame.road());
        game.setBuildingList(SetUpGame.buildingsList());
//        } else {
//            LoadGame.load(game);
//            for (Player player : game.getPlayerList()) {
//                System.out.println(player);
//            }
//        }
        Phase1.play(game, sc);
        System.out.println("");
        Phase2.play(game, sc);
        System.out.println("");
        Phase3.play(game, sc);
        System.out.println("");
        Phase4.play(game, sc);
        System.out.println("");
        Phase5.play(game, sc);
//        choice = Functions.inputValidation(1, 2, "Save game\n1)Yes\n2)No",
//                Game.WARNING, sc);
//        if (choice == 1) {
//            SaveFile.save(game);
//        }
        //
        //        for (Player player : game.getPlayerList()) {
        //            System.out.println(player);
        //        }
    }

}
