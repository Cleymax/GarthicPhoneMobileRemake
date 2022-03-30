package fr.univ_smb.iut_acy.perrincl.garthicphone.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import fr.univ_smb.iut_acy.perrincl.garthicphone.RoomDataListener;

public class Player {

    private UUID id;
    private String name;
    private boolean ready;

    public Player() {
        this.ready = false;
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    public Player(String playerName, UUID id, Boolean ready) {
        this(playerName);
        this.ready = ready;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    private Room currentRoom;

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void connectToRoom(String roomCode, RoomDataListener rdl) {
        //TODO JOIN ROOM
    }

    public void createRoom(RoomDataListener rdl) {
        currentRoom = new Room(this, rdl);
    }


    public Map<String, String> toDictionary() {
        Map<String, String> map = new HashMap<>();
        map.put("playerName", getName());
        map.put("playerId", getId().toString());
        map.put("ready", ready ? "1" : "0");
        return map;
    }
}
