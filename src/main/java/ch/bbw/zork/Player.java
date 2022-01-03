package ch.bbw.zork;

public class Player {
    private final Backpack backpack;
    private final String name;

    public Player(String name) {
        backpack = new Backpack();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Backpack getBackpack() {
        return backpack;
    }
}
