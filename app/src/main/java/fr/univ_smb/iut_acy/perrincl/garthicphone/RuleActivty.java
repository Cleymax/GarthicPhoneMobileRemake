package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.os.Bundle;

import androidx.annotation.Nullable;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class RuleActivty extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableLastSlideAlphaExitTransition(true);
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.light_black)
                .image(R.drawable.pencil_pen_svgrepo_com)
                .title(getResources().getString(R.string.app_name))
                .description("Créé une partie avec vos amis ! ")
                .buttonsColor(R.color.primaryTwo)
                .build()
        );
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.light_black)
                .title(getResources().getString(R.string.app_name))
                .description("Invitez les !")
                .buttonsColor(R.color.primaryTwo)
                .build()
        );
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.light_black)
                .title(getResources().getString(R.string.app_name))
                .description("Dessinez ! ")
                .buttonsColor(R.color.primaryTwo)
                .build()
        );
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.light_black)
                .title(getResources().getString(R.string.app_name))
                .description("Du fun  ! ")
                .buttonsColor(R.color.primaryTwo)
                .build()
        );
    }
}
