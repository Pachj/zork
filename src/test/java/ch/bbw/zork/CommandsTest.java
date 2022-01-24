package ch.bbw.zork;

import ch.bbw.zork.reader.Commands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandsTest {

    private Game game;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        game = new Game();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Tests for method commandGo()
    @Test
    public void shouldGoToOtherRoom() {
        Commands commands = new Commands(game);
        String[] command = {"go", "living", "room"};

        assertThat(game.getPlayer().getCurrentRoom())
                .isEqualTo(RoomName.EXIT_ROOM);

        commands.executeCommand(command);

        assertThat(game.getPlayer().getCurrentRoom())
                .isEqualTo(RoomName.LIVING_ROOM);
    }

    @Test
    public void shouldPrintWarningIfRoomDoesNotExist() {
        Commands commands = new Commands(game);
        String[] command = {"go", "unexisting", "room"};


        commands.executeCommand(command);

        assertThat(outContent.toString())
                .isEqualToIgnoringNewLines("Dieser Raum existiert nicht oder grenzt nicht an diesen Raum an!");
    }

    @Test
    public void shouldPrintWarningIfRoomIsMissing() {
        Commands commands = new Commands(game);
        String[] command = {"go"};

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .isEqualToIgnoringNewLines("Bitte gebe einen Raum an!");
    }

    // Tests for method commandShow()
    @Test
    public void shouldPrintNearbyRooms() {
        Commands commands = new Commands(game);
        String[] command = {"show", "exits"};

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .isEqualToIgnoringNewLines(RoomName.LIVING_ROOM.name);
    }

    @Test
    public void shouldPrintWinningItems() {
        Commands commands = new Commands(game);
        String[] command = {"show", "items", "--winning"};
        Set<Item> winningItems = game.getWinningItems();
        Object[] ItemArray = winningItems.toArray();

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .containsAnyOf(ItemArray[0].toString().toLowerCase(),
                        ItemArray[1].toString().toLowerCase(),
                        ItemArray[2].toString().toLowerCase(),
                        ItemArray[3].toString().toLowerCase(),
                        ItemArray[4].toString().toLowerCase());
    }

    // TODO
    @Test
    public void shouldCheckIfAllItemsArePrinted() {
        Commands commands = new Commands(game);
        String[] command = {"show", "items", "--global"};

        Item[] values = Item.values();


        commands.executeCommand(command);

        assertThat(outContent.toByteArray().length)
                .isEqualTo(values.length);
    }

    @Test
    public void shouldPrintWarningIfParameterIsMissing() {
        Commands commands = new Commands(game);
        String[] command = {"show"};

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .isEqualToIgnoringNewLines("Bitte gebe einen Parameter an!");
    }

    @Test
    public void shouldIgnoreUnnecessaryParameter() {
        Commands commands = new Commands(game);
        String[] command = {"show", "items", "--winning", "parameter"};

        Set<Item> winningItems = game.getWinningItems();
        Object[] ItemArray = winningItems.toArray();

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .containsAnyOf(ItemArray[0].toString().toLowerCase(),
                        ItemArray[1].toString().toLowerCase(),
                        ItemArray[2].toString().toLowerCase(),
                        ItemArray[3].toString().toLowerCase(),
                        ItemArray[4].toString().toLowerCase());
    }

    //Tests for method commandShowBackpack()
    @Test
    public void shouldPrintItemsFromBackpack() {
        Commands commands = new Commands(game);
        String[] command = {"show", "backpack"};

        game.getPlayer().getBackpack().addItem(Item.BUCH);
        game.getPlayer().getBackpack().addItem(Item.APFEL);
        String remainingCapacity = String.valueOf(game.getPlayer().getBackpack().getRemainingCapacity());

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .containsAnyOf(Item.BUCH.name(), Item.APFEL.name, remainingCapacity);
    }

    //Test for method commandDrop()
    //TODO
    @Test
    public void shouldCheckIfAllItemsAreDropped() {
        Commands commands = new Commands(game);
        String[] command = {"drop", "--all"};

        game.getPlayer().getBackpack().addItem(Item.BUCH);
        game.getPlayer().getBackpack().addItem(Item.APFEL);

        commands.executeCommand(command);

        assertThat(game.getPlayer().getBackpack().getRemainingCapacity())
                .isEqualTo(Backpack.MAX_WEIGHT_G);
        assertThat(game.getPlayer().getBackpack().getItems()).isEmpty();
    }

    @Test
    public void shouldCheckIfSingleItemIsDropped() {
        Commands commands = new Commands(game);
        String[] command = {"drop", Item.BUCH.name};

        game.getPlayer().getBackpack().addItem(Item.BUCH);

        commands.executeCommand(command);

        assertThat(game.getPlayer().getBackpack().getRemainingCapacity())
                .isEqualTo(Backpack.MAX_WEIGHT_G);
        assertThat(game.getPlayer().getBackpack().getItems()).isEmpty();
    }


}

