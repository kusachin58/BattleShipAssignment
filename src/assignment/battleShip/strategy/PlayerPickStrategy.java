package assignment.battleShip.strategy;

import assignment.battleShip.models.Player;

import java.util.ArrayList;

public interface PlayerPickStrategy {
    Player getFirstPlayer(ArrayList<Player> playerArrayList);

    Player getNextPlayer(Player currentPlayer, ArrayList<Player> playerArrayList);

    Player getTargetPlayer(Player currentPlayer, ArrayList<Player> playerArrayList);
}
