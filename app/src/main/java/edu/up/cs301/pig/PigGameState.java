package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    private int playerID;
    private int player0Score;
    private int player1Score;
    private int runningTotal;
    private int dieValue;
    private String message;

    public PigGameState(){
        this.playerID = 0;
        this.player0Score = 0;
        this.player1Score = 0;
        this.runningTotal = 0;
        this.dieValue = -1;
        this.message = "";
    }

    public PigGameState(PigGameState state){
        this.playerID = state.playerID;
        this.player0Score = state.player0Score;
        this.player1Score = state.player1Score;
        this.runningTotal = state.runningTotal;
        this.dieValue = state.dieValue;
        this.message = state.message;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayer0Score() {
        return player0Score;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public int getDieValue() {
        return dieValue;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
