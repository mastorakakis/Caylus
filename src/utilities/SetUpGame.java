package utilities;

import entities.ComPlayer;
import entities.Player;
import entities.UserPlayer;
import enums.Color;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SetUpGame {

    private static final int MIN_USER_PLAYERS = 0;
    private static final int MAX_PLAYERS = 5;

    // creates a list of players in random order
    public static List<Player> createPlayerList(Scanner sc) {
        List<Player> players = new ArrayList();
        int numberOfComPlayers = 0;
        int numberOfUserPlayers = numberOfUserPlayers(sc);
        if (numberOfUserPlayers != 5) {
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
        int userPlayers = inputValidation(MIN_USER_PLAYERS, MAX_PLAYERS,
                "Select number of user players", "Invalid option.", sc);
        return userPlayers;
    }

    // returns number of com players.
    private static int numberOfComPlayers(Scanner sc, int numberOfUserPlayers) {
        int max = MAX_PLAYERS - numberOfUserPlayers;
        int min = numberOfUserPlayers < 2 ? 2 - numberOfUserPlayers : 0;
        int players = inputValidation(min, max,
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
                Player redPlayer = new UserPlayer(Color.RED);
                players.add(redPlayer);
            case 1:
                Player greenPlayer = new UserPlayer(Color.GREEN);
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

    // validates user input
    public static int inputValidation(int min, int max, String message,
            String warning, Scanner sc) {
        String input = String.valueOf(min - 1);
        do {
            try {
                System.out.printf("%s (%d-%d): ", message, min, max);
                input = sc.nextLine();
                if (parseInt(input) < min || parseInt(input) > max) {
                    System.out.println(warning);
                }
            } catch (NumberFormatException e) {
                System.out.println(warning);
                input = String.valueOf(min - 1);
            }
        } while (parseInt(input) < min || parseInt(input) > max);
        return parseInt(input);
    }
    // transfer inputValidation elsewhere
}
