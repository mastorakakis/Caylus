package caylus;

import caylussetup.SetUpGame;
import entities.players.Player;
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
        System.out.println("");
        Phase6.play(game, sc);
        System.out.println("");

//        Player player1 = game.getPlayerList().get(0);
//        Player player2 = game.getPlayerList().get(1);
//        Player player3 = game.getPlayerList().get(2);
//
//        player1.setPoints(5);
//        player2.setPoints(5);
//        player3.setPoints(5);
//
//        player1.setWorkers(5);
//        player2.setWorkers(5);
//        player3.setWorkers(5);
//
//        player2.getResources().setFood(3);
//        player2.getResources().setWood(2);
//        player2.getResources().setStone(2);
//        player2.getResources().setCloth(2);
//        player2.getResources().setGold(1);
//        player1.getResources().setFood(2);
//        player1.getResources().setWood(3);
//        player1.getResources().setCloth(3);
//
//        game.getRoad().get(0).getWorkers().add(player1);
//        game.getRoad().get(0).getWorkers().add(player2);
//        game.getRoad().get(0).getWorkers().add(player3);
//
//        game.getCastle().getDungeon().getBuildSpaces().add(player1);
//        game.getCastle().getDungeon().getBuildSpaces().add(player1);
//        game.getCastle().getDungeon().getBuildSpaces().add(player1);
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
