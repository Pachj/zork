package ch.bbw.zork.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {
    private final BufferedReader reader;

    public Parser() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String[] readWords() throws IOException {
        return reader.readLine()
                .toLowerCase()
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }
}
