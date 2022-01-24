package ch.bbw.zork;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Tests for method loading()
    @Test
    public void shouldCheckLoadingPrintContent() {

        Printer.loading();

        String text =
                "  _                     _ _             \n" +
                        " | |                   | (_)            \n" +
                        " | |     ___   __ _  __| |_ _ __   __ _ \n" +
                        " | |    / _ \\ / _` |/ _` | | '_ \\ / _` |\n" +
                        " | |___| (_) | (_| | (_| | | | | | (_| |\n" +
                        " |______\\___/ \\__,_|\\__,_|_|_| |_|\\__, |\n" +
                        "                                   __/ |\n" +
                        "                                  |___/ \n\n" +
                        "*******************************************************************************\n\n\n\n\n";

        assertThat(outContent.toString()).isEqualToIgnoringNewLines(text);

    }

    //Tests for method zork_asci()
    @Test
    public void shouldCheckZorkPrintContent() {

        Printer.zork_asci();

        String text =
                "ZZZZZZZZZZZZZZZZZZZ     OOOOOOOOO     RRRRRRRRRRRRRRRRR   KKKKKKKKK    KKKKKKK\n" +
                        "Z:::::::::::::::::Z   OO:::::::::OO   R::::::::::::::::R  K:::::::K    K:::::K\n" +
                        "Z:::::::::::::::::Z OO:::::::::::::OO R::::::RRRRRR:::::R K:::::::K    K:::::K\n" +
                        "Z:::ZZZZZZZZ:::::Z O:::::::OOO:::::::ORR:::::R     R:::::RK:::::::K   K::::::K\n" +
                        "ZZZZZ     Z:::::Z  O::::::O   O::::::O  R::::R     R:::::RKK::::::K  K:::::KKK\n" +
                        "        Z:::::Z    O:::::O     O:::::O  R::::R     R:::::R  K:::::K K:::::K   \n" +
                        "       Z:::::Z     O:::::O     O:::::O  R::::RRRRRR:::::R   K::::::K:::::K    \n" +
                        "      Z:::::Z      O:::::O     O:::::O  R:::::::::::::RR    K:::::::::::K     \n" +
                        "     Z:::::Z       O:::::O     O:::::O  R::::RRRRRR:::::R   K:::::::::::K     \n" +
                        "    Z:::::Z        O:::::O     O:::::O  R::::R     R:::::R  K::::::K:::::K    \n" +
                        "   Z:::::Z         O:::::O     O:::::O  R::::R     R:::::R  K:::::K K:::::K   \n" +
                        "ZZZ:::::Z     ZZZZZO::::::O   O::::::O  R::::R     R:::::RKK::::::K  K:::::KKK\n" +
                        "Z::::::ZZZZZZZZ:::ZO:::::::OOO:::::::ORR:::::R     R:::::RK:::::::K   K::::::K\n" +
                        "Z:::::::::::::::::Z OO:::::::::::::OO R::::::R     R:::::RK:::::::K    K:::::K\n" +
                        "Z:::::::::::::::::Z   OO:::::::::OO   R::::::R     R:::::RK:::::::K    K:::::K\n" +
                        "ZZZZZZZZZZZZZZZZZZZ     OOOOOOOOO     RRRRRRRR     RRRRRRRKKKKKKKKK    KKKKKKK\n" +
                        "                                                                              \n";

        assertThat(outContent.toString()).isEqualToIgnoringNewLines(text);

    }

    //Tests for method greet()
    @Test
    public void shouldCheckGreetingContent() {

        String name = "Name";
        Printer.greet(name);

        String text =
                "Hallo " + name + "\n" +
                        "Wie du bald erfahren wirst, bist du in unserem Labyrinth gefangen. Jedoch geben \n" +
                        "wir dir die Chance zu fliehen. Dafür musst du nur einige Items in den Exit Room bringen.";

        assertThat(outContent.toString()).isEqualToIgnoringNewLines(text);

    }

    // Tests for method printItems()
    @Test
    public void shouldCheckIfItemsWillBePrinted() {

        Item book = Item.BUCH;
        Item apple = Item.APFEL;
        List<Item> itemList = new LinkedList<>();
        itemList.add(book);
        itemList.add(apple);

        Printer.printItems(itemList, 0);
        assertThat(outContent.toString())
                .contains(book.name, apple.name);

    }

    //Tests for method go()
    @Test
    public void shouldCheckIfGoPrintIsNotEmpty() {

        Printer.go();

        assertThat(outContent.toString()).isNotEmpty();
    }

    //Tests for method show()
    @Test
    public void shouldCheckIfShowPrintIsNotEmpty() {

        Printer.show();

        assertThat(outContent.toString()).isNotEmpty();
    }

    // Tests for method drop_grab()
    @Test
    public void shouldCheckIfDropGrabPrintIsNotEmpty() {

        Printer.drop_grab();

        assertThat(outContent.toString()).isNotEmpty();
    }

    //Tests for method back()
    @Test
    public void shouldCheckIfBackPrintIsNotEmpty() {

        Printer.back();

        assertThat(outContent.toString()).isNotEmpty();
    }

    //Tests for method map()
    @Test
    public void shouldCheckIfMapPrintIsNotEmpty() {

        Printer.map();

        assertThat(outContent.toString()).isNotEmpty();
    }

    //Tests for method help()
    @Test
    public void shouldCheckIfHelpPrintIsNotEmpty() {

        Printer.help();

        assertThat(outContent.toString()).isNotEmpty();
    }

    //Test for method won()
    @Test
    public void shouldCheckIfWonPrintIsNotEmpty() {

        Printer.won();

        assertThat(outContent.toString()).isNotEmpty();
    }

}
