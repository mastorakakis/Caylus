package caylus;

import caylussetup.SetUpGame;
import entities.buildings.Bridge;
import entities.buildings.Building;
import entities.buildings.Inn;
import entities.players.Player;
import java.util.Scanner;
import utilities.Functions;
import utilities.LoadGame;
import utilities.SaveFile;

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
        System.out.println("");

        Phase1.play(game, sc);

//        Phase2.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
//        System.out.println("");
//        choice = Functions.inputValidation(1, 2, "Save game\n1)Yes\n2)No",
//                Game.WARNING, sc);
//        if (choice == 1) {
//            SaveFile.save(game);
//        }
//        Phase2.play(game, sc);
        //        for (Player player : game.getPlayerList()) {
        //            System.out.println(player);
        //        }
        //        Phase4.play(game, sc);
        //        for (Player player : game.getPlayerList()) {
        //            System.out.println(player);
        //        }
        //        System.out.println("");
        //        Phase5.play(game, sc);
        //        for (Player player : game.getPlayerList()) {
        //            System.out.println(player);
        //        }
        Player player1 = game.getPlayerList().get(0);
        Player player2 = game.getPlayerList().get(1);
        Player player3 = game.getPlayerList().get(2);

        player1.setWorkers(4);
        player2.setWorkers(4);
        player3.setWorkers(4);

        player1.setMoney(1);
        player2.setMoney(1);
        player3.setMoney(1);

        game.getRoad().get(4).getWorkers().add(player1);
        game.getRoad().get(5).getWorkers().add(player2);

        Bridge.getPositionList().add(player1);
        Bridge.getPositionList().add(player2);
        Bridge.getPositionList().add(player3);

        Phase4.play(game, sc);
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
        System.out.println("");
    }

}
