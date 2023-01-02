package assignment.battleShip.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Player {
    public int getPlayerId() {
        return playerId;
    }

    public int getPlayerIdForLog() {
        return playerId+1;
    }

    private final int playerId;

    public Board getBoard() {
        return board;
    }

    private final Board board;


    private final Queue<String>  missileTargetLocations;

    public Player(int playerId, Board board) {
        this.playerId = playerId;
        this.board = board;
        missileTargetLocations = new LinkedList<>();
    }

    public void addCoordinatesForMissileFire(String[] missileTargetLocationList) {
        Collections.addAll(missileTargetLocations, missileTargetLocationList);
    }

    public String getMissileTargetLocation() {
        String location = missileTargetLocations.peek();
        missileTargetLocations.poll();
        return location;

    }

    public boolean isMissileTargetQueueEmpty() {
        return missileTargetLocations.isEmpty();
    }

    public boolean hasShipLeft() {
        return board.coordinatesToArtilleryMapSize() > 0;
    }

    public boolean missileFiredOnBoard(Coordinates coordinates) {

        if (board.coordinatesToArtilleryMapContainsKey(coordinates)) {
            board.hitMissileOnBoardSuccessful(coordinates);
            return true;
        }

        return false;
    }
}
