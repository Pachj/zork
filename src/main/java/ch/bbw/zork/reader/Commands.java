package ch.bbw.zork.reader;

import ch.bbw.zork.Player;
import ch.bbw.zork.Room;
import ch.bbw.zork.RoomName;

import java.util.LinkedList;
import java.util.Map;

public class Commands {
    private String[] words;
    private final LinkedList<String> commandsList = new LinkedList<>();
    private final Player player;
    private final Map<String, Room> rooms;

    public Commands(Player player, Map<String, Room> rooms) {
        this.rooms = rooms;
        this.player = player;
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

    public void checkCommands(String[] words) {
        this.words = words;
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
        if (words.length >= 2) {
            Map<String, Room> nearbyRooms = getNearbyRooms();
            StringBuilder roomInput = new StringBuilder(words[1]);
            for (int i = 2; i < words.length; i++) {
                roomInput.append(" ").append(words[i]);
            }
            if (nearbyRooms.containsKey(roomInput.toString())) {
                player.setCurrentRoom(RoomName.fromString(nearbyRooms.get(roomInput.toString()).getName()));
                System.out.println("Du bist jetzt im Raum " + player.getCurrentRoom().name);
            } else {
                System.out.println("Dieser Raum existiert nicht oder grenzt nicht an diesen Raum an!");
            }
        } else {
            System.out.println("Bitte gebe einen Raum an!");
        }
    }

    private void commandShow() {
        if (words.length >= 2) {
            switch (words[1]) {
                case "exits":
                    commandShowExits();
                    break;
                case "items":
                    commandShowItems();
                    break;
                case "backpack":
                    commandShowBackpack();
                    break;
                case "room":
                    commandShowRoom();
                    break;
            }
        } else {
            System.out.println("Bitte gebe einen Parameter an!");
        }
    }

    private void commandShowExits() {
        Map<String, Room> nearbyRooms = getNearbyRooms();
        for (Map.Entry<String, Room> entry : nearbyRooms.entrySet()) {
            System.out.println(entry.getValue().getName());
        }
    }

    private void commandShowItems() {

    }

    private void commandShowBackpack() {

    }

    private void commandShowRoom() {
        System.out.println(player.getCurrentRoom().name);
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

    private Map<String, Room> getNearbyRooms() {
        return rooms.get(player.getCurrentRoom().name).getDoors();
    }
}
