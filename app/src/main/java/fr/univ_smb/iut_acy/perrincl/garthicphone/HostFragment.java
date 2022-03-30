package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.univ_smb.iut_acy.perrincl.garthicphone.adapter.PlayerAdapter;
import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class HostFragment extends Fragment implements View.OnClickListener {

    private Player player;
    private Button startButton;
    private RoomDataListener rdl;
    private PlayerAdapter adapteur;


    public HostFragment(String playerName, String roomCode) {
        super(R.layout.fragment_host);
        Bundle bundle = new Bundle();
        bundle.putString("playerName", playerName);
        bundle.putString("roomCode", roomCode);
        setArguments(bundle);
    }

    public HostFragment(String playerName) {
        super(R.layout.fragment_host);
        Bundle bundle = new Bundle();
        bundle.putString("playerName", playerName);
        setArguments(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();

        RecyclerView listView = getView().findViewById(R.id.host_activity_listView);
        rdl = new RoomDataListener() {

            @Override
            public void initialize() {
                listView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapteur = new PlayerAdapter(player.getCurrentRoom().getPlayers());
                listView.setAdapter(adapteur);
            }

            @Override
            public void update() {
                if (listView.getAdapter() != null)
                    adapteur.updatePlayers(player.getCurrentRoom().getPlayers());
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void launch() {
                //TODO lock room ==> to server
                player.setReady(false);

             //   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GameStartSessionActivity(player)).commit();

            }
        };

        player = new Player(getArguments().getString("playerName"));

        if (getArguments().getString("roomCode") != null) {
            ((TextView) getView().findViewById(R.id.host_activity_code)).setText(getArguments().getString("roomCode"));
            player.connectToRoom(getArguments().getString("roomCode"), rdl);
        } else {
            player.createRoom(rdl);
            ((TextView) getView().findViewById(R.id.host_activity_code)).setText(player.getCurrentRoom().getRoomCode());
        }

        ((GameActivity) getActivity()).setPlayer(player);
        startButton = getView().findViewById(R.id.host_activity_start_button);
        startButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.host_activity_start_button) {
            player.setReady(!player.isReady());
        }
    }
}