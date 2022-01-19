package ch.bbw.zork;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Backpack {
    public static final int MAX_WEIGHT_G = 20_000;
    private List<Item> items;
    private int currentWeight;

    public Backpack() {
        this.items = new LinkedList<>();
        this.currentWeight = 0;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean addItem(Item item) {
        if (currentWeight + item.weight > Backpack.MAX_WEIGHT_G) {
            System.out.println("Das Item " + item.name + " konnte nicht aufgesammelt werden, da der Rucksack das maximale Gewicht von " + Backpack.MAX_WEIGHT_G / 1_000 + " Kg überschritten hat.");
            return false;
        } else {
            items.add(item);
            currentWeight += item.weight;
            System.out.println("Das Item " + item.name + " wurde aufgesammelt. Es können noch " + (Backpack.MAX_WEIGHT_G - currentWeight) / 1_000 + " Kg aufgenommen werden.");
            return true;
        }
    }

    public void removeItem(String name) {
        items = items.stream().filter(it -> {
            if (it.name.equals(name)) {
                currentWeight -= it.weight;
                return false;
            } else {
                return true;
            }
        }).collect(Collectors.toList());
    }

    public void clear() {
        items.clear();
        currentWeight = 0;
    }

    private int getCurrentWeight() {
        return currentWeight;
    }

    public int getRemainingCapacity() {
        return Backpack.MAX_WEIGHT_G - getCurrentWeight();
    }
}
