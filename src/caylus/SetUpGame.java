package caylus;

import entities.players.ComPlayer;
import entities.players.Player;
import entities.players.UserPlayer;
import otherClasses.Color;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class SetUpGame {

    public static final int MIN_PLAYERS = 2;
    public static final int MIN_USER_PLAYERS = 0;
    public static final int MAX_PLAYERS = 5;

    // shuffles the player list and initializes the players' amount of money
    protected static void randomOrderList(List<Player> players) {
        Collections.shuffle(players);
        for (int i = 0; i < players.size(); i++) {
            switch (i) {
                case 0:
                    players.get(i).setMoney(5);
                    break;
                case 1:
                case 2:
                    players.get(i).setMoney(6);
                    break;
                default:
                    players.get(i).setMoney(7);
                    break;
            }
        }
    }

    // returns number of user players
    protected static int numberOfUserPlayers(Scanner sc) {
        int userPlayers = Functions.inputValidation(MIN_USER_PLAYERS, MAX_PLAYERS,
                "Select number of user players", "Invalid option.", sc);
        return userPlayers;
    }

    // returns number of com players.
    protected static int numberOfComPlayers(Scanner sc, int numberOfUserPlayers) {
        int max = MAX_PLAYERS - numberOfUserPlayers;
        int min = numberOfUserPlayers < MIN_PLAYERS ? MIN_PLAYERS - numberOfUserPlayers : 0;
        int players = Functions.inputValidation(min, max,
                "Select number of COM players", "Invalid option.", sc);
        return players;
    }

    // adds user players to the list
    protected static void addUserPlayers(int numberOfUserPlayers, List<Player> players) {
        switch (numberOfUserPlayers) {
            case 5:
                Player OrangePlayer = new UserPlayer(Color.ORANGE);
                players.add(OrangePlayer);
            case 4:
                Player bluePlayer = new UserPlayer(Color.BLUE);
                players.add(bluePlayer);
            case 3:
                Player blackPlayer = new UserPlayer(Color.BLACK);
                players.add(blackPlayer);
            case 2:
                Player redPlayer = new UserPlayer(Color.GREEN);
                players.add(redPlayer);
            case 1:
                Player greenPlayer = new UserPlayer(Color.RED);
                players.add(greenPlayer);
        }
    }

    // adds com players to the list
    protected static List<Player> addComPlayers(int numberOfComPlayers, List<Player> players) {
        switch (numberOfComPlayers) {
            case 5:
                Player OrangePlayer = new ComPlayer(Color.RED);
                players.add(OrangePlayer);
            case 4:
                Player bluePlayer = new ComPlayer(Color.GREEN);
                players.add(bluePlayer);
            case 3:
                Player blackPlayer = new ComPlayer(Color.BLACK);
                players.add(blackPlayer);
            case 2:
                Player redPlayer = new ComPlayer(Color.BLUE);
                players.add(redPlayer);
            case 1:
                Player greenPlayer = new ComPlayer(Color.ORANGE);
                players.add(greenPlayer);
        }
        return players;
    }

}
