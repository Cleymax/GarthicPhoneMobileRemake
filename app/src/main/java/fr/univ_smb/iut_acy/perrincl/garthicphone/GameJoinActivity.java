package fr.univ_smb.iut_acy.perrincl.garthicphone;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class GameJoinActivity extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() != null) {
                    if (isValidCode(result.getContents())) {
                        ((TextInputLayout) findViewById(R.id.joinActivity_code)).getEditText().setText(result.getContents());
                    } else {
                        Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    private boolean isValidCode(String contents) {
        return contents.length() == 6 && contents.matches("[0-9]+");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_join);

        findViewById(R.id.game_join_activty_scan_qrcode).setOnClickListener(v -> {
            ScanOptions so = new ScanOptions();
            so.setBeepEnabled(false);
            so.setCameraId(0);
            so.setOrientationLocked(false);
            so.setBarcodeImageEnabled(true);
            so.setPrompt("Scan QR Code of your friend !");
            barcodeLauncher.launch(so);
        });

        TextInputLayout til = findViewById(R.id.joinActivity_code);
        til.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 6) {
                    til.setError("Le code doit être de 6 caractères");
                    til.setErrorEnabled(true);
                } else {
                    til.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}