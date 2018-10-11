package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState gameState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        gameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        return gameState.getPlayerID() == playerIdx;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        GamePlayer player = action.getPlayer();
        String playerName = "";
        if (player instanceof PigHumanPlayer){
            PigHumanPlayer humanPlayer = (PigHumanPlayer) player;
            playerName = humanPlayer.getName();
        }
        else if (player instanceof  PigComputerPlayer){
            PigComputerPlayer compPlayer = (PigComputerPlayer) player;
            playerName = compPlayer.getName();
        }
        else if (player instanceof  PigSmartComputerPlayer){
            PigSmartComputerPlayer compPlayer = (PigSmartComputerPlayer) player;
            playerName = compPlayer.getName();
        }

        if (action instanceof PigHoldAction){
                //set the score of the player - only player 0 is used for 1 player
                if (gameState.getPlayerID() == 0) gameState.setPlayer0Score(gameState.getRunningTotal() + gameState.getPlayer0Score());
                else if (gameState.getPlayerID() == 1) gameState.setPlayer1Score(gameState.getRunningTotal() + gameState.getPlayer1Score());

                //if 2 players, switch player turn
                if (players.length == 2) gameState.setPlayerID(1-gameState.getPlayerID());


                gameState.setMessage(playerName + " just scored " + gameState.getRunningTotal() + " points");
                gameState.setRunningTotal(0);
                return true;
        } else if (action instanceof PigRollAction){
            Random rand = new Random();
            gameState.setDieValue(rand.nextInt(6) + 1);

            if (gameState.getDieValue() != 1){
                gameState.setRunningTotal(gameState.getRunningTotal() + gameState.getDieValue());
            }
            else{
                gameState.setMessage(playerName + " just rolled a 1 and lost " + gameState.getRunningTotal() + " points");
                gameState.setRunningTotal(0);
                if (players.length == 2) gameState.setPlayerID(1-gameState.getPlayerID());
            }
            return true;
        }

        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState copyGameState = new PigGameState(this.gameState);
        p.sendInfo(copyGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if (gameState.getPlayer0Score() >= 50) return "Player 0 won - score: " + gameState.getPlayer0Score();
        if (gameState.getPlayer1Score() >= 50) return "Player 1 won - score: " + gameState.getPlayer1Score();

        return null;
    }

}// class PigLocalGame
