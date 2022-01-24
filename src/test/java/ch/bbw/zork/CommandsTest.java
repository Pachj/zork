package ch.bbw.zork;

import ch.bbw.zork.reader.Commands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    // Tests for method checkGameWon()
    @Test
    public void shouldCheckIfPlayerWon() {
        Commands commands = new Commands(game);
        String[] command = {"drop", "--all"};

        System.out.println(game.getPlayer().getCurrentRoom().name);

        List<Item> items = game.getWinningItems().stream().collect(Collectors.toList());

        items.forEach(it -> {
            game.getPlayer().getBackpack().addItem(it);
            commands.executeCommand(command);
        });

        String won =
                "                                                                                                                                                       \n" +
                        "                                                                                                                                                       \n" +
                        "        CCCCCCCCCCCCC                                                                                                   tttt                           \n" +
                        "     CCC::::::::::::C                                                                                                ttt:::t                           \n" +
                        "   CC:::::::::::::::C                                                                                                t:::::t                           \n" +
                        "  C:::::CCCCCCCC::::C                                                                                                t:::::t                           \n" +
                        " C:::::C       CCCCCC   ooooooooooo   nnnn  nnnnnnnn       ggggggggg   gggggrrrrr   rrrrrrrrr   aaaaaaaaaaaaa  ttttttt:::::ttttttt        ssssssssss   \n" +
                        "C:::::C               oo:::::::::::oo n:::nn::::::::nn    g:::::::::ggg::::gr::::rrr:::::::::r  a::::::::::::a t:::::::::::::::::t      ss::::::::::s  \n" +
                        "C:::::C              o:::::::::::::::on::::::::::::::nn  g:::::::::::::::::gr:::::::::::::::::r aaaaaaaaa:::::at:::::::::::::::::t    ss:::::::::::::s \n" +
                        "C:::::C              o:::::ooooo:::::onn:::::::::::::::ng::::::ggggg::::::ggrr::::::rrrrr::::::r         a::::atttttt:::::::tttttt    s::::::ssss:::::s\n" +
                        "C:::::C              o::::o     o::::o  n:::::nnnn:::::ng:::::g     g:::::g  r:::::r     r:::::r  aaaaaaa:::::a      t:::::t           s:::::s  ssssss \n" +
                        "C:::::C              o::::o     o::::o  n::::n    n::::ng:::::g     g:::::g  r:::::r     rrrrrrraa::::::::::::a      t:::::t             s::::::s      \n" +
                        "C:::::C              o::::o     o::::o  n::::n    n::::ng:::::g     g:::::g  r:::::r           a::::aaaa::::::a      t:::::t                s::::::s   \n" +
                        " C:::::C       CCCCCCo::::o     o::::o  n::::n    n::::ng::::::g    g:::::g  r:::::r          a::::a    a:::::a      t:::::t    ttttttssssss   s:::::s \n" +
                        "  C:::::CCCCCCCC::::Co:::::ooooo:::::o  n::::n    n::::ng:::::::ggggg:::::g  r:::::r          a::::a    a:::::a      t::::::tttt:::::ts:::::ssss::::::s\n" +
                        "   CC:::::::::::::::Co:::::::::::::::o  n::::n    n::::n g::::::::::::::::g  r:::::r          a:::::aaaa::::::a      tt::::::::::::::ts::::::::::::::s \n" +
                        "     CCC::::::::::::C oo:::::::::::oo   n::::n    n::::n  gg::::::::::::::g  r:::::r           a::::::::::aa:::a       tt:::::::::::tt s:::::::::::ss  \n" +
                        "        CCCCCCCCCCCCC   ooooooooooo     nnnnnn    nnnnnn    gggggggg::::::g  rrrrrrr            aaaaaaaaaa  aaaa         ttttttttttt    sssssssssss    \n" +
                        "                                                                    g:::::g                                                                            \n" +
                        "                                                        gggggg      g:::::g                                                                            \n" +
                        "                                                        g:::::gg   gg:::::g                                                                            \n" +
                        "                                                         g::::::ggg:::::::g                                                                            \n" +
                        "                                                          gg:::::::::::::g                                                                             \n" +
                        "                                                            ggg::::::ggg                                                                               \n" +
                        "                                                               gggggg                                                                                  \n" +
                        "                                                                                                                                                       \n" +
                        "                                                                                                                                                       \n" +
                        "                                                                                                                                                       \n" +
                        "yyyyyyy           yyyyyyy ooooooooooo   uuuuuu    uuuuuu       wwwwwww           wwwww           wwwwwww ooooooooooo   nnnn  nnnnnnnn                  \n" +
                        " y:::::y         y:::::yoo:::::::::::oo u::::u    u::::u        w:::::w         w:::::w         w:::::woo:::::::::::oo n:::nn::::::::nn                \n" +
                        "  y:::::y       y:::::yo:::::::::::::::ou::::u    u::::u         w:::::w       w:::::::w       w:::::wo:::::::::::::::on::::::::::::::nn               \n" +
                        "   y:::::y     y:::::y o:::::ooooo:::::ou::::u    u::::u          w:::::w     w:::::::::w     w:::::w o:::::ooooo:::::onn:::::::::::::::n              \n" +
                        "    y:::::y   y:::::y  o::::o     o::::ou::::u    u::::u           w:::::w   w:::::w:::::w   w:::::w  o::::o     o::::o  n:::::nnnn:::::n              \n" +
                        "     y:::::y y:::::y   o::::o     o::::ou::::u    u::::u            w:::::w w:::::w w:::::w w:::::w   o::::o     o::::o  n::::n    n::::n              \n" +
                        "      y:::::y:::::y    o::::o     o::::ou::::u    u::::u             w:::::w:::::w   w:::::w:::::w    o::::o     o::::o  n::::n    n::::n              \n" +
                        "       y:::::::::y     o::::o     o::::ou:::::uuuu:::::u              w:::::::::w     w:::::::::w     o::::o     o::::o  n::::n    n::::n              \n" +
                        "        y:::::::y      o:::::ooooo:::::ou:::::::::::::::uu             w:::::::w       w:::::::w      o:::::ooooo:::::o  n::::n    n::::n              \n" +
                        "         y:::::y       o:::::::::::::::o u:::::::::::::::u              w:::::w         w:::::w       o:::::::::::::::o  n::::n    n::::n              \n" +
                        "        y:::::y         oo:::::::::::oo   uu::::::::uu:::u               w:::w           w:::w         oo:::::::::::oo   n::::n    n::::n              \n" +
                        "       y:::::y            ooooooooooo       uuuuuuuu  uuuu                www             www            ooooooooooo     nnnnnn    nnnnnn              \n" +
                        "      y:::::y                                                                                                                                          \n" +
                        "     y:::::y                                                                                                                                           \n" +
                        "    y:::::y                                                                                                                                            \n" +
                        "   y:::::y                                                                                                                                             \n" +
                        "  yyyyyyy                                                                                                                                              \n" +
                        "                                                                                                                                                       \n" +
                        "                                                                                                                                                       ";

        assertThat(outContent.toString()).contains(won);
    }

    // Tests for method commandGrab()
    @Test
    public void shouldGrabItem() {
        Commands commands = new Commands(game);
        String[] command = {"grab", Item.KRUEMEL.name};

        game.getPlayer().setCurrentRoom(RoomName.LIVING_ROOM);

        commands.executeCommand(command);

        List<Item> items = game.getPlayer().getBackpack().getItems();
        assertThat(items.get(0).name).isEqualTo(Item.KRUEMEL.name);
    }

    @Test
    public void shouldPrintWarningIfItemDoesNotExist() {
        Commands commands = new Commands(game);
        String nonexistentItem = "NonexistentItem";
        String[] command = {"grab", nonexistentItem};

        game.getPlayer().setCurrentRoom(RoomName.LIVING_ROOM);

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .isEqualToIgnoringNewLines("Das Item " + nonexistentItem + " existiert nicht in diesem Raum");
    }

    // Tests for method commandBack()
    @Test
    public void shouldGoOneRoomBack() {
        Commands commands = new Commands(game);
        String[] commandGo = {"go", RoomName.LIVING_ROOM.name};
        String[] commandBack = {"back"};

        commands.executeCommand(commandGo);
        assertThat(game.getPlayer().getCurrentRoom().name).isEqualTo(RoomName.LIVING_ROOM.name);

        commands.executeCommand(commandBack);
        assertThat(game.getPlayer().getCurrentRoom().name).isEqualTo(RoomName.EXIT_ROOM.name);
    }

    @Test
    public void shouldNotGoBackIfYouAreInExitRoom() {
        Commands commands = new Commands(game);
        String[] command = {"back"};

        commands.executeCommand(command);

        assertThat(outContent.toString())
                .isEqualToIgnoringNewLines("Du befindest dich im Startraum und kannst deshalb nicht zurück.");
    }

}

