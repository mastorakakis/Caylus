package caylus;

import entities.ComPlayer;
import entities.Player;
import entities.UserPlayer;
import enums.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import utilities.Functions;

public class SetUpGame {

    private static final int MIN_PLAYERS = 2;
    private static final int MIN_USER_PLAYERS = 0;
    private static final int MAX_PLAYERS = 5;

    // creates a list of players in random order
    public static List<Player> createPlayerList(Scanner sc) {
        List<Player> players = new ArrayList();
        int numberOfComPlayers = 0;
        int numberOfUserPlayers = numberOfUserPlayers(sc);
        if (numberOfUserPlayers != MAX_PLAYERS) {
            numberOfComPlayers = numberOfComPlayers(sc, numberOfUserPlayers);
        }
        addUserPlayers(numberOfUserPlayers, players);
        if (numberOfComPlayers != 0) {
            addComPlayers(numberOfComPlayers, players);
        }
        randomOrderList(players);
        return players;
    }

    // shuffles the list and initializes the players amount of money
    private static List<Player> randomOrderList(List<Player> players) {
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
        return players;
    }

    // returns number of user players
    private static int numberOfUserPlayers(Scanner sc) {
        int userPlayers = Functions.inputValidation(MIN_USER_PLAYERS, MAX_PLAYERS,
                "Select number of user players", "Invalid option.", sc);
        return userPlayers;
    }

    // returns number of com players.
    private static int numberOfComPlayers(Scanner sc, int numberOfUserPlayers) {
        int max = MAX_PLAYERS - numberOfUserPlayers;
        int min = numberOfUserPlayers < MIN_PLAYERS ? MIN_PLAYERS - numberOfUserPlayers : 0;
        int players = Functions.inputValidation(min, max,
                "Select number of COM players", "Invalid option.", sc);
        return players;
    }

    // adds user players to the list
    private static void addUserPlayers(int numberOfUserPlayers, List<Player> players) {
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
    private static List<Player> addComPlayers(int numberOfComPlayers, List<Player> players) {
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
