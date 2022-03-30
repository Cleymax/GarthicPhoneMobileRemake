package fr.univ_smb.iut_acy.perrincl.garthicphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.material.textfield.TextInputLayout;

import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class GameStartSessionActivity extends AppCompatActivity {

    private Player player;
    private String game_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start_session);
        game_id = getIntent().getStringExtra("game_id");

        ((TextInputLayout) findViewById(R.id.what_to_guest)).setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                startGame();
            }
            return false;
        });


        findViewById(R.id.gamesessionstart_button_start).setOnClickListener(v -> startGame());
    }

    private void startGame() {
        String guest = ((TextInputLayout) findViewById(R.id.what_to_guest)).getEditText().getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("game_id", game_id);
        intent.putExtra("guest", guest);
        startActivity(intent);
    }
}