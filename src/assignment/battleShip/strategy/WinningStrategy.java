package assignment.battleShip.strategy;

import assignment.battleShip.models.Player;

import java.util.ArrayList;

public interface WinningStrategy {

   Player getGameWinner(ArrayList<Player> playerArrayList);
}
