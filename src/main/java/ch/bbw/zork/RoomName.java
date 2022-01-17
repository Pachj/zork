package ch.bbw.zork;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomName {
    TAVERNE("taverne"),
    OUTSIDE("outside"),
    LAB("lab"),
    EXIT_ROOM("exit room"),
    LIVING_ROOM("living room"),
    OFFICE("office"),
    KITCHEN("kitchen"),
    BALCONY("balcony"),
    WC("wc");

    public final String name;

    RoomName(String name) {
        this.name = name;
    }

    public static RoomName fromString(String string) {
        List<RoomName> values = Arrays.stream(RoomName.values()).collect(Collectors.toList());
        List<RoomName> roomNames = values.stream().filter(it -> it.name.equals(string)).collect(Collectors.toList());
        if (roomNames.size() < 1) {
            return null;
        } else {
            return roomNames.get(0);
        }
    }
}
