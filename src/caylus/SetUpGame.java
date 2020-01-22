package caylus;

import static caylus.CreateBuildings.*;
import entities.Block;
import entities.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetUpGame {

    public static final int MIN_PLAYERS = 2;
    public static final int MIN_USER_PLAYERS = 0;
    public static final int MAX_PLAYERS = 5;

    // creates a list of playerList in random order
    public static List<Player> players(Scanner sc) {
        List<Player> playerList = new ArrayList();
        int numberOfComPlayers = 0;
        // selecy number of user players
        int numberOfUserPlayers = 3; // CreatePlayers.numberOfUserPlayers(sc);
        if (numberOfUserPlayers != SetUpGame.MAX_PLAYERS) {// if number of players is not max ask for com players
            numberOfComPlayers = 0;// CreatePlayers.numberOfComPlayers(sc, numberOfUserPlayers);
        }
        if (numberOfUserPlayers != 0) { // add user players to the list if there are any
            CreatePlayers.addUserPlayers(numberOfUserPlayers, playerList);
        }
        if (numberOfComPlayers != 0) { // add com players to the list if there are any
            CreatePlayers.addComPlayers(numberOfComPlayers, playerList);
        }
        CreatePlayers.randomOrderList(playerList); // randomize order
        return playerList;
    }

    public static List<Block> road() {
        List<Block> road = new ArrayList();
        road.add(new Block(gate));
        road.add(new Block(tradingPost));
        road.add(new Block(merchantsGuild));
        road.add(new Block(joustField));
        road.add(new Block(stables));
        road.add(new Block(inn));
        return road;
    }
}
// delete number of user players 5 and com players 3
