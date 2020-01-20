package caylus;

import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Player> playerList;

    public List<Player> getPlayerList() {
        return playerList;
    }

    // creates a list of playerList in random order
    public void createPlayerList(Scanner sc) {
        playerList = new ArrayList();
        int numberOfComPlayers = 0;
        // selecy number of user players
        int numberOfUserPlayers = SetUpGame.numberOfUserPlayers(sc);
        // if number of players is not max ask for com players
        if (numberOfUserPlayers != SetUpGame.MAX_PLAYERS) {
            numberOfComPlayers = SetUpGame.numberOfComPlayers(sc, numberOfUserPlayers);
        }
        // add user players to the list if there are any
        if (numberOfUserPlayers != 0) {
            SetUpGame.addUserPlayers(numberOfUserPlayers, playerList);
        }
        // add com players to the list if there are any
        if (numberOfComPlayers != 0) {
            SetUpGame.addComPlayers(numberOfComPlayers, playerList);
        }
        // randomize order
        SetUpGame.randomOrderList(playerList);
    }
}
