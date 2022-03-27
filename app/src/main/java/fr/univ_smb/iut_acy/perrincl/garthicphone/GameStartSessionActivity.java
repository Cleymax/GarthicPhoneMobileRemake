package fr.univ_smb.iut_acy.perrincl.garthicphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.material.textfield.TextInputLayout;

public class GameStartSessionActivity extends AppCompatActivity {

    private String game_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start_session);
        game_id = getIntent().getStringExtra("game_id");



        ((TextInputLayout) findViewById(R.id.gamesessionstart_expression_player_name)).setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                startGame();
            }
            return false;
        });

        findViewById(R.id.gamesessionstart_button_start).setOnClickListener(v -> startGame());
    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("game_id", game_id);
        startActivity(intent);
    }
}