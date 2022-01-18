package ch.bbw.zork;

import java.util.List;
import java.util.Map;

public class Printer {
    public static void loading() {
        System.out.println(
            "  _                     _ _             \n"+
            " | |                   | (_)            \n"+
            " | |     ___   __ _  __| |_ _ __   __ _ \n"+
            " | |    / _ \\ / _` |/ _` | | '_ \\ / _` |\n"+
            " | |___| (_) | (_| | (_| | | | | | (_| |\n"+
            " |______\\___/ \\__,_|\\__,_|_|_| |_|\\__, |\n"+
            "                                   __/ |\n"+
            "                                  |___/ \n");
        for(int i = 0; i< 79; i++) {
            System.out.print("*");
            sleep((long) (Math.random() * 100));
        }
        System.out.println("\n\n\n");
    }

    public static void zork_asci() {
        System.out.println(
            "ZZZZZZZZZZZZZZZZZZZ     OOOOOOOOO     RRRRRRRRRRRRRRRRR   KKKKKKKKK    KKKKKKK\n"+
            "Z:::::::::::::::::Z   OO:::::::::OO   R::::::::::::::::R  K:::::::K    K:::::K\n"+
            "Z:::::::::::::::::Z OO:::::::::::::OO R::::::RRRRRR:::::R K:::::::K    K:::::K\n"+
            "Z:::ZZZZZZZZ:::::Z O:::::::OOO:::::::ORR:::::R     R:::::RK:::::::K   K::::::K\n"+
            "ZZZZZ     Z:::::Z  O::::::O   O::::::O  R::::R     R:::::RKK::::::K  K:::::KKK\n"+
            "        Z:::::Z    O:::::O     O:::::O  R::::R     R:::::R  K:::::K K:::::K   \n"+
            "       Z:::::Z     O:::::O     O:::::O  R::::RRRRRR:::::R   K::::::K:::::K    \n"+
            "      Z:::::Z      O:::::O     O:::::O  R:::::::::::::RR    K:::::::::::K     \n"+
            "     Z:::::Z       O:::::O     O:::::O  R::::RRRRRR:::::R   K:::::::::::K     \n"+
            "    Z:::::Z        O:::::O     O:::::O  R::::R     R:::::R  K::::::K:::::K    \n"+
            "   Z:::::Z         O:::::O     O:::::O  R::::R     R:::::R  K:::::K K:::::K   \n"+
            "ZZZ:::::Z     ZZZZZO::::::O   O::::::O  R::::R     R:::::RKK::::::K  K:::::KKK\n"+
            "Z::::::ZZZZZZZZ:::ZO:::::::OOO:::::::ORR:::::R     R:::::RK:::::::K   K::::::K\n"+
            "Z:::::::::::::::::Z OO:::::::::::::OO R::::::R     R:::::RK:::::::K    K:::::K\n"+
            "Z:::::::::::::::::Z   OO:::::::::OO   R::::::R     R:::::RK:::::::K    K:::::K\n"+
            "ZZZZZZZZZZZZZZZZZZZ     OOOOOOOOO     RRRRRRRR     RRRRRRRKKKKKKKKK    KKKKKKK\n"+
            "                                                                              \n");
    }

    public static void greet(String name) {
        System.out.println("Hallo " + name + "\n");

        sleep(1000);
        System.out.println("Wie du bald erfahren wirst, bist du in unserem Labyrinth gefangen. Jedoch geben \n"+
                "wir dir die Chance zu fliehen. Dafür musst du nur einige Items in den Exit Room bringen.");
    }

    public static void printItems(List<Item> items) {
        items.forEach(it -> {
            System.out.println("{ name: " + it.getName() + ", gewicht: " + it.getWeight() + " }");
            sleep(1000);
        });
    }

    public static void printRoomsWithItems(Map<String, Room> rooms) {
        rooms.forEach((key, value) -> {
            System.out.println("Raum: " + value.getName());
            value.getItems().forEach(it ->
                    System.out.println("{ name: " + it.getName() + ", gewicht: " + it.getWeight() + " }"));
            sleep(1000);
        });
    }

    public static void go() {
        System.out.println("\n"+
            "Um dich in dem Labyrinth fortzubewegen, kannst du den Befehl go verwenden. \n"+
            "Ebenfalls musst du noch eine Richtung angeben. \n"+
            "$ go ROOM_NAME\n\n");
    }

    public static void show() {
        System.out.println(
            "Um dich zurechtzufinden, kannst du den Befehl show verwenden. Bei diesem kannst du noch ein Attribut angeben, welches bestimmt, was angezeigt wird.\n"+
            "$ show exits \t\t\t Zeigt dir alle Ausgänge vom aktuellen Raum an.\n"+
            "$ show items \t\t\t Listet alle Items auf, welche sich im aktuellen Raum befinden.\n"+
            "$ show items --global \t Liste aller Items.\n"+
            "$ show backpack \t\t Listet alle Items auf, welche sich in deinem Rucksack befinden.\n\n");
    }

    public static void drop_grab() {
        System.out.println(
            "Damit du Items in deinem Rucksack verstauen und wieder auf den Boden legen kannst, gibt es die Befehle drop und grap.\n"+
            "$ drop ITEM_NAME \t\t legt das Item mit dem Namen ITEM_NAME auf den Boden.\n"+
            "$ drop --all \t\t\t Legt alle Items, welche sich in dem Rucksack befinden auf den Boden.\n"+
            "$ grab ITEM_NAME \t\t Nimmt das Item ITEM_NAME in deinen Rucksack auf.\n"+
            "Was du beachten solltest, ist, dass jedes Item ein Gewicht hat und du nur 20 Kilogramm tragen kannst. Hättest du nur mehr Sport gemacht.\n\n");
}

    public static void back() {
        System.out.println(
            "Mit dem Befehl back kannst du in den Raum gelangen, in welchem du zuvor warst.\n"+
            "$ back\n\n");
    }

    public static void map() {
        System.out.println(
            "Mit dem Befehl map bekommt man eine Übersicht, über alle Räume und die Items, welche sich in denen befinden.\n"+
            "$ map\n\n");
    }

    public static void help() {
        System.out.println(
            "Falls du jemals Hilfe brauchen solltest, kannst du mit dem Befehl help alle Befehle anschauen.\n"+
            "$ help\n\n");
    }

    public static void won() {
        System.out.println(
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
                        "                                                                                                                                                       ");
    }

    private static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
