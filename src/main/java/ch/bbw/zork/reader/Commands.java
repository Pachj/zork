package ch.bbw.zork.reader;

import ch.bbw.zork.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Commands {
    private final Game game;
    private final Player player;
    private final Map<String, Room> rooms;

    public Commands(Game game) {
        this.rooms = game.getRooms();
        this.player = game.getPlayer();
        this.game = game;
    }

    public void executeCommand(String[] words) {
        switch (words[0]) {
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
            default:
                System.err.println("Der Befehl " + words[0] + " existiert nicht");
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
                default:
                    Printer.parameterDoesNotExists(words[1]);
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
                    Printer.printItems(Arrays.stream(Item.values()).collect(Collectors.toList()), 0);
                    break;
                case "--winning":
                    Printer.printItems(new ArrayList<>(game.getWinningItems()), 0);
                    break;
                default:
                    Printer.parameterDoesNotExists(words[2]);
            }
        } else {
            List<Item> items = rooms.get(player.getCurrentRoom().name).getItems();
            Printer.printItems(items, 0);
        }
    }

    private void commandShowBackpack() {
        Printer.printItems(player.getBackpack().getItems(), 0);
        System.out.println("Verbleibendes Gewicht: " + player.getBackpack().getRemainingCapacity());
    }

    private void commandShowRoom() {
        System.out.println(player.getCurrentRoom().name);
    }

    private void commandDrop(String parameter) {
        Room room = game.getRooms().get(game.getPlayer().getCurrentRoom().name);
        Backpack backpack = game.getPlayer().getBackpack();
        if (parameter.equals("--all")) {
            if (backpack.getItems().size() > 1) {
                room.addItems(backpack.getItems());
            } else if (backpack.getItems().size() != 0) {
                room.addItem(backpack.getItems().get(0));
            }
            backpack.clear();
            System.out.println("Alle Items wurden aus dem Rucksack entfernt");
        } else {
            List<Item> items = backpack.getItems().stream().filter(it -> it.name.equals(parameter)).collect(Collectors.toList());
            if (items.size() < 1) {
                System.out.println("Das Item " + parameter + " hast du nicht in deinem Rucksack");
            } else {
                Item item = items.get(0);
                room.addItem(item);
                backpack.removeItem(item.name);
                System.out.println("Das Item " + item.name + " wurde fallengelassen");
            }
        }
        checkGameWon();
    }

    private void checkGameWon() {
        if (game.getPlayer().getCurrentRoom().equals(RoomName.EXIT_ROOM)) {
            AtomicReference<Boolean> allNeededItemsDropped = new AtomicReference<>(true);
            List<String> items = new LinkedList<>();
            game.getRooms().get(game.getPlayer().getCurrentRoom().name).getItems().forEach(it -> items.add(it.name));
            List<String> neededItems = new LinkedList<>();
            game.getWinningItems().forEach(it -> neededItems.add(it.name));

            neededItems.forEach(it -> {
                if (!items.contains(it)) {
                    allNeededItemsDropped.set(false);
                }
            });
            if (allNeededItemsDropped.get()) {
                Printer.won();
            }
        }
    }

    private void commandGrab(String itemName) {
        Room room = game.getRooms().get(game.getPlayer().getCurrentRoom().name);
        List<Item> items = room.getItems().stream()
                .filter(it -> it.name.equals(itemName)).collect(Collectors.toList());
        if (items.size() < 1) {
            System.out.println("Das Item " + itemName + " existiert nicht in diesem Raum");
        } else {
            boolean succeed = game.getPlayer().getBackpack().addItem(items.get(0));
            if (succeed) {
                room.removeItem(itemName);
            }
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
        Printer.drop_grab();
    }

    private void commandMap() {
        Printer.printMap();
    }

    private Map<String, Room> getNearbyRooms() {
        return rooms.get(player.getCurrentRoom().name).getDoors();
    }
}
