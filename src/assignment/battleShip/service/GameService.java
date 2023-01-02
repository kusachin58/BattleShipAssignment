package assignment.battleShip.service;

import assignment.battleShip.models.Board;
import assignment.battleShip.models.Coordinates;
import assignment.battleShip.models.Player;
import assignment.battleShip.models.warArtillery.ArtilleryFactory;
import assignment.battleShip.strategy.PlayerPickStrategy;
import assignment.battleShip.strategy.WinningStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameService {
    private  final ArrayList<Player> playerArrayList;

    private final WinningStrategy winningStrategy;

    private final PlayerPickStrategy playerPickStrategy;

    private final int numberOfParticipants;

    ArtilleryFactory artilleryFactory;


    public GameService(PlayerPickStrategy playerPickStrategy, WinningStrategy winningStrategy, int numberOfParticipants) {

        playerArrayList = new ArrayList<>();
        this.playerPickStrategy = playerPickStrategy;
        this.winningStrategy = winningStrategy;
        this.numberOfParticipants = numberOfParticipants;
        this.artilleryFactory = new ArtilleryFactory();
    }

    /**
     * Function for Deploying war Artillery on the board &
     * Load the missile targets for each player.
     */
    public void setUpGameEnvironment() throws IOException {


        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String dimension = input.readLine();
        input.readLine();

        String[] dimensionList  = dimension.split("\\s");

        int boardColumns = Integer.parseInt(dimensionList[0]);
        int boardRows = dimensionList[1].charAt(0) - 'A' + 1;




        for (int playerId = 0; playerId < numberOfParticipants; playerId++) {
            Board board = new Board(boardRows, boardColumns);
            Player player = new Player(playerId, board);
            playerArrayList.add(player);
        }



        int artilleryCount = Integer.parseInt(input.readLine());
        input.readLine();



        String warArtilleryInfo;
        String warArtilleryType;
        int warArtilleryBreadth;
        int warArtilleryHeight;


       // Logic for Deploying War Inventory.
        while (artilleryCount-- > 0) {
            int currIndex = 0;
            int playerID = 0;
            int artilleryListLength;
            warArtilleryInfo = input.readLine();
            input.readLine();
            String[] inventoryInfoList = warArtilleryInfo.split("\\s");
            artilleryListLength = inventoryInfoList.length;

            warArtilleryType = inventoryInfoList[currIndex++];
            warArtilleryBreadth = Integer.parseInt(inventoryInfoList[currIndex++]);
            warArtilleryHeight = Integer.parseInt(inventoryInfoList[currIndex++]);



            while (currIndex < artilleryListLength && playerID < numberOfParticipants) {
                String warInventoryPosition = inventoryInfoList[currIndex];
                playerArrayList.get(playerID).getBoard().addWarArtilleryToBoard(warArtilleryBreadth, warArtilleryHeight, warInventoryPosition, warArtilleryType, artilleryFactory);
                playerID++;
                currIndex++;
            }
        }



        int currentPlayerID = 0;



        while (currentPlayerID < numberOfParticipants) {
            String missileTargetLocationInput = input.readLine();
            input.readLine();
            String[] missileTargetLocationList = missileTargetLocationInput.split("\\s");

            playerArrayList.get(currentPlayerID).addCoordinatesForMissileFire(missileTargetLocationList);
            currentPlayerID++;
        }


    }


    /**
     * Logic for Game Play once setup completed.
     */
    public void StartGame() {


            Player currentPlayer = playerPickStrategy.getFirstPlayer(playerArrayList);
            boolean winnerFound  = false;

            while (checkIfValidMoveExist()) {

                if (currentPlayer.isMissileTargetQueueEmpty()) {
                    System.out.println("Player-" + currentPlayer.getPlayerIdForLog() + " has no more missiles left to launch");
                    currentPlayer = playerPickStrategy.getNextPlayer(currentPlayer, playerArrayList);
                    continue;
                }

                while (!currentPlayer.isMissileTargetQueueEmpty()) {



                    String location = currentPlayer.getMissileTargetLocation();

                    Coordinates coordinates = new Coordinates(location);
                    Player targetedPlayer = playerPickStrategy.getTargetPlayer(currentPlayer, playerArrayList);
                    boolean targetHitSuccessful =  targetedPlayer.missileFiredOnBoard(coordinates);

                    if (targetHitSuccessful) {
                        System.out.println("Player-" + currentPlayer.getPlayerIdForLog() + " fires a missile with target " + location + " which got hit");
                        Player winnerPlayer = winningStrategy.getGameWinner(playerArrayList);

                        if (winnerPlayer != null) {
                            System.out.println("Player-" + winnerPlayer.getPlayerIdForLog() + " won Battle");
                            winnerFound = true;
                            break;
                        }
                    } else {
                        System.out.println("Player-" + currentPlayer.getPlayerIdForLog() + " fires a missile with target " + location + " which got miss");
                        break;
                    }

                 }


                 if (winnerFound)
                     break;
                 currentPlayer = playerPickStrategy.getNextPlayer(currentPlayer, playerArrayList);
            }

            if (!winnerFound) {
                System.out.println("Match Draw");
            }


       }


       boolean checkIfValidMoveExist() {
           for (Player player: playerArrayList) {
               if (!player.isMissileTargetQueueEmpty()) {
                   return true;
               }
           }

           return false;
       }

}
