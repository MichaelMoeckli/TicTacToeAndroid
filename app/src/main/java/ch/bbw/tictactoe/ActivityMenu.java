package ch.bbw.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityMenu extends AppCompatActivity {
    private Button button_play;
    private Button button_playvsbot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button_play = (Button) findViewById(R.id.button_play);
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySpielSpieler();
            }
        });
        button_playvsbot = (Button) findViewById(R.id.button_playvsbot);
        button_playvsbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySpielBot();
            }
        });
    }

    public void openActivitySpielSpieler() {
        Intent intent = new Intent(this, ActivitySpielSpieler.class);
        startActivity(intent);
    }

    public void openActivitySpielBot() {
        Intent intent = new Intent(this, ActivitySpielBot.class);
        startActivity(intent);
    }
}
