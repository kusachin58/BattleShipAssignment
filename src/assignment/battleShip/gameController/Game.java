package assignment.battleShip.gameController;

import assignment.battleShip.service.GameService;
import assignment.battleShip.strategy.DefaultPlayerPickStrategy;
import assignment.battleShip.strategy.DefaultWinningStrategy;

import java.io.IOException;


public class Game {

    public static void main(String[] args) throws IOException {
        int numberOfParticipants = 2;
        GameService gameService = new GameService(new DefaultPlayerPickStrategy(), new DefaultWinningStrategy(), numberOfParticipants);
        gameService.setUpGameEnvironment();
        gameService.StartGame();
    }
}
