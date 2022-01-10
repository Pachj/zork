package ch.bbw.zork.reader;

import ch.bbw.zork.Player;
import ch.bbw.zork.Room;
import ch.bbw.zork.RoomName;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class Commands {
    private final Parser parser;
    private String[] words;
    private final LinkedList<String> commandsList = new LinkedList<>();
    private final Player player;
    private final Map<RoomName, Room> rooms;

    public Commands(Player player,  Map<RoomName, Room> rooms) {
        this.rooms = rooms;
        this.player = player;
        parser = new Parser();
        fillCommandsList();
    }

    private void fillCommandsList() {
        commandsList.add("go");
        commandsList.add("show");
        commandsList.add("drop");
        commandsList.add("grap");
        commandsList.add("back");
        commandsList.add("map");
    }

    public void checkCommands() {
        if (commandsList.contains(words[0])) {
            executeCommand();
        } else {
            System.out.println("Dieser Befehl existiert nicht! Probier es noch einmal.");
            //TODO Error Handling
        }
    }

    private void executeCommand() {
        switch (words[0].trim()) {
            case "go":
                commandGo();
                break;
            case "show":
                commandShow();
                break;
            case "drop":
                commandDrop();
                break;
            case "grap":
                commandGrap();
                break;
            case "back":
                commandBack();
                break;
            case "map":
                commandMap();
                break;
        }
    }

    private void commandGo() {
        if (words.length == 2) {
            RoomName currentRoomName = player.getCurrentRoom();
            Room currentRoom = rooms.get(currentRoomName);
            Map<RoomName, Room> nearbyRooms = currentRoom.getDoors();
            String roomInput = words[1];
        }
    }

    private void commandShow() {
        System.out.println("Show");
    }

    private void commandDrop() {
        System.out.println("Drop");
    }

    private void commandGrap() {
        System.out.println("Grap");
    }

    private void commandBack() {
        System.out.println("Back");
    }

    private void commandMap() {
        System.out.println("Map");
    }
}
