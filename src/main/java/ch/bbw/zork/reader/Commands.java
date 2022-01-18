package ch.bbw.zork.reader;

import ch.bbw.zork.*;
import ch.bbw.zork.Player;
import ch.bbw.zork.Printer;
import ch.bbw.zork.Room;
import ch.bbw.zork.RoomName;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Commands {
    private final LinkedList<String> commandsList = new LinkedList<>();
    private final Game game;
    private final Player player;
    private final Map<String, Room> rooms;

    public Commands(Player player, Map<String, Room> rooms, Game game) {
        this.rooms = rooms;
        this.player = player;
        this.game = game;
        fillCommandsList();
    }

    private void fillCommandsList() {
        commandsList.add("go");
        commandsList.add("show");
        commandsList.add("drop");
        commandsList.add("grab");
        commandsList.add("back");
        commandsList.add("map");
        commandsList.add("help");
    }

    public void checkCommands(String[] words) {
        if (commandsList.contains(words[0])) {
            executeCommand(words);
        } else {
            System.out.println("Dieser Befehl existiert nicht! Probier es noch einmal.");
            //TODO Error Handling
        }
    }

    private void executeCommand(String[] words) {
        switch (words[0].trim()) {
            case "go":
                commandGo(words);
                break;
            case "show":
                commandShow(words);
                break;
            case "drop":
                commandDrop(words[1]);
                break;
            case "grab":
                commandGrab(words[1]);
                break;
            case "back":
                commandBack();
                break;
            case "help":
                commandHelp();
                break;
            case "map":
                commandMap();
                break;
        }
    }

    private void commandGo(String[] words) {
        if (words.length >= 2) {
            Map<String, Room> nearbyRooms = getNearbyRooms();
            StringBuilder roomInput = new StringBuilder(words[1]);
            for (int i = 2; i < words.length; i++) {
                roomInput.append(" ").append(words[i]);
            }
            if (nearbyRooms.containsKey(roomInput.toString())) {
                player.setLastRoom(player.getCurrentRoom());
                player.setCurrentRoom(RoomName.fromString(nearbyRooms.get(roomInput.toString()).getName()));
                System.out.println("Du bist jetzt im Raum " + player.getCurrentRoom().name);
            } else {
                System.out.println("Dieser Raum existiert nicht oder grenzt nicht an diesen Raum an!");
            }
        } else {
            System.out.println("Bitte gebe einen Raum an!");
        }
    }

    private void commandShow(String[] words) {
        if (words.length >= 2) {
            switch (words[1]) {
                case "exits":
                    commandShowExits();
                    break;
                case "items":
                    commandShowItems(words);
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

    private void commandShowItems(String[] words) {
        if (words.length >= 3) {
            switch (words[2]) {
                case "--global":
                    commandShowGlobalItems();
                    break;
                case "--winning":
                    commandShowWinningItems();
                    break;
            }
        } else {
            List<Item> items = rooms.get(player.getCurrentRoom().name).getItems();
            for (Item item : items) {
                System.out.println("{ name: " + item.getName() + ", gewicht: " + item.getWeight() + " }");
            }
        }
    }

    private void commandShowGlobalItems() {
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            for (Item item : entry.getValue().getItems()) {
                System.out.println("{ name: " + item.getName() + ", gewicht: " + item.getWeight() + " }");
            }
        }
    }

    private void commandShowWinningItems() {
        for (Item item : game.getWinningItems()) {
            System.out.println("{ name: " + item.getName() + ", gewicht: " + item.getWeight() + " }");
        }
    }

    private void commandShowBackpack() {
        for (Item item : player.getBackpack().getItems()) {
            System.out.println("{ name: " + item.getName() + ", gewicht: " + item.getWeight() + " }");
        }
        System.out.println("Verbleibendes Gewicht: " + (Backpack.MAX_WEIGHT_G - player.getBackpack().getCurrentWeight()));
    }

    private void commandShowRoom() {
        System.out.println(player.getCurrentRoom().name);
    }

    private void commandDrop(String parameter) {
        Room room = game.getRooms().get(game.getPlayer().getCurrentRoom().name);
        Backpack backpack = game.getPlayer().getBackpack();
        if (parameter.equals("--all")) {
            room.addItems(backpack.getItems());
            backpack.clear();
            System.out.println("Alle Items wurden aus dem Rucksack entfernt");
        } else {
            Item item = backpack.getItems().stream().filter(it -> it.getName().equals(parameter)).collect(Collectors.toList()).get(0);
            room.addItem(item);
            backpack.removeItem(item.getName());
            System.out.println("Das Item " + item.getName() + " wurde fallengelassen");
        }
    }

    private void commandGrab(String itemName) {
        Room room = game.getRooms().get(game.getPlayer().getCurrentRoom().name);
        List<Item> items = room.getItems().stream()
                .filter(it -> it.getName().equals(itemName)).collect(Collectors.toList());
        if (items.size() < 1) {
            System.out.println("Das Item" + itemName + " existiert nicht in diesem Raum");
        } else {
            game.getPlayer().getBackpack().addItem(items.get(0));
            room.removeItem(itemName);
        }
    }

    private void commandBack() {
        if (player.getLastRoom() != null) {
            RoomName tmpRoom = player.getCurrentRoom();

            player.setCurrentRoom(player.getLastRoom());
            player.setLastRoom(tmpRoom);
            System.out.println("Du befindest dich jetzt im Raum " + player.getCurrentRoom().name);
        }
        else {
            System.out.println("Du befindest dich im Startraum und kannst deshalb nicht zurück.");
        }
    }

    private void commandHelp() {
        Printer.show();
        Printer.map();
        Printer.back();
        Printer.go();
        Printer.drop_grep();
    }

    private void commandMap() {
        System.out.println("Map");
    }

    private Map<String, Room> getNearbyRooms() {
        return rooms.get(player.getCurrentRoom().name).getDoors();
    }
}
