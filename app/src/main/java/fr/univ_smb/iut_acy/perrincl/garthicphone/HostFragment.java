package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.univ_smb.iut_acy.perrincl.garthicphone.adapter.PlayerAdapter;
import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class HostFragment extends Fragment implements View.OnClickListener {

    private Player player;
    private Button startButton;
    private PlayerAdapter adapter;


    public HostFragment(String playerName, String roomCode){
        super(R.layout.fragment_host);
        Bundle bundle = new Bundle();
        bundle.putString("playerName", playerName);
        bundle.putString("roomCode", roomCode);
        setArguments(bundle);
    }

    public HostFragment(String playerName){
        super(R.layout.fragment_host);
        Bundle bundle = new Bundle();
        bundle.putString("playerName", playerName);
        setArguments(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.host_activity_start_button){
            player.setReady(!player.isReady());
        }
    }
}