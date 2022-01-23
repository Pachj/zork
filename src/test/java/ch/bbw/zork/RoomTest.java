package ch.bbw.zork;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class RoomTest {

    //Tests for method removeItem()
    @Test
    public void shouldRemoveItemFromRoomWithOneItem() {
        Item book = Item.BUCH;
        List<Item> itemList = new LinkedList<>();
        itemList.add(book);
        Room room = new Room("Room", itemList);

        assertThat(room.getItems().size()).isEqualTo(1);

        room.removeItem(book.name);

        assertThat(room.getItems().size()).isZero();
    }

    @Test
    public void shouldDoNothingIfRoomIsEmpty() {
        List<Item> emptyList = new LinkedList<>();
        Room room = new Room("Room", emptyList);

        room.removeItem("Item");

        assertThatCode(() -> room.removeItem("Item"))
                .doesNotThrowAnyException();
    }

    //Tests for method addItems()
    @Test
    public void shouldAddListOfItemsToEmptyList() {
        Item book = Item.BUCH;
        Item apple = Item.APFEL;
        List<Item> itemList = new LinkedList<>();
        itemList.add(book);
        itemList.add(apple);
        List<Item> emptyList = new LinkedList<>();
        Room room = new Room("Room", emptyList);

        assertThat(room.getItems().size()).isZero();

        room.addItems(itemList);

        assertThat(room.getItems().size()).isEqualTo(2);
    }

    @Test
    public void shouldAddListOfItemsToExistingList() {
        Item book = Item.BUCH;
        Item apple = Item.APFEL;
        List<Item> currentList = new LinkedList<>();
        currentList.add(book);
        currentList.add(apple);

        Item bierkrug = Item.BIERKRUG;
        Item soil = Item.ERDE;
        List<Item> addedList = new LinkedList<>();
        addedList.add(bierkrug);
        addedList.add(soil);

        Room room = new Room("Room", currentList);

        assertThat(room.getItems().size()).isEqualTo(2);

        room.addItems(addedList);

        assertThat(room.getItems().size()).isEqualTo(4);
        assertThat(room.getItems().get(3)).isEqualTo(soil);
    }

    //Tests for method addItem()
    @Test
    public void shouldAddItemToEmptyList() {
        Item book = Item.BUCH;
        List<Item> emptyList = new LinkedList<>();
        Room room = new Room("Room", emptyList);

        assertThat(room.getItems().size()).isZero();

        room.addItem(book);

        assertThat(room.getItems().size()).isEqualTo(1);
    }

    @Test
    public void shouldAddItemToExistingList() {
        Item book = Item.BUCH;
        Item apple = Item.APFEL;
        List<Item> currentList = new LinkedList<>();
        currentList.add(book);
        currentList.add(apple);
        Item soil = Item.ERDE;

        Room room = new Room("Room", currentList);

        assertThat(room.getItems().size()).isEqualTo(2);

        room.addItem(soil);

        assertThat(room.getItems().size()).isEqualTo(3);
        assertThat(room.getItems().get(2)).isEqualTo(soil);
    }
}
