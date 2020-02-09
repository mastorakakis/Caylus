package caylus;

import setup.SetUpGame;
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
        Game game;
        List<Phase> phases = SetUpGame.createPhases();
        if (Functions.inputValidation(1, 2, "\n1)Start new Game\t2)Load Game",
                new UserPlayer(Color.BLUE), sc) == 1) {
            game = SetUpGame.newGame(); // new game
        } else {
            game = LoadGame.load(); // load game
            Synopsis.print(game);
        }
        Status gameStatus = Status.CONTINUE;
        do { // play
            for (Phase phase : phases) {
                if (gameStatus == Status.CONTINUE) {
                    phase.play(game, sc);
                }
                if (game.getCastle().getTowers().isScored()) {
                    gameStatus = Status.FINISH;
                }
            } // save game
            if (Functions.inputValidation(1, 2, "\nSave Game\n1)Yes\t2)No",
                    new UserPlayer(Color.BLUE), sc) == 1) {
                SaveGame.save(game);
            }
        } while (gameStatus == Status.CONTINUE);
        GameEnd.score(game);
    } // end of main
} // end of Class
