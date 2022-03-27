package fr.univ_smb.iut_acy.perrincl.garthicphone.intro;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import fr.univ_smb.iut_acy.perrincl.garthicphone.R;
import fr.univ_smb.iut_acy.perrincl.garthicphone.intro.ChooseSocketServerFragment;
import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideBackButton();

        enableLastSlideAlphaExitTransition(true);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.light_black)
                .image(R.drawable.pencil_pen_svgrepo_com)
                .title(getResources().getString(R.string.app_name))
                .description(getResources().getString(R.string.intro_description))
                .buttonsColor(R.color.primaryTwo)
                .build()
        );

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.primaryTwo)
                .buttonsColor(R.color.light_black)
                .neededPermissions(new String[]{Manifest.permission.GET_ACCOUNTS})
                .title(getResources().getString(R.string.need_permission))
                .build());

        addSlide(new ChooseSocketServerFragment());
    }
}
