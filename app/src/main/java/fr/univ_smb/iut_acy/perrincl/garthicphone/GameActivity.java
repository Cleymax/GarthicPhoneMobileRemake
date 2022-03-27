package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class GameActivity extends AppCompatActivity {

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player = new Player();
        player.setId(UUID.randomUUID());
        player.setName("Cleymax");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PaintFragment(player, 1)).commit();

    }
}
