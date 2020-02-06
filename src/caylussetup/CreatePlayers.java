package caylussetup;

import entities.players.ComPlayer;
import entities.players.Player;
import entities.players.UserPlayer;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import enums.Color;
import java.util.ArrayList;
import utilities.Functions;

public class CreatePlayers {

    public static final int MIN_PLAYERS = 2;
    public static final int MIN_USER_PLAYERS = 0;
    public static final int MAX_PLAYERS = 5;

    // creates a list of playerList in random order
    public List<Player> getPlayers(Scanner sc) {
        List<Player> playerList = new ArrayList();
        int numberOfComPlayers = 0;
        // select number of user getPlayers
        int numberOfUserPlayers = 2; //numberOfUserPlayers(sc);
        // if number of getPlayers is not max ask for com getPlayers
        if (numberOfUserPlayers != MAX_PLAYERS) {
            numberOfComPlayers = 0;//numberOfComPlayers(sc, numberOfUserPlayers);
        }// add user getPlayers to the list if there are any
        if (numberOfUserPlayers != 0) {
            addUserPlayers(numberOfUserPlayers, playerList);
        }// add com getPlayers to the list if there are any
        if (numberOfComPlayers != 0) {
            addComPlayers(numberOfComPlayers, playerList);
        }
        randomOrderList(playerList); // randomize order
        return playerList;
    }

    // returns number of user getPlayers
    private int numberOfUserPlayers(Scanner sc) {
        int userPlayers = Functions.inputValidation(MIN_USER_PLAYERS, MAX_PLAYERS,
                "Select number of user players", null, sc);
        return userPlayers;
    }

    // returns number of com getPlayers.
    private int numberOfComPlayers(Scanner sc, int numberOfUserPlayers) {
        int max = MAX_PLAYERS - numberOfUserPlayers;
        int min = numberOfUserPlayers < MIN_PLAYERS ? MIN_PLAYERS
                - numberOfUserPlayers : 0;
        int players = Functions.inputValidation(min, max,
                "Select number of COM players", null, sc);
        return players;
    }

    // adds user getPlayers to the list
    private void addUserPlayers(int numberOfUserPlayers, List<Player> players) {
        switch (numberOfUserPlayers) {
            case 5:
                Player OrangePlayer = new UserPlayer(Color.GREEN);
                players.add(OrangePlayer);
            case 4:
                Player bluePlayer = new UserPlayer(Color.BLUE);
                players.add(bluePlayer);
            case 3:
                Player blackPlayer = new UserPlayer(Color.BLACK);
                players.add(blackPlayer);
            case 2:
                Player redPlayer = new UserPlayer(Color.ORANGE);
                players.add(redPlayer);
            case 1:
                Player greenPlayer = new UserPlayer(Color.RED);
                players.add(greenPlayer);
        }
    }

    // shuffles the player list and initializes the getPlayers' amount of money
    private void randomOrderList(List<Player> players) {
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

    // adds com getPlayers to the list
    private void addComPlayers(int numberOfComPlayers, List<Player> players) {
        switch (numberOfComPlayers) {
            case 5:
                Player OrangePlayer = new ComPlayer(Color.RED);
                players.add(OrangePlayer);
            case 4:
                Player bluePlayer = new ComPlayer(Color.ORANGE);
                players.add(bluePlayer);
            case 3:
                Player blackPlayer = new ComPlayer(Color.BLACK);
                players.add(blackPlayer);
            case 2:
                Player redPlayer = new ComPlayer(Color.BLUE);
                players.add(redPlayer);
            case 1:
                Player greenPlayer = new ComPlayer(Color.GREEN);
                players.add(greenPlayer);
        }
    }
}
