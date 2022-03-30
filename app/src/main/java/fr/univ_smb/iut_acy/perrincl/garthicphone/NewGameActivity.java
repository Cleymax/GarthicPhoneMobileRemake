package fr.univ_smb.iut_acy.perrincl.garthicphone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class NewGameActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private static final String TAG = "NewGameActivity";
    private String GAME_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_new_game);
        this.GAME_ID = getIntent().getStringExtra("game_id");
        Log.d(TAG, "onCreate: " + GAME_ID);

        new ClientSocket(sp, "NEW_GAME-" + GAME_ID);

        ((TextView) findViewById(R.id.room_code)).setText(GAME_ID);

        findViewById(R.id.share_room).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "garthicphone://join?id=" + GAME_ID);
            startActivity(Intent.createChooser(intent, "Share via"));
        });

        findViewById(R.id.host_activity_qrcode_button).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(NewGameActivity.this);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(GAME_ID, BarcodeFormat.QR_CODE, 300, 300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                final Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                ImageView iv = new ImageView(NewGameActivity.this);
                iv.setImageBitmap(bitmap);
                builder.setTitle(getResources().getString(R.string.qrcode_share_with_friend));
                builder.setView(iv).create().show();
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });

        findViewById(R.id.host_activity_start_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, GameStartSessionActivity.class);
            new ClientSocket(sp, "START-" + GAME_ID);
            intent.putExtra("game_id", GAME_ID);
            startActivity(intent);
            finish();
        });
    }
}