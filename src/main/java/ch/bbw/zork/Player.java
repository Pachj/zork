package ch.bbw.zork;

public class Player {
    private final Backpack backpack;
    private final String name;
    private RoomName currentRoom;

    public Player(String name) {
        backpack = new Backpack();
        this.name = name;
        currentRoom = RoomName.EXIT_ROOM;
    }

    public String getName() {
        return name;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public RoomName getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(RoomName currentRoom) {
        this.currentRoom = currentRoom;
    }
}
