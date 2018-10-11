package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;
    private TextView    playerNameTextView  = null;
    private TextView    oppNameTextView     = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        //TODO You will implement this method to receive state objects from the game
        if (info instanceof PigGameState){
            PigGameState gameState = (PigGameState) info;
            if (this.playerNum == 0){
                this.playerScoreTextView.setText(String.valueOf(gameState.getPlayer0Score()));
                this.oppScoreTextView.setText(String.valueOf(gameState.getPlayer1Score()));
                this.turnTotalTextView.setText(String.valueOf(gameState.getRunningTotal()));
            }else{
                this.playerScoreTextView.setText(String.valueOf(gameState.getPlayer0Score()));
                this.oppScoreTextView.setText(String.valueOf(gameState.getPlayer1Score()));
                this.turnTotalTextView.setText(String.valueOf(gameState.getRunningTotal()));
            }

            if (this.playerNum == gameState.getPlayerID()) this.dieImageButton.setBackgroundColor(0b11111111111111110000000000000000);
            else this.dieImageButton.setBackgroundColor(0b11111111000000001111111100000000);

            this.messageTextView.setText(gameState.getMessage());

            switch(gameState.getDieValue()){
                case 1:
                    dieImageButton.setImageResource(R.drawable.face1);
                    break;
                case 2:
                    dieImageButton.setImageResource(R.drawable.face2);
                    break;
                case 3:
                    dieImageButton.setImageResource(R.drawable.face3);
                    break;
                case 4:
                    dieImageButton.setImageResource(R.drawable.face4);
                    break;
                case 5:
                    dieImageButton.setImageResource(R.drawable.face5);
                    break;
                case 6:
                    dieImageButton.setImageResource(R.drawable.face6);
                    break;
            }


        }else{
            flash(0b00000000111111110000000000000000, 500);
        }

    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        //TODO  You will implement this method to send appropriate action objects to the game
        if (button instanceof ImageButton) {
            ImageButton btn = (ImageButton) button;
            if (btn.getId() == R.id.dieButton) this.game.sendAction(new PigRollAction(this));
        }

        if (button instanceof Button){
            Button btn = (Button) button;
            if (btn.getId() == R.id.holdButton){
                this.game.sendAction(new PigHoldAction(this));
            }
        }

    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);
        this.playerNameTextView  = (TextView) activity.findViewById(R.id.yourScoreText);
        this.oppNameTextView     = (TextView) activity.findViewById(R.id.oppScoreText);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

    public String getName(){
        return this.name;
    }

    @Override
    protected void initAfterReady(){
        //todo set player and opposing player names in textviews
        if (this.playerNum == 0){
            this.playerNameTextView.setText(this.allPlayerNames[0]);
            this.oppNameTextView.setText(this.allPlayerNames[1]);
        } else {
            this.playerNameTextView.setText(this.allPlayerNames[1]);
            this.oppNameTextView.setText(this.allPlayerNames[0]);
        }
    }

}// class PigHumanPlayer
