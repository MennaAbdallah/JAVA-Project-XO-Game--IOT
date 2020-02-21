package DTO.GameOnlineClass;

import java.io.Serializable;

public class Play implements Serializable{
    private int location,type;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
