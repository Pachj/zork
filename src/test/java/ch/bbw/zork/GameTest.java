package ch.bbw.zork;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    //Tests for method choosePlayerName()
    @Test
    public void shouldSetNameFromInput() {
        String nameInput = "Name";
        InputStream inputStream = new ByteArrayInputStream(nameInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Game game = new Game();

        assertThat(game.getPlayer().getName()).isEqualTo(nameInput);
    }

    @Test
    public void shouldNotExceptEmptyName() {
        String emptyNameInput = " ";
        InputStream firstInputStream = new ByteArrayInputStream(emptyNameInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(firstInputStream);

        String nameInput = "Name";
        InputStream secondInputStream = new ByteArrayInputStream(nameInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(secondInputStream);

        Game game = new Game();

        assertThat(game.getPlayer().getName()).isEqualTo(nameInput);
    }


    //Tests for method initialiseWinningItems()
    @Test
    public void shouldChooseRightNumberOfWinningItems() {
        String nameInput = "Name";
        InputStream inputStream = new ByteArrayInputStream(nameInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Game game = new Game();

        assertThat(game.getWinningItems().size()).isEqualTo(5);
    }
}
