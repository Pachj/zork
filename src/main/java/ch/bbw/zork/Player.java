package ch.bbw.zork;

public class Player {
    private final Backpack backpack;
    private RoomName currentRoom;
    private RoomName lastRoom;
    private String name;

    public Player() {
        backpack = new Backpack();
        currentRoom = RoomName.EXIT_ROOM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
