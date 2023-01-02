package assignment.battleShip.strategy;

import assignment.battleShip.models.Player;

import java.util.ArrayList;

public class DefaultPlayerPickStrategy implements  PlayerPickStrategy {

    public Player getFirstPlayer(ArrayList<Player> playerArrayList) {
        return playerArrayList.get(0);
    }

    @Override
    public Player getNextPlayer(Player currentPlayer, ArrayList<Player> playerArrayList) {
        int size = playerArrayList.size();
        int playerId = (currentPlayer.getPlayerId()+1)%size;
        return playerArrayList.get(playerId);
    }

    @Override
    public Player getTargetPlayer(Player currentPlayer, ArrayList<Player> playerArrayList) {
        for (Player player: playerArrayList) {
            if (player.getPlayerId() != currentPlayer.getPlayerId() && player.hasShipLeft()) {
                return player;
            }
        }

        return null;
    }
}
