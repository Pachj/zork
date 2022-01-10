package ch.bbw.zork;

import java.util.*;

public class Game {
    private final Map<RoomName, Room> rooms;
    private final Set<Item> winningItems;
    private final Map<ItemName, Item> items;
    public static final int NEEDED_ITEMS = 5;

    public Game() {
        rooms = new HashMap<>();
        winningItems = new HashSet<>();
        items = new HashMap<>();
        initialise();
    }

    private void initialise() {
        initialiseItems();
        initialiseRooms();
        initialiseWinningItems();
    }

    private void initialiseRooms() {
        List<Item> roomItems = new LinkedList<>();
        roomItems.add(items.get(ItemName.BIERKRUG));
        roomItems.add(items.get(ItemName.HOCKER));
        roomItems.add(items.get(ItemName.KAKERLAKE));
        rooms.put(RoomName.TAVERNE, new Room(RoomName.TAVERNE.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.ROSE));
        roomItems.add(items.get(ItemName.SCHMETTERLING));
        roomItems.add(items.get(ItemName.RAUPE));
        roomItems.add(items.get(ItemName.SCHAUFEL));
        rooms.put(RoomName.OUTSIDE, new Room(RoomName.OUTSIDE.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.REAGENZGLAS));
        roomItems.add(items.get(ItemName.ZAUBERTRANK));
        roomItems.add(items.get(ItemName.SKALPELL));
        roomItems.add(items.get(ItemName.APFEL));
        rooms.put(RoomName.LAB, new Room(RoomName.LAB.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.FERNSEHER));
        roomItems.add(items.get(ItemName.KRUEMEL));
        roomItems.add(items.get(ItemName.PENNY));
        roomItems.add(items.get(ItemName.MONOPOLY));
        roomItems.add(items.get(ItemName.ERDE));
        rooms.put(RoomName.LIVING_ROOM, new Room(RoomName.LIVING_ROOM.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.HANDY));
        roomItems.add(items.get(ItemName.PUTZMITTEL));
        roomItems.add(items.get(ItemName.STROMKABEL));
        roomItems.add(items.get(ItemName.SANDWICH));
        rooms.put(RoomName.OFFICE, new Room(RoomName.OFFICE.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.PALME));
        roomItems.add(items.get(ItemName.GRILL));
        roomItems.add(items.get(ItemName.FLEISCH));
        roomItems.add(items.get(ItemName.SCHLUESSEL));
        roomItems.add(items.get(ItemName.SCHROTFLINTE));
        rooms.put(RoomName.BALCONY, new Room(RoomName.BALCONY.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.WC_PAPIER));
        roomItems.add(items.get(ItemName.BUCH));
        roomItems.add(items.get(ItemName.WLAN_ROUTER));
        rooms.put(RoomName.WC, new Room(RoomName.WC.name(), roomItems));
        roomItems = new LinkedList<>();

        roomItems.add(items.get(ItemName.MESSER));
        roomItems.add(items.get(ItemName.LAUCH));
        roomItems.add(items.get(ItemName.WASSERFLASCHE));
        rooms.put(RoomName.KITCHEN, new Room(RoomName.KITCHEN.name(), roomItems));
    }

    private void initialiseItems() {
        items.put(ItemName.GRILL, new Item(20000, ItemName.GRILL.name()));
        items.put(ItemName.PALME, new Item(18000, ItemName.PALME.name()));
        items.put(ItemName.FERNSEHER, new Item(15000, ItemName.FERNSEHER.name()));
        items.put(ItemName.SCHROTFLINTE, new Item(6500, ItemName.SCHROTFLINTE.name()));
        items.put(ItemName.ERDE, new Item(6000, ItemName.ERDE.name()));
        items.put(ItemName.HOCKER, new Item(5500, ItemName.HOCKER.name()));
        items.put(ItemName.SCHAUFEL, new Item(3000, ItemName.HOCKER.name()));
        items.put(ItemName.WLAN_ROUTER, new Item(2000, ItemName.WLAN_ROUTER.name()));
        items.put(ItemName.BIERKRUG, new Item(1000, ItemName.BIERKRUG.name()));
        items.put(ItemName.PUTZMITTEL, new Item(1000, ItemName.PUTZMITTEL.name()));
        items.put(ItemName.MONOPOLY, new Item(1000, ItemName.MONOPOLY.name()));
        items.put(ItemName.BUCH, new Item(300, ItemName.BUCH.name()));
        items.put(ItemName.HANDY, new Item(150, ItemName.HANDY.name()));
        items.put(ItemName.FLEISCH, new Item(150, ItemName.FLEISCH.name()));
        items.put(ItemName.SANDWICH, new Item(150, ItemName.SANDWICH.name()));
        items.put(ItemName.APFEL, new Item(100, ItemName.APFEL.name()));
        items.put(ItemName.STROMKABEL, new Item(100, ItemName.STROMKABEL.name()));
        items.put(ItemName.ZAUBERTRANK, new Item(80, ItemName.ZAUBERTRANK.name()));
        items.put(ItemName.WC_PAPIER, new Item(80, ItemName.WC_PAPIER.name()));
        items.put(ItemName.REAGENZGLAS, new Item(80, ItemName.REAGENZGLAS.name()));
        items.put(ItemName.SCHLUESSEL, new Item(20, ItemName.SCHLUESSEL.name()));
        items.put(ItemName.SKALPELL, new Item(20, ItemName.SKALPELL.name()));
        items.put(ItemName.ROSE, new Item(20, ItemName.ROSE.name()));
        items.put(ItemName.PENNY, new Item(5, ItemName.PENNY.name()));
        items.put(ItemName.KAKERLAKE, new Item(1, ItemName.KAKERLAKE.name()));
        items.put(ItemName.RAUPE, new Item(1, ItemName.RAUPE.name()));
        items.put(ItemName.SCHMETTERLING, new Item(1, ItemName.SCHLUESSEL.name()));
        items.put(ItemName.KRUEMEL, new Item(1, ItemName.KRUEMEL.name()));
        items.put(ItemName.MESSER, new Item(300, ItemName.MESSER.name()));
        items.put(ItemName.WASSERFLASCHE, new Item(1500, ItemName.WASSERFLASCHE.name()));
        items.put(ItemName.LAUCH, new Item(100, ItemName.LAUCH.name()));
    }

    private void initialiseWinningItems() {
        List<Item> items = new ArrayList<>(this.items.values());
        while (winningItems.size() < NEEDED_ITEMS) {
            int random = (int) Math.round(Math.random() * (items.size() - 1));
            winningItems.add(items.get(random));
        }
    }

    public void start() {
        System.out.println(
                "  _                     _ _             \n" +
                " | |                   | (_)            \n" +
                " | |     ___   __ _  __| |_ _ __   __ _ \n" +
                " | |    / _ \\ / _` |/ _` | | '_ \\ / _` |\n" +
                " | |___| (_) | (_| | (_| | | | | | (_| |\n" +
                " |______\\___/ \\__,_|\\__,_|_|_| |_|\\__, |\n" +
                "                                   __/ |\n" +
                "                                  |___/ \n");
        for (int i = 0; i < 79; i++) {
            System.out.print("*");
            sleep((long) (Math.random() * 100));
        }
        System.out.println("\n\n\n");

        System.out.println(
                "ZZZZZZZZZZZZZZZZZZZ     OOOOOOOOO     RRRRRRRRRRRRRRRRR   KKKKKKKKK    KKKKKKK\n" +
                "Z:::::::::::::::::Z   OO:::::::::OO   R::::::::::::::::R  K:::::::K    K:::::K\n" +
                "Z:::::::::::::::::Z OO:::::::::::::OO R::::::RRRRRR:::::R K:::::::K    K:::::K\n" +
                "Z:::ZZZZZZZZ:::::Z O:::::::OOO:::::::ORR:::::R     R:::::RK:::::::K   K::::::K\n" +
                "ZZZZZ     Z:::::Z  O::::::O   O::::::O  R::::R     R:::::RKK::::::K  K:::::KKK\n" +
                "        Z:::::Z    O:::::O     O:::::O  R::::R     R:::::R  K:::::K K:::::K   \n" +
                "       Z:::::Z     O:::::O     O:::::O  R::::RRRRRR:::::R   K::::::K:::::K    \n" +
                "      Z:::::Z      O:::::O     O:::::O  R:::::::::::::RR    K:::::::::::K     \n" +
                "     Z:::::Z       O:::::O     O:::::O  R::::RRRRRR:::::R   K:::::::::::K     \n" +
                "    Z:::::Z        O:::::O     O:::::O  R::::R     R:::::R  K::::::K:::::K    \n" +
                "   Z:::::Z         O:::::O     O:::::O  R::::R     R:::::R  K:::::K K:::::K   \n" +
                "ZZZ:::::Z     ZZZZZO::::::O   O::::::O  R::::R     R:::::RKK::::::K  K:::::KKK\n" +
                "Z::::::ZZZZZZZZ:::ZO:::::::OOO:::::::ORR:::::R     R:::::RK:::::::K   K::::::K\n" +
                "Z:::::::::::::::::Z OO:::::::::::::OO R::::::R     R:::::RK:::::::K    K:::::K\n" +
                "Z:::::::::::::::::Z   OO:::::::::OO   R::::::R     R:::::RK:::::::K    K:::::K\n" +
                "ZZZZZZZZZZZZZZZZZZZ     OOOOOOOOO     RRRRRRRR     RRRRRRRKKKKKKKKK    KKKKKKK\n" +
                "                                                                              \n");

        System.out.println("Hallo Neuling\n");
        sleep(1000);
        System.out.println("Wie du bald erfahren wirst, bist du in unserem Labyrint gefangen\n" +
                "Jedoch geben wird dir die Chance zu entflien. Dafür musst du blos einige\n" +
                "Items in den exit Room bringen.");
        sleep(1000);
        System.out.println("\n" +
                "");
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
