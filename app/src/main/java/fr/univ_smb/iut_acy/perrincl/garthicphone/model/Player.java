package fr.univ_smb.iut_acy.perrincl.garthicphone.model;

import java.util.UUID;

public class Player {

    private UUID id;
    private String name;
    private boolean ready;

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
}
