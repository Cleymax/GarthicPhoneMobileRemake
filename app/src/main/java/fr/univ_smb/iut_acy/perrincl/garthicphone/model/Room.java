package fr.univ_smb.iut_acy.perrincl.garthicphone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univ_smb.iut_acy.perrincl.garthicphone.RoomDataListener;

public class Room {

    private final String roomCode;
    private Player host;
    private List<Player> players;

    public Room(String roomCode, Player player, RoomDataListener rdl) {
        this.roomCode = roomCode;
        this.host = player;
        this.players = new ArrayList<>();
    }

    public Room(Player host, RoomDataListener rdl) {
        this.host = host;
        this.roomCode =  generateRoomCode();
        this.players = new ArrayList<>();
        host.setCurrentRoom(this);
    }

    public static String generateRoomCode() {
        int stringLen = 6;
        Random random = new Random();
        char[] generatedString = new char [stringLen];

        for (int i=0;i<stringLen;i++){
            generatedString[i] = (char) ('A' + random.nextInt(26));
        }
        return String.valueOf(generatedString);
    }

    public String getRoomCode() {
        return roomCode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getHost() {
        return host;
    }
}
