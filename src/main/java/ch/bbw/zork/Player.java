package ch.bbw.zork;

public class Player {
    private final Backpack backpack;
    private final String name;
    private RoomName currentRoom;
    private RoomName lastRoom;

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

    public RoomName getLastRoom() {
        return lastRoom;
    }

    public void setLastRoom(RoomName lastRoom) {
        this.lastRoom = lastRoom;
    }
}
