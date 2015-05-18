package me.andrewlee.wartanks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

    private Random die;

    // Game variables
    private int currentPlayerIndex;
    private int movePointsRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        die = new Random();

        restartGame();
    }

    private void restartGame() {
        newTurn(0);
    }

    private void newTurn(int newPlayerIndex) {
        currentPlayerIndex = newPlayerIndex;
        movePointsRemaining = die.nextInt(6);
        Log.d("asdf", "number is " + movePointsRemaining);
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
