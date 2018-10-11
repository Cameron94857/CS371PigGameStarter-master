package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {
    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        this.sleep(2000);
        PigGameState game = (PigGameState) info;
        if (this.playerNum == game.getPlayerID()){
            Random rand = new Random();
            GameAction gameActn;

            if (this.playerNum == 0) {
                if (game.getRunningTotal() >= 4 || game.getRunningTotal() + game.getPlayer0Score() >= 50) {
                    gameActn = new PigHoldAction(this);
                } else {
                    gameActn = new PigRollAction(this);
                }
            }else{
                if (game.getRunningTotal() >= 4 || game.getRunningTotal() + game.getPlayer1Score() >= 50) {
                    gameActn = new PigHoldAction(this);
                } else {
                    gameActn = new PigRollAction(this);
                }
            }
            this.game.sendAction(gameActn);
        }
    }//receiveInfo

    public String getName(){
        return this.name;
    }
}
