package ch.bbw.zork;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BackpackTest {

    private final Backpack backpack = new Backpack();

    // Tests for method addItem()
    @Test
    public void shouldAddItemToBackpack() {
        Item item = Item.BUCH;
        backpack.addItem(item);

        assertThat(item).isEqualTo(backpack.getItems().get(0));
    }

    // Tests for method removeItem()
    @Test
    public void shouldRemoveItemFromBackpackWithOneItem() {
        Item item = Item.BUCH;
        backpack.addItem(item);

        assertThat(backpack.getItems().size()).isEqualTo(1);

        backpack.removeItem(item.name);

        assertThat(backpack.getItems().size()).isEqualTo(0);
    }

    @Test
    public void shouldRemoveItemFromBackpackWithMultipleItems() {
        Item book = Item.BUCH;
        backpack.addItem(book);
        Item apple = Item.APFEL;
        backpack.addItem(apple);

        assertThat(backpack.getItems().size()).isEqualTo(2);

        backpack.removeItem(apple.name);

        assertThat(backpack.getItems().size()).isEqualTo(1);
        assertThat(backpack.getItems().get(0)).isEqualTo(book);
    }

    @Test
    public void shouldRemoveNothingIfItemDoesNotExist() {
        Item book = Item.BUCH;
        backpack.addItem(book);

        assertThat(backpack.getItems().size()).isEqualTo(1);

        backpack.removeItem("Failure");

        assertThat(backpack.getItems().size()).isEqualTo(1);
    }

    @Test
    public void shouldCheckIfWeightHasBeenAdjusted() {
        Item book = Item.BUCH;
        backpack.addItem(book);

        assertThat(backpack.getRemainingCapacity()).isEqualTo(Backpack.MAX_WEIGHT_G - book.weight);

        backpack.removeItem(book.name);

        assertThat(backpack.getRemainingCapacity()).isEqualTo(Backpack.MAX_WEIGHT_G);
    }

    // Tests for method clear()
    @Test
    public void shouldClearBackpackWithMultipleItems() {
        Item book = Item.BUCH;
        backpack.addItem(book);
        Item apple = Item.APFEL;
        backpack.addItem(apple);

        assertThat(backpack.getItems().size()).isEqualTo(2);

        backpack.clear();

        assertThat(backpack.getItems()).isEmpty();
    }

    @Test
    public void shouldClearEmptyBackpack() {
        backpack.clear();

        assertThat(backpack.getItems()).isEmpty();
        assertThat(backpack.getRemainingCapacity()).isEqualTo(Backpack.MAX_WEIGHT_G);
    }

    @Test
    public void shouldCheckIfWeightHasBeenReset() {
        Item book = Item.BUCH;
        backpack.addItem(book);

        assertThat(backpack.getRemainingCapacity()).isEqualTo(Backpack.MAX_WEIGHT_G - book.weight);

        backpack.clear();

        assertThat(backpack.getRemainingCapacity()).isEqualTo(Backpack.MAX_WEIGHT_G);
    }
}
