package ch.bbw.zork;

public enum RoomName {
    TAVERNE("Taverne"),
    OUTSIDE("Outside"),
    LAB("Lab"),
    EXIT_ROOM("Exit room"),
    LIVING_ROOM("Living room"),
    OFFICE("Office"),
    KITCHEN("Kitchen"),
    BALCONY("Balcony"),
    WC("Wc");

    private String name;

    RoomName(String name) {
        this.name = name;
    }
}
