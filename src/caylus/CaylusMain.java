package caylus;

import caylussetup.SetUpGame;
import entities.players.Player;
import java.util.Scanner;
import utilities.Functions;
import utilities.LoadGame;
import utilities.SaveGame;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class CaylusMain {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("C A Y L U S");
        int choice
                = Functions.inputValidation(1, 2, "1)Start a new game\n2)Load game",
                        Game.WARNING, sc);
        Game game = null;
        if (choice == 1) {
            game = new Game();
            game.setPlayerList(SetUpGame.players(sc));
            game.setRoad(SetUpGame.road());
            game.setBuildingList(SetUpGame.buildingsList());
        } else {
            LoadGame.openFile();
            game = LoadGame.readRecords();
            LoadGame.close();
        }
        for (Player player : game.getPlayerList()) {
            System.out.println(player);
        }
        System.out.println("");

//        Phase2.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
//        Phase3.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
//        System.out.println("");
//        Phase4.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
//        System.out.println("");
//        Phase5.play(game, sc);
//        for (Player player : game.getPlayerList()) {
//            System.out.println(player);
//        }
    }

}
