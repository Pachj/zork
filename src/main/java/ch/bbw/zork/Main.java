package ch.bbw.zork;

import ch.bbw.zork.reader.Commands;

public class Main {
    public static void main(String[] args) {
        /*
        Commands commands = new Commands();
        commands.parse();

         */
        Game game = new Game();
        game.start();
    }
}
