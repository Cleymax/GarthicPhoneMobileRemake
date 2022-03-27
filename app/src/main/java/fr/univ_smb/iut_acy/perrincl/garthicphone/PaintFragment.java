package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

import fr.univ_smb.iut_acy.perrincl.garthicphone.model.PaintView;
import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class PaintFragment extends Fragment implements View.OnClickListener {

    private PaintView paintView;
    private Boolean validated = false;
    private Player player;
    private int turn;
    private TextView chrono;
    private TextView expressionTextView;

    public PaintFragment(Player player, int turn) {
        super(R.layout.activity_paint);
        this.player = player;
        this.turn = turn;
    }

    @Override
    public void onStart() {
        super.onStart();


        ColorSeekBar colorSeekBar = getView().findViewById(R.id.colorSlider);
        colorSeekBar.setOnColorChangeListener((colorBarPosition, alphaBarPosition, color) -> paintView.setCurrentColor(color));

        getView().findViewById(R.id.paint_activity_backButton).setOnClickListener(this);
        getView().findViewById(R.id.paint_activity_readyButton).setOnClickListener(this);

        paintView = (PaintView) getView().findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        chrono = ((TextView) getView().findViewById(R.id.paint_activity_chrono));
        expressionTextView = ((TextView) getView().findViewById(R.id.paint_activity_expression));

        new CountDownTimer(120 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                chrono.setText(String.valueOf(millisUntilFinished / 1000));
                if (millisUntilFinished < 1001) {
                    paintView.setDrawIsEnable(false);
                }
            }

            public void onFinish() {
                if (!validated) {
                    validated = true;
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paint_activity_backButton:
                paintView.deleteLastFingerPath();
                break;
            case R.id.paint_activity_readyButton:
                if (!validated) {
                    validated = true;
                    sendImage();
                }
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void sendImage() {
        Bitmap image = paintView.getmBitmap();
        paintView.setDrawIsEnable(false);
        Toast.makeText(getContext(), "Send !", Toast.LENGTH_SHORT).show();

    }
}
