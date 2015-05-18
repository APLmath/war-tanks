package me.andrewlee.wartanks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends ActionBarActivity implements GameListener {
    private TextView player0TurnPoints;
    private TextView player1TurnPoints;

    // Game variables
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player0TurnPoints = (TextView)findViewById(R.id.Player0TurnPoints);
        player1TurnPoints = (TextView)findViewById(R.id.Player1TurnPoints);

        restartGame();
    }

    private void restartGame() {
        game = new Game(this);
        refresh();
    }

    public void refresh() {
        setTurnPoints(0, game.getTurnPointsLeftForPlayer(0));
        setTurnPoints(1, game.getTurnPointsLeftForPlayer(1));
    }

    private void setTurnPoints(int playerIndex, int turnPoints) {
        (playerIndex == 0 ? player0TurnPoints : player1TurnPoints).setText("" + turnPoints);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_restart) {
            Log.d("a", "Restart clicked");
            new AlertDialog.Builder(this)
                    .setTitle(R.string.dialog_restart_title)
                    .setMessage(R.string.dialog_restart_message)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
