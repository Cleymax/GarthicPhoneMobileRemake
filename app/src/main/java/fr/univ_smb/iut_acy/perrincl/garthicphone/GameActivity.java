package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import java.util.UUID;

import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class GameActivity extends AppCompatActivity {

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

        setContentView(R.layout.activity_game);
        player = new Player();
        player.setId(UUID.randomUUID());
        player.setName(sp.getString("username", "Incognito"));

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        Fragment hostFragment;

        String room_code = getIntent().getStringExtra("room_code");

        if (room_code != null) {
            hostFragment = new HostFragment(player.getName(), room_code);
        } else {
            hostFragment = new HostFragment(player.getName());
        }

        String guest = getIntent().getStringExtra("guest");

        if (fragment == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PaintFragment(player, 1, guest), null).commit();
        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PaintFragment(player, 1)).commit();

    }
}
