package assignment.battleShip.models;

import assignment.battleShip.exception.CoordinatesOutOfBoundException;
import assignment.battleShip.models.warArtillery.Artillery;
import assignment.battleShip.models.warArtillery.ArtilleryFactory;
import assignment.battleShip.models.warArtillery.ArtilleryType;

import java.util.HashMap;

public class Board {
     private final int  boardRows;
     private final int boardCols;
     private final HashMap <Coordinates, Artillery> coordinatesToArtilleryMap;



     public  Board (int boardRows, int boardCols) {
         this.boardRows = boardRows;
         this.boardCols = boardCols;
         coordinatesToArtilleryMap = new HashMap<>();
     }


     public int coordinatesToArtilleryMapSize() {
         return this.coordinatesToArtilleryMap.size();
     }

     public boolean coordinatesToArtilleryMapContainsKey(Coordinates coordinates) {
         return coordinatesToArtilleryMap.containsKey(coordinates);
     }
    public void validateCoordinates(Coordinates coordinates) throws CoordinatesOutOfBoundException {
         if (coordinates.getCol() < 0 || coordinates.getRow() >= boardRows || coordinates.getRow() < 0 || coordinates.getCol() >= boardCols) {
             throw new CoordinatesOutOfBoundException("Skipping War Artillery Deployments: Coordinates out of Bound");
         }
    }
     public void addWarArtilleryToBoard(int inventoryBreadth, int inventoryLength, String warInventoryLocation, String inventoryPower, ArtilleryFactory artilleryFactory) {
         int row = warInventoryLocation.charAt(0) - 'A';
         int col = Integer.parseInt(warInventoryLocation.substring(1)) -1;

         for (int currRow = row; currRow < row + inventoryLength; currRow++) {
             try {
                for (int currCol = col; currCol < col+ inventoryBreadth; currCol++) {
                 Coordinates currentCoordinates =  new Coordinates(currRow, currCol);
                 validateCoordinates(currentCoordinates);
                 Artillery artillery = artilleryFactory.createWarInventory(inventoryPower, ArtilleryType.SHIP);
                 coordinatesToArtilleryMap.put(currentCoordinates, artillery);
                }
             } catch (CoordinatesOutOfBoundException coordinatesOutOfBoundException) {
                     System.out.println(coordinatesOutOfBoundException.getMessage());
             }
         }
     }

     public void hitMissileOnBoardSuccessful(Coordinates coordinates) {
         Artillery artillery = coordinatesToArtilleryMap.get(coordinates);
         coordinatesToArtilleryMap.remove(coordinates);
         if (artillery.getPower()-1 > 0) {
             artillery.decreasePowerOnHit();
             coordinatesToArtilleryMap.put(coordinates, artillery);
         }
     }


}
