package caylus;

import caylussetup.CreatePlayers;
import caylussetup.SetUpGame;
import entities.FavorTable;
import entities.buildings.Castle;
import entities.players.Player;
import entities.players.UserPlayer;
import enums.Color;
import enums.Status;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;
import utilities.LoadGame;
import utilities.SaveGame;
import utilities.Synopsis;

public class TestMain { // TODO delete class

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("C A Y L U S");
        Game game = new Game();
        if (Functions.inputValidation(1, 2, "\n1)Start new Game\t2)Load Game",
                new UserPlayer(Color.BLUE), sc) == 2) {
            game = LoadGame.load();
            Synopsis.print(game);
        } else {
            CreatePlayers cp = new CreatePlayers();
            SetUpGame sug = new SetUpGame();
            FavorTable favorTable = new FavorTable();
            favorTable.createPlayerFavorTable();
            game.setPlayerList(cp.getPlayers(sc));
            game.setFavorTable(favorTable);
            game.setRoad(sug.getRoad());
            game.setBuildingList(sug.getBuildingList());
            game.setCastle((Castle) game.getRoad().get(0).getBuilding());

            Player player = null;
            for (int i = 0; i < game.getPlayerList().size(); i++) {
                Player get = game.getPlayerList().get(i);
                if (get.getColor() == Color.RED) {
                    player = get;
                    break;
                }
            }
            player.setMoney(1000);
            player.getResources().setCloth(1000);
            player.getResources().setFood(1000);
            player.getResources().setGold(1000);
            player.getResources().setStone(1000);
            player.getResources().setWood(1000);
        }
////////////////////////////////////////////////////////////////////////////////
        Status gameStatus = Status.CONTINUE;
        do {
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
            if (game.getCastle().getTowers().isScored()) {
                gameStatus = Status.FINISH;
            }
            if (gameStatus == Status.CONTINUE) {
                Phase7.play(game, sc);
                System.out.println("");
            }
            if (game.getCastle().getTowers().isScored()) {
                gameStatus = Status.FINISH;
            }
            if (Functions.inputValidation(1, 2, "\nSave Game\n1)Yes\t2)No",
                    new UserPlayer(Color.BLUE), sc) == 1) {
                SaveGame.save(game);
            }
        } while (gameStatus == Status.CONTINUE);
        List<Player> winners = Phase7.scoreGame(game);
        System.out.println("\nWinners:");
        for (Player winner : winners) {
            System.out.println(winner);
        }
    } // end of main
}
