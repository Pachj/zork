package ch.bbw.zork;

import java.util.LinkedList;
import java.util.List;

public class Backpack {
    public static final int MAX_WEIGHT_G = 20_000;
    private final List<Item> items;
    private int currentWeight;

    public Backpack() {
        this.items = new LinkedList<>();
        this.currentWeight = 0;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (currentWeight + item.getWeight() > Backpack.MAX_WEIGHT_G) {
            System.out.println("Das Item " + item.getName() + " konnte nicht aufgesammelt werden, da der Rucksack das maximale Gewicht von " + Backpack.MAX_WEIGHT_G / 1_000 + " Kg überschritten hat.");
        } else {
            items.add(item);
            currentWeight += item.getWeight();
            System.out.println("Das Item " + item.getName() + " wurde aufgesammelt. Es können noch " + (Backpack.MAX_WEIGHT_G - currentWeight) / 1_000 + " Kg aufgenommen werden.");
        }
    }

    public void removeItem(String name) {
        items.forEach(item -> {
            if (item.getName().equals(name)) {
                currentWeight -= item.getWeight();
                items.remove(item);
            }
        });
    }

    public void clear() {
        items.clear();
        currentWeight = 0;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
}
