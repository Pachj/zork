package ch.bbw.zork;

import java.util.List;
import java.util.Map;

public class Room {
    private final String name;
    private final List<Item> items;
    private Map<RoomName, Room> doors;

    public Room(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public Map<RoomName, Room> getDoors() {
        return doors;
    }

    public void setDoors(Map<RoomName, Room> doors) {
        this.doors = doors;
    }
}
