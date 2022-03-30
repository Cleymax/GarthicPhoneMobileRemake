package fr.univ_smb.iut_acy.perrincl.garthicphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.univ_smb.iut_acy.perrincl.garthicphone.intro.IntroActivity;
import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Room;
import fr.univ_smb.iut_acy.perrincl.garthicphone.settings.SettingsActivity;
import fr.univ_smb.iut_acy.perrincl.garthicphone.tools.RandomUtils;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        if (this.sharedPreferences.getBoolean("first_run", true)) {
            this.sharedPreferences.edit().putBoolean("first_run", false).apply();
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }

        String theme = this.sharedPreferences.getString("theme", "system");

        if (theme.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (theme.equals("light")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        setContentView(R.layout.activity_main);

        findViewById(R.id.settings_btn).setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.create_game).setOnClickListener(view -> {
            Intent intent = new Intent(this, NewGameActivity.class);
            intent.putExtra("game_id", Room.generateRoomCode());
            startActivity(intent);
        });
        findViewById(R.id.join_game).setOnClickListener(view -> {
            Intent intent = new Intent(this, GameJoinActivity.class);

            startActivity(intent);
        });
        findViewById(R.id.rules).setOnClickListener(view -> {
            Intent intent = new Intent(this, RuleActivty.class);
            startActivity(intent);
        });
    }

}