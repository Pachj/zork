package ch.bbw.zork;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BackpackTest {

    private final Backpack backpack = new Backpack();

    // Tests for method addItem()
    @Test
    public void shouldAddItemToBackpack() {
        Item item = new Item(10, "Item");
        backpack.addItem(item);

        assertThat(item).isEqualTo(backpack.getItems().get(0));
    }

    // Tests for method removeItem()
    @Test
    public void shouldRemoveItemFromBackpackWithOneItem() {
        Item item = new Item(10, "Item");
        backpack.addItem(item);

        assertThat(backpack.getItems().size()).isEqualTo(1);

        backpack.removeItem("Item");

        assertThat(backpack.getItems().size()).isEqualTo(0);
    }

    @Test
    public void shouldRemoveItemFromBackpackWithMultipleItems() {
        Item itemOne = new Item(10, "ItemOne");
        backpack.addItem(itemOne);
        Item itemTwo = new Item(20, "ItemTwo");
        backpack.addItem(itemTwo);

        assertThat(backpack.getItems().size()).isEqualTo(2);

        backpack.removeItem("ItemTwo");

        assertThat(backpack.getItems().size()).isEqualTo(1);
        assertThat(backpack.getItems().get(0)).isEqualTo(itemOne);
    }

    @Test
    public void shouldRemoveNothingIfItemDoesNotExist() {
        Item itemOne = new Item(10, "Item");
        backpack.addItem(itemOne);

        assertThat(backpack.getItems().size()).isEqualTo(1);

        backpack.removeItem("Failure");

        assertThat(backpack.getItems().size()).isEqualTo(1);
    }

    @Test
    public void shouldCheckIfWeightHasBeenAdjusted() {
        Item itemOne = new Item(10, "Item");
        backpack.addItem(itemOne);

        assertThat(backpack.getCurrentWeight()).isEqualTo(itemOne.getWeight());

        backpack.removeItem(itemOne.getName());

        assertThat(backpack.getCurrentWeight()).isEqualTo(0);
    }

    // Tests for method clear()
    @Test
    public void shouldClearBackpackWithMultipleItems() {
        Item itemOne = new Item(10, "ItemOne");
        backpack.addItem(itemOne);
        Item itemTwo = new Item(20, "ItemTwo");
        backpack.addItem(itemTwo);

        assertThat(backpack.getItems().size()).isEqualTo(2);

        backpack.clear();

        assertThat(backpack.getItems()).isEmpty();
    }

    @Test
    public void shouldClearEmptyBackpack() {
        backpack.clear();

        assertThat(backpack.getItems()).isEmpty();
        assertThat(backpack.getCurrentWeight()).isEqualTo(0);
    }

    @Test
    public void shouldCheckIfWeightHasBeenReset() {
        Item itemOne = new Item(10, "Item");
        backpack.addItem(itemOne);

        assertThat(backpack.getCurrentWeight()).isEqualTo(itemOne.getWeight());

        backpack.clear();

        assertThat(backpack.getCurrentWeight()).isEqualTo(0);
    }
}
