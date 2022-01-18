package ch.bbw.zork;

import ch.bbw.zork.reader.Commands;
import ch.bbw.zork.reader.Parser;

import java.io.IOException;
import java.util.*;

public class Game {
    private final Map<String, Room> rooms;
    private final Set<Item> winningItems;
    private final Map<ItemName, Item> items;
    private final Player player;
    private final Commands commands;
    private final Parser parser;
    public static final int NEEDED_ITEMS = 5;

    public Game() {
        player = new Player(choosePlayerName());
        rooms = new HashMap<>();
        winningItems = new HashSet<>();
        items = new HashMap<>();
        initialise();
        commands = new Commands(player, rooms, this);
        parser = new Parser();
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public Set<Item> getWinningItems() {
        return winningItems;
    }

    public Map<ItemName, Item> getItems() {
        return items;
    }

    public Player getPlayer() {
        return player;
    }

    private String choosePlayerName() {
        Parser parser = new Parser();
        String name = "";
        System.out.println("Gib deinen Namen ein: ");
        try {
            name = parser.readLine();
            System.out.println("Dein Name: " + name);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ein Fehler ist aufgetreten! Versuche es noch einmal.");
            choosePlayerName();
        }
        //TODO Handle empty name
        return name;
    }

    private void initialise() {
        initialiseItems();
        initialiseRooms();
        initialiseDoors();
        initialiseWinningItems();
    }

    private void initialiseRooms() {
        List<Item> roomItems = new LinkedList<>();
        roomItems.add(items.get(ItemName.BIERKRUG));
        roomItems.add(items.get(ItemName.HOCKER));
        roomItems.add(items.get(ItemName.KAKERLAKE));
        rooms.put(RoomName.TAVERNE.name, new Room(RoomName.TAVERNE.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.ROSE));
        roomItems.add(items.get(ItemName.SCHMETTERLING));
        roomItems.add(items.get(ItemName.RAUPE));
        roomItems.add(items.get(ItemName.SCHAUFEL));
        rooms.put(RoomName.OUTSIDE.name, new Room(RoomName.OUTSIDE.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.REAGENZGLAS));
        roomItems.add(items.get(ItemName.ZAUBERTRANK));
        roomItems.add(items.get(ItemName.SKALPELL));
        roomItems.add(items.get(ItemName.APFEL));
        rooms.put(RoomName.LAB.name, new Room(RoomName.LAB.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.FERNSEHER));
        roomItems.add(items.get(ItemName.KRUEMEL));
        roomItems.add(items.get(ItemName.PENNY));
        roomItems.add(items.get(ItemName.MONOPOLY));
        roomItems.add(items.get(ItemName.ERDE));
        rooms.put(RoomName.LIVING_ROOM.name, new Room(RoomName.LIVING_ROOM.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.HANDY));
        roomItems.add(items.get(ItemName.PUTZMITTEL));
        roomItems.add(items.get(ItemName.STROMKABEL));
        roomItems.add(items.get(ItemName.SANDWICH));
        rooms.put(RoomName.OFFICE.name, new Room(RoomName.OFFICE.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.PALME));
        roomItems.add(items.get(ItemName.GRILL));
        roomItems.add(items.get(ItemName.FLEISCH));
        roomItems.add(items.get(ItemName.SCHLUESSEL));
        roomItems.add(items.get(ItemName.SCHROTFLINTE));
        rooms.put(RoomName.BALCONY.name, new Room(RoomName.BALCONY.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.WC_PAPIER));
        roomItems.add(items.get(ItemName.BUCH));
        roomItems.add(items.get(ItemName.WLAN_ROUTER));
        rooms.put(RoomName.WC.name, new Room(RoomName.WC.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.MESSER));
        roomItems.add(items.get(ItemName.LAUCH));
        roomItems.add(items.get(ItemName.WASSERFLASCHE));
        rooms.put(RoomName.KITCHEN.name, new Room(RoomName.KITCHEN.name, roomItems));

        rooms.put(RoomName.EXIT_ROOM.name, new Room(RoomName.EXIT_ROOM.name, new LinkedList<>()));
    }

    private void initialiseDoors() {
        Map<String, Room> doors = new HashMap<>();
        doors.put(RoomName.OUTSIDE.name, rooms.get(RoomName.OUTSIDE.name));
        rooms.get(RoomName.TAVERNE.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.LAB.name, rooms.get(RoomName.LAB.name));
        doors.put(RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name));
        doors.put(RoomName.TAVERNE.name, rooms.get(RoomName.TAVERNE.name));
        rooms.get(RoomName.OUTSIDE.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.OUTSIDE.name, rooms.get(RoomName.OUTSIDE.name));
        rooms.get(RoomName.LAB.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.OUTSIDE.name, rooms.get(RoomName.OUTSIDE.name));
        doors.put(RoomName.OFFICE.name, rooms.get(RoomName.OFFICE.name));
        doors.put(RoomName.BALCONY.name, rooms.get(RoomName.BALCONY.name));
        doors.put(RoomName.EXIT_ROOM.name, rooms.get(RoomName.EXIT_ROOM.name));
        rooms.get(RoomName.LIVING_ROOM.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.KITCHEN.name, rooms.get(RoomName.KITCHEN.name));
        doors.put(RoomName.WC.name, rooms.get(RoomName.WC.name));
        doors.put(RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name));
        rooms.get(RoomName.OFFICE.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.OFFICE.name, rooms.get(RoomName.OFFICE.name));
        rooms.get(RoomName.KITCHEN.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.OFFICE.name, rooms.get(RoomName.OFFICE.name));
        rooms.get(RoomName.WC.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name));
        rooms.get(RoomName.BALCONY.name).setDoors(doors);

        doors = new HashMap<>();
        doors.put(RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name));
        rooms.get(RoomName.EXIT_ROOM.name).setDoors(doors);
    }

    private void initialiseItems() {
        items.put(ItemName.GRILL, new Item(20000, ItemName.GRILL.name));
        items.put(ItemName.PALME, new Item(18000, ItemName.PALME.name));
        items.put(ItemName.FERNSEHER, new Item(15000, ItemName.FERNSEHER.name));
        items.put(ItemName.SCHROTFLINTE, new Item(6500, ItemName.SCHROTFLINTE.name));
        items.put(ItemName.ERDE, new Item(6000, ItemName.ERDE.name));
        items.put(ItemName.HOCKER, new Item(5500, ItemName.HOCKER.name));
        items.put(ItemName.SCHAUFEL, new Item(3000, ItemName.HOCKER.name));
        items.put(ItemName.WLAN_ROUTER, new Item(2000, ItemName.WLAN_ROUTER.name));
        items.put(ItemName.BIERKRUG, new Item(1000, ItemName.BIERKRUG.name));
        items.put(ItemName.PUTZMITTEL, new Item(1000, ItemName.PUTZMITTEL.name));
        items.put(ItemName.MONOPOLY, new Item(1000, ItemName.MONOPOLY.name));
        items.put(ItemName.BUCH, new Item(300, ItemName.BUCH.name));
        items.put(ItemName.HANDY, new Item(150, ItemName.HANDY.name));
        items.put(ItemName.FLEISCH, new Item(150, ItemName.FLEISCH.name));
        items.put(ItemName.SANDWICH, new Item(150, ItemName.SANDWICH.name));
        items.put(ItemName.APFEL, new Item(100, ItemName.APFEL.name));
        items.put(ItemName.STROMKABEL, new Item(100, ItemName.STROMKABEL.name));
        items.put(ItemName.ZAUBERTRANK, new Item(80, ItemName.ZAUBERTRANK.name));
        items.put(ItemName.WC_PAPIER, new Item(80, ItemName.WC_PAPIER.name));
        items.put(ItemName.REAGENZGLAS, new Item(80, ItemName.REAGENZGLAS.name));
        items.put(ItemName.SCHLUESSEL, new Item(20, ItemName.SCHLUESSEL.name));
        items.put(ItemName.SKALPELL, new Item(20, ItemName.SKALPELL.name));
        items.put(ItemName.ROSE, new Item(20, ItemName.ROSE.name));
        items.put(ItemName.PENNY, new Item(5, ItemName.PENNY.name));
        items.put(ItemName.KAKERLAKE, new Item(1, ItemName.KAKERLAKE.name));
        items.put(ItemName.RAUPE, new Item(1, ItemName.RAUPE.name));
        items.put(ItemName.SCHMETTERLING, new Item(1, ItemName.SCHLUESSEL.name));
        items.put(ItemName.KRUEMEL, new Item(1, ItemName.KRUEMEL.name));
        items.put(ItemName.MESSER, new Item(300, ItemName.MESSER.name));
        items.put(ItemName.WASSERFLASCHE, new Item(1500, ItemName.WASSERFLASCHE.name));
        items.put(ItemName.LAUCH, new Item(100, ItemName.LAUCH.name));
    }

    private void initialiseWinningItems() {
        List<Item> items = new ArrayList<>(this.items.values());
        while (winningItems.size() < NEEDED_ITEMS) {
            int random = (int) Math.round(Math.random() * (items.size() - 1));
            winningItems.add(items.get(random));
        }
    }

    public void start() {
        Printer.loading();
        Printer.zork_asci();
        Printer.greet(player.getName());
        sleep(6000);

        System.out.println("\nDiese Items benötigst du:\n");
        sleep(1000);
        Printer.printItems(new ArrayList<>(winningItems));

        Printer.go();
        sleep(5000);

        Printer.show();
        sleep(8000);

        Printer.drop_grab();
        sleep(8000);

        Printer.back();
        sleep(5000);

        Printer.map();
        sleep(5000);

        Printer.help();

        System.out.println("Gebe deinen ersten Befehl ein:");
        while (true) {
            try {
                commands.checkCommands(parser.readWords());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
