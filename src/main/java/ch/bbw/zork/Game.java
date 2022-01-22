package ch.bbw.zork;

import ch.bbw.zork.reader.Commands;
import ch.bbw.zork.reader.Parser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class Game {
    private final Map<String, Room> rooms;
    private final Set<Item> winningItems;
    private Player player;
    private final Commands commands;
    private final Parser parser;
    public static final int NEEDED_ITEMS = 5;

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

    private String choosePlayerName() {
        Parser parser = new Parser();
        String name = "";
        System.out.println("Gib deinen Namen ein: ");
        try {
            name = parser.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ein Fehler ist aufgetreten! Versuche es noch einmal.");
            choosePlayerName();
        }
        if (name.isEmpty()) {
            name = "Neuling";
        }
        return name;
    }

    private void initialise() {
        initialiseRooms();
        initialiseDoors();
        initialiseWinningItems();
    }

    private void initialiseRooms() {
        rooms.put(RoomName.TAVERNE.name, new Room(RoomName.TAVERNE.name,
                List.of(Item.BIERKRUG,
                        Item.HOCKER,
                        Item.KAKERLAKE)));


        rooms.put(RoomName.OUTSIDE.name, new Room(RoomName.OUTSIDE.name,
                List.of(Item.ROSE,
                        Item.SCHMETTERLING,
                        Item.RAUPE,
                        Item.SCHAUFEL)));


        rooms.put(RoomName.LAB.name, new Room(RoomName.LAB.name,
                List.of(Item.REAGENZGLAS,
                        Item.ZAUBERTRANK,
                        Item.SKALPELL,
                        Item.APFEL)));


        rooms.put(RoomName.LIVING_ROOM.name, new Room(RoomName.LIVING_ROOM.name,
                List.of(Item.FERNSEHER,
                        Item.KRUEMEL,
                        Item.PENNY,
                        Item.MONOPOLY,
                        Item.ERDE)));


        rooms.put(RoomName.OFFICE.name, new Room(RoomName.OFFICE.name,
                List.of(Item.HANDY,
                        Item.PUTZMITTEL,
                        Item.STROMKABEL,
                        Item.SANDWICH)));


        rooms.put(RoomName.BALCONY.name, new Room(RoomName.BALCONY.name,
                List.of(Item.PALME,
                        Item.GRILL,
                        Item.FLEISCH,
                        Item.SCHLUESSEL,
                        Item.SCHROTFLINTE)));


        rooms.put(RoomName.WC.name, new Room(RoomName.WC.name,
                List.of(Item.WC_PAPIER,
                        Item.BUCH,
                        Item.WLAN_ROUTER)));


        rooms.put(RoomName.KITCHEN.name, new Room(RoomName.KITCHEN.name,
                List.of(Item.MESSER,
                        Item.LAUCH,
                        Item.WASSERFLASCHE)));

        rooms.put(RoomName.EXIT_ROOM.name, new Room(RoomName.EXIT_ROOM.name,
                List.of()));
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
        Printer.printItemsDelayed(new ArrayList<>(winningItems));

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
                commands.executeCommand(parser.readWords());
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
