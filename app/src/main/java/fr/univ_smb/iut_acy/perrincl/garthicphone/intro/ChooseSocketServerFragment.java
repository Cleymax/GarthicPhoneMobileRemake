package fr.univ_smb.iut_acy.perrincl.garthicphone.intro;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.InetAddresses;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import fr.univ_smb.iut_acy.perrincl.garthicphone.R;
import io.github.dreierf.materialintroscreen.SlideFragment;

public class ChooseSocketServerFragment extends SlideFragment {

    private TextInputLayout textInputLayoutIpAdress;
    private TextInputLayout textInputLayoutPortNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_choose_socket_slide, container, false);

       SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(view.getContext());

        textInputLayoutIpAdress = view.findViewById(R.id.ip_adress);
        textInputLayoutIpAdress.setError(view.getContext().getString(R.string.valid_address_ip));
        textInputLayoutIpAdress.setErrorEnabled(true);
        textInputLayoutIpAdress.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0 || !isIpValid(text.toString())) {
                    textInputLayoutIpAdress.setError(view.getContext().getString(R.string.valid_address_ip));
                    textInputLayoutIpAdress.setErrorEnabled(true);
                } else {
                    textInputLayoutIpAdress.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
               if(!textInputLayoutIpAdress.isErrorEnabled()){
                   sp.edit().putString("ip", textInputLayoutIpAdress.getEditText().getText().toString()).apply();
               }
            }
        });

        textInputLayoutPortNumber = (TextInputLayout) view.findViewById(R.id.port_number);
        textInputLayoutPortNumber.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0) {
                    textInputLayoutPortNumber.setError("Port is required");
                    textInputLayoutPortNumber.setErrorEnabled(true);
                } else if (!isValidPort(text.toString())) {
                    textInputLayoutPortNumber.setError(view.getContext().getString(R.string.invalid_port));
                    textInputLayoutPortNumber.setErrorEnabled(true);
                } else {
                    textInputLayoutPortNumber.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!textInputLayoutPortNumber.isErrorEnabled()){
                    sp.edit().putString("port", textInputLayoutPortNumber.getEditText().getText().toString()).apply();
                }
            }
        });
        return view;
    }

    private boolean isIpValid(String ip) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            return InetAddresses.isNumericAddress(ip);
        } else {
            return Patterns.IP_ADDRESS.matcher(ip).matches();
        }
    }

    private boolean isValidPort(String toString) {
        return Integer.parseInt(toString) >= 0 && Integer.parseInt(toString) <= 65535;
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
        return !this.textInputLayoutIpAdress.isErrorEnabled() && !this.textInputLayoutPortNumber.isErrorEnabled();
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        String message = null;

        if (textInputLayoutPortNumber.isErrorEnabled()) {
            message = textInputLayoutPortNumber.getError().toString();
        } else if (textInputLayoutIpAdress.isErrorEnabled()) {
            message = textInputLayoutIpAdress.getError().toString();
        }

        return message;
    }

}
