package fr.univ_smb.iut_acy.perrincl.garthicphone.settings;


import android.net.InetAddresses;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.ThemeUtils;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.material.snackbar.Snackbar;

import fr.univ_smb.iut_acy.perrincl.garthicphone.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.settings__title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            findPreference("ip").setOnPreferenceChangeListener((preference, newValue) -> {
                boolean result =  isIpValid(newValue.toString());
                if(!result){
                    Snackbar.make(this.getView(), R.string.valid_address_ip, Snackbar.LENGTH_LONG).show();
                }
                return result;
            });
            findPreference("port").setOnPreferenceChangeListener((preference, newValue) -> {
                boolean result =  isPortValid(newValue.toString());
                if(!result){
                    Snackbar.make(this.getView(), R.string.invalid_port, Snackbar.LENGTH_LONG).show();
                }
                return result;
            });
            findPreference("theme").setOnPreferenceChangeListener((preference, newValue) -> {
                if(newValue.toString().equals("dark")){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else if (newValue.toString().equals("light")){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                return true;
            });
        }

        private boolean isPortValid(String toString) {
            try {
                int port = Integer.parseInt(toString);
                if(port < 0 || port > 65535){
                    return false;
                }
            }catch (NumberFormatException e){
                return false;
            }
            return true;
        }
    }

    public static boolean isIpValid(String ip) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            return InetAddresses.isNumericAddress(ip);
        } else {
            return Patterns.IP_ADDRESS.matcher(ip).matches();
        }
    }
}