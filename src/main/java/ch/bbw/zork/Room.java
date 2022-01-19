package ch.bbw.zork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
    private final String name;
    private List<Item> items;
    private Map<String, Room> doors;

    public Room(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(String itemName) {
        items = items.stream().filter(it -> !it.name.equals(itemName)).collect(Collectors.toList());
    }

    public void addItems(List<Item> items) {
        this.items.addAll(items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Map<String, Room> getDoors() {
        return doors;
    }

    public void setDoors(Map<String, Room> doors) {
        this.doors = doors;
    }
}
