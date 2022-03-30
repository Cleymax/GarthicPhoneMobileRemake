package fr.univ_smb.iut_acy.perrincl.garthicphone.intro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import fr.univ_smb.iut_acy.perrincl.garthicphone.R;
import io.github.dreierf.materialintroscreen.SlideFragment;

public class ChooseUsernameFragment extends SlideFragment {

    private TextInputLayout textInputUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_username, container, false);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        this.textInputUsername = view.findViewById(R.id.username_input_layout);
        this.textInputUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sp.edit().putString("username", editable.toString()).apply();
            }
        });

        return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.light_black;
    }

    @Override
    public int buttonsColor() {
        return R.color.primaryTwo;
    }

    @Override
    public boolean canMoveFurther() {
        return true;
    }
}
