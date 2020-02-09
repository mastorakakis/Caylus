package caylus;

import caylussetup.CreatePlayers;
import caylussetup.SetUpGame;
import entities.FavorTable;
import entities.buildings.Castle;
import entities.players.Player;
import entities.players.UserPlayer;
import enums.Color;
import enums.Status;
import interfaces.Phase;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;
import utilities.LoadGame;
import utilities.SaveGame;
import utilities.Synopsis;

/**
 * @author Παναγιώτης Μαστορακάκης
 */
public class CaylusMain {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("C A Y L U S");
        Game game = new Game();
        List<Phase> phases = SetUpGame.createPhases();
        if (Functions.inputValidation(1, 2, "\n1)Start new Game\t2)Load Game",
                new UserPlayer(Color.BLUE), sc) == 2) {
            game = LoadGame.load();

            Synopsis.print(game);
        } else {
            FavorTable favorTable = new FavorTable();
            favorTable.createPlayerFavorTable();
            game.setPlayerList(CreatePlayers.getPlayers(sc));
            game.setFavorTable(favorTable);
            game.setRoad(SetUpGame.getRoad());
            game.setBuildingList(SetUpGame.getBuildingList());
            game.setCastle((Castle) game.getRoad().get(0).getBuilding());
        }
////////////////////////////////////////////////////////////////////////////////
        Status gameStatus = Status.CONTINUE;
        do {
            for (Phase phase : phases) {
                if (gameStatus == Status.CONTINUE) {
                    phase.play(game, sc);
                }
                if (game.getCastle().getTowers().isScored()) {
                    gameStatus = Status.FINISH;
                }
            }
            if (Functions.inputValidation(1, 2, "\nSave Game\n1)Yes\t2)No",
                    new UserPlayer(Color.BLUE), sc) == 1) {
                SaveGame.save(game);
            }
        } while (gameStatus == Status.CONTINUE);
        List<Player> winners = Phase7.scoreGame(game);

        System.out.println(
                "\nWinners:");
        for (Player winner : winners) {
            System.out.println(winner);
        }
    } // end of main
} // end of Class
