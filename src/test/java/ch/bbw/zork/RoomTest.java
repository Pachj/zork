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
        Item item = new Item(10, "Item");
        List<Item> itemList = new LinkedList<>();
        itemList.add(item);
        Room room = new Room("Room", itemList);

        assertThat(room.getItems().size()).isEqualTo(1);

        room.removeItem("Item");

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
        Item itemOne = new Item(10, "ItemOne");
        Item itemTwo = new Item(10, "ItemTwo");
        List<Item> itemList = new LinkedList<>();
        itemList.add(itemOne);
        itemList.add(itemTwo);
        List<Item> emptyList = new LinkedList<>();
        Room room = new Room("Room", emptyList);

        assertThat(room.getItems().size()).isZero();

        room.addItems(itemList);

        assertThat(room.getItems().size()).isEqualTo(2);
    }

    @Test
    public void shouldAddListOfItemsToExistingList() {
        Item itemOne = new Item(10, "ItemOne");
        Item itemTwo = new Item(10, "ItemTwo");
        List<Item> currentList = new LinkedList<>();
        currentList.add(itemOne);
        currentList.add(itemTwo);

        Item itemThree = new Item(10, "ItemThree");
        Item itemFour = new Item(10, "ItemFour");
        List<Item> addedList = new LinkedList<>();
        addedList.add(itemThree);
        addedList.add(itemFour);

        Room room = new Room("Room", currentList);

        assertThat(room.getItems().size()).isEqualTo(2);

        room.addItems(addedList);

        assertThat(room.getItems().size()).isEqualTo(4);
        assertThat(room.getItems().get(3)).isEqualTo(itemFour);
    }

    //Tests for method addItem()
    @Test
    public void shouldAddItemToEmptyList() {
        Item itemOne = new Item(10, "ItemOne");
        List<Item> emptyList = new LinkedList<>();
        Room room = new Room("Room", emptyList);

        assertThat(room.getItems().size()).isZero();

        room.addItem(itemOne);

        assertThat(room.getItems().size()).isEqualTo(1);
    }

    @Test
    public void shouldAddItemToExistingList() {
        Item itemOne = new Item(10, "ItemOne");
        Item itemTwo = new Item(10, "ItemTwo");
        List<Item> currentList = new LinkedList<>();
        currentList.add(itemOne);
        currentList.add(itemTwo);
        Item itemThree = new Item(10, "ItemThree");

        Room room = new Room("Room", currentList);

        assertThat(room.getItems().size()).isEqualTo(2);

        room.addItem(itemThree);

        assertThat(room.getItems().size()).isEqualTo(3);
        assertThat(room.getItems().get(2)).isEqualTo(itemThree);
    }
}
