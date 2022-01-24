package ch.bbw.zork;

import ch.bbw.zork.reader.Commands;
import ch.bbw.zork.reader.Parser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public static final int NEEDED_ITEMS = 5;
    private final Map<String, Room> rooms;
    private final Set<Item> winningItems;
    private final Player player;
    private final Commands commands;
    private final Parser parser;
    private boolean running = true;

    public Game() {
        rooms = new HashMap<>();
        winningItems = new HashSet<>();
        initialise();
        player = new Player();
        commands = new Commands(this);
        parser = new Parser();
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public Set<Item> getWinningItems() {
        return winningItems;
    }

    public Player getPlayer() {
        return player;
    }

    public String choosePlayerName() {
        Parser parser = new Parser();
        String name = "";
        System.out.println("Gib deinen Namen ein: ");
        try {
            name = parser.readLine();
            if (name.isBlank()) {
                name = "Neuling";
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ein Fehler ist aufgetreten! Versuche es noch einmal.");
            choosePlayerName();
        }
        return name;
    }

    private void initialise() {
        initialiseRooms();
        initialiseDoors();
        initialiseWinningItems();
    }

    private void initialiseRooms() {
        List<Item> roomItems = new LinkedList<>();
        roomItems.add(Item.BIERKRUG);
        roomItems.add(Item.HOCKER);
        roomItems.add(Item.KAKERLAKE);
        rooms.put(RoomName.TAVERNE.name, new Room(RoomName.TAVERNE.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.ROSE);
        roomItems.add(Item.SCHMETTERLING);
        roomItems.add(Item.RAUPE);
        roomItems.add(Item.SCHAUFEL);
        rooms.put(RoomName.OUTSIDE.name, new Room(RoomName.OUTSIDE.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.REAGENZGLAS);
        roomItems.add(Item.ZAUBERTRANK);
        roomItems.add(Item.SKALPELL);
        roomItems.add(Item.APFEL);
        rooms.put(RoomName.LAB.name, new Room(RoomName.LAB.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.FERNSEHER);
        roomItems.add(Item.KRUEMEL);
        roomItems.add(Item.PENNY);
        roomItems.add(Item.MONOPOLY);
        roomItems.add(Item.ERDE);
        rooms.put(RoomName.LIVING_ROOM.name, new Room(RoomName.LIVING_ROOM.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.HANDY);
        roomItems.add(Item.PUTZMITTEL);
        roomItems.add(Item.STROMKABEL);
        roomItems.add(Item.SANDWICH);
        rooms.put(RoomName.OFFICE.name, new Room(RoomName.OFFICE.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.PALME);
        roomItems.add(Item.GRILL);
        roomItems.add(Item.FLEISCH);
        roomItems.add(Item.SCHLUESSEL);
        roomItems.add(Item.SCHROTFLINTE);
        rooms.put(RoomName.BALCONY.name, new Room(RoomName.BALCONY.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.WC_PAPIER);
        roomItems.add(Item.BUCH);
        roomItems.add(Item.WLAN_ROUTER);
        rooms.put(RoomName.WC.name, new Room(RoomName.WC.name, roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(Item.MESSER);
        roomItems.add(Item.LAUCH);
        roomItems.add(Item.WASSERFLASCHE);
        rooms.put(RoomName.KITCHEN.name, new Room(RoomName.KITCHEN.name, roomItems));

        rooms.put(RoomName.EXIT_ROOM.name, new Room(RoomName.EXIT_ROOM.name, new LinkedList<>()));
    }

    private void initialiseDoors() {
        rooms.get(RoomName.TAVERNE.name).setDoors(
                Map.of(
                        RoomName.OUTSIDE.name, rooms.get(RoomName.OUTSIDE.name)
                ));

        rooms.get(RoomName.OUTSIDE.name).setDoors(
                Map.of(
                        RoomName.LAB.name, rooms.get(RoomName.LAB.name),
                        RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name),
                        RoomName.TAVERNE.name, rooms.get(RoomName.TAVERNE.name)
                ));

        rooms.get(RoomName.LAB.name).setDoors(
                Map.of(
                        RoomName.OUTSIDE.name, rooms.get(RoomName.OUTSIDE.name)
                ));


        rooms.get(RoomName.LIVING_ROOM.name).setDoors(
                Map.of(
                        RoomName.OUTSIDE.name, rooms.get(RoomName.OUTSIDE.name),
                        RoomName.OFFICE.name, rooms.get(RoomName.OFFICE.name),
                        RoomName.BALCONY.name, rooms.get(RoomName.BALCONY.name),
                        RoomName.EXIT_ROOM.name, rooms.get(RoomName.EXIT_ROOM.name)
                ));

        rooms.get(RoomName.OFFICE.name).setDoors(
                Map.of(
                        RoomName.KITCHEN.name, rooms.get(RoomName.KITCHEN.name),
                        RoomName.WC.name, rooms.get(RoomName.WC.name),
                        RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name)
                ));

        rooms.get(RoomName.KITCHEN.name).setDoors(
                Map.of(
                        RoomName.OFFICE.name, rooms.get(RoomName.OFFICE.name)
                ));

        rooms.get(RoomName.WC.name).setDoors(
                Map.of(
                        RoomName.OFFICE.name, rooms.get(RoomName.OFFICE.name)
                ));

        rooms.get(RoomName.BALCONY.name).setDoors(
                Map.of(
                        RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name)
                ));

        rooms.get(RoomName.EXIT_ROOM.name).setDoors(
                Map.of(
                        RoomName.LIVING_ROOM.name, rooms.get(RoomName.LIVING_ROOM.name)
                ));
    }

    private void initialiseWinningItems() {
        List<Item> items = Arrays.stream(Item.values()).collect(Collectors.toList());
        while (winningItems.size() < NEEDED_ITEMS) {
            int random = (int) Math.round(Math.random() * (items.size() - 1));
            winningItems.add(items.get(random));
            items.remove(random);
        }
    }

    public void start() {
        Printer.loading();
        Printer.zork_asci();

        player.setName(choosePlayerName());

        Printer.greet(player.getName());
        sleep(6000);

        System.out.println("\nDiese Items benötigst du:\n");
        sleep(1000);
        Printer.printItems(new ArrayList<>(winningItems), 1_000);

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
        while (running) {
            try {
                commands.executeCommand(parser.readWords());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        running = false;
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
