/*
Ein TicTacToe Programm in Java
Mit diesem Programm kann man TicTac Toe gegeneinander Spielen
@author Michael Moeckli
@author Nicolas Rickenbacher
@version 0.4
 */

package ch.bbw.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySpielSpieler extends AppCompatActivity implements View.OnClickListener {
    //2d Array
    private  Button[][] buttons = new Button[3][3];
    //Buttons
    private Button button_play;
    private Button button_backtomenu;

   //Sobald das Spiel beginnt, kann der player1 seinen Zug machen
    private boolean player1Zug = true;

    //Wird unsere Runden zählen, wenn wir 9 Ruden gespielt haben wissen wir, dass es Unentschieden ist, da wir nur 9 Felder haben
    //Wird unsere Runden zählen, wenn wir 9 Ruden gespielt haben wissen wir, dass es Unentschieden ist, da wir nur 9 Felder haben
    private int rundenZähler;
    //Punkte für SPieler asl ints
    private int player1Punkte;
    private int player2Punkte;
    //Textfelder für Punkte ausgabe der SPieler als TextView
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    /*
        Methode wird ausgeführt wenn Activity startet
        @param savedInstanceState  Status der App wird in einem Bundle gespiechert
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielspieler);

        button_backtomenu = (Button) findViewById(R.id.button_backtomenu);
        button_backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                openActivityMenu();
            }
        });

        textViewPlayer1 = findViewById(R.id.textview_p1);
        textViewPlayer2 = findViewById(R.id.textview_p2);
        //Diese Schleife wird durch alle Reihen und Spalten von unserem 2d Array gehen
        //Es wird durch alle Buttons die wir erstellt haben gehen
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPunkte();
            }
        });

    }
    /*
    Methode wird gefrufen falls einen Button gepresst wird
    Diese Methode schaut wer am zug ist und was in die Buttons kommt
    ausserdem werden die Methoden playerGewinnt oder draw ausgeführt falls so etwas passiert
    und er Spiler Zug wird gewechstelt
    @param v Wenn dieser View ausgelöst wird wird methode ausgeführt
    */
    @Override
    public void onClick(View v) {
        //Konrolliert ob Button einen leeren String hat bzw ein x oder o hat
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1Zug){
            ((Button)v).setText("X");
        }else{
            ((Button)v).setText("O");
        }

        rundenZähler++;

        //Gibt Methode falls einen SPieler gewinnt oder es ein Uentsschieden gibt
        //Falls noch nichts passiert wird ein der SPieler gewechselt
        if (checkForWin()) {
            if (player1Zug) {
                player1Gewinnt();
            } else {
                player2Gewinnt();
            }
        }else if (rundenZähler==9){
            draw();
        }else{
            player1Zug = !player1Zug;
        }
    }
    /*
    @return true falls jemand gewonnen hat anstonsten gibt er false zurück
    man weiss jedoch noch nicht wer gewonnen hat.
    */
    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++){
            for(int a = 0; a < 3; a++){
                field[i][a] = buttons[i][a].getText().toString();
            }
        }
        //3 Felder nebeneinender werden verglichen
        //Schleife überprüft Zeilen
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        //Schleife überprüft Spalten
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        //Schleifen überprüfen Diagonale
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
    /*
    Die Folgenden Methoden änder die Punkte etc. falls jemand gewonnen hat
     */
    private void player1Gewinnt(){
        player1Punkte++;
        Toast.makeText(this, "player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePunkteText();
        resetSpiel();
    }
    private void player2Gewinnt(){
        player2Punkte++;
        Toast.makeText(this, "player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePunkteText();
        resetSpiel();
    }
    /*
    Draw löst resestSPiel aus
     */
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetSpiel();
    }
    //Textview werden angepasst
    private void updatePunkteText(){
        textViewPlayer1.setText("Player 1: " + player1Punkte);
        textViewPlayer2.setText("Player 2: " +player2Punkte);
    }
    //Felder werden zurückgesetzt und der Zähler auch und der SPiler 1 hat wieder den Zug
    private void  resetSpiel(){
        //loopt durch alle Buttons und resetet denn  Text falls es einen zu einem leeren String
        for(int i = 0; i<3; i++){
            for (int a = 0; a < 3; a++){
                buttons[i][a].setText("");
            }
        }
        rundenZähler = 0;
        player1Zug = true;
    }
    // Punkte werden zurückgesetzt
    public void resetPunkte(){
        player1Punkte = 0;
        player2Punkte = 0;
        updatePunkteText();
        resetSpiel();
    }

    //Sobald man Gerät rotiert, wird diese Methode aufgerufen und diese Werte werden in dem outState Bundel gespeichert
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("rundenZähler", rundenZähler);
        outState.putInt("player1Punkte", player1Punkte);
        outState.putInt("player2Punkte", player2Punkte);
        outState.putBoolean("player1Zug",player1Zug);
    }
    //Werte zurück vom outState bringen
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        rundenZähler = savedInstanceState.getInt("rundenZähler");
        player1Punkte = savedInstanceState.getInt("player1Punkte");
        player2Punkte = savedInstanceState.getInt("player2Punkte");
        player1Zug = savedInstanceState.getBoolean("player1Zug");
    }
    //öffnet activity Menu
    public void openActivityMenu() {
        Intent intent = new Intent(this, ActivityMenu.class);
        startActivity(intent);
    }

}
