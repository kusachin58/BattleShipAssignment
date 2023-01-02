package assignment.battleShip.strategy;

import assignment.battleShip.models.Player;

import java.util.ArrayList;

public class DefaultWinningStrategy implements  WinningStrategy{
    public Player getGameWinner(ArrayList<Player> playerArrayList) {
        Player winnerPlayer = null;
        int activePlayer = 0;

        for (Player player: playerArrayList) {
            if (player.hasShipLeft()) {
                activePlayer++;
                winnerPlayer = player;
            }
        }

        if (activePlayer == 1) {
            return winnerPlayer;
        }

        return null;
    }
}
