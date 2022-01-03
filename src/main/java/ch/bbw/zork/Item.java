package ch.bbw.zork;

public class Item {
    private final int weight;
    private final String name;

    public Item(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
