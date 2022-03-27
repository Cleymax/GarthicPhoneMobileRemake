package fr.univ_smb.iut_acy.perrincl.garthicphone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.univ_smb.iut_acy.perrincl.garthicphone.R;
import fr.univ_smb.iut_acy.perrincl.garthicphone.model.Player;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.toggleStatus();
        holder.getUsername().setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void updatePlayers(List<Player> players){
        this.players = players;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        public ImageView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.status = itemView.findViewById(R.id.status);
        }

        public TextView getUsername() {
            return username;
        }

        public void setUsername(TextView username) {
            this.username = username;
        }

        public ImageView getStatus() {
            return status;
        }

        public void setStatus(ImageView status) {
            this.status = status;
        }

        public void toggleStatus() {
            if (status.getVisibility() == View.VISIBLE) {
                status.setVisibility(View.INVISIBLE);
            } else {
                status.setVisibility(View.VISIBLE);
            }
        }
    }

}
