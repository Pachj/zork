package ch.bbw.zork;

import ch.bbw.zork.reader.Parser;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    // Tests for method readWords()
    @Test
    public void shouldMakeInputToLowerCase() throws IOException {
        String input = "This IS mY inPut";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Parser parser = new Parser();
        String[] parsedInput = parser.readWords();

        assertThat(parsedInput[0]).isEqualTo("this");
        assertThat(parsedInput[1]).isEqualTo("is");
        assertThat(parsedInput[2]).isEqualTo("my");
        assertThat(parsedInput[3]).isEqualTo("input");
    }

    @Test
    public void shouldIgnoreMultipleWhiteSpaces() throws IOException {
        String input = "  My   input  ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Parser parser = new Parser();
        String[] parsedInput = parser.readWords();

        assertThat(parsedInput.length).isEqualTo(2);
        assertThat(parsedInput[0]).isEqualTo("my");
        assertThat(parsedInput[1]).isEqualTo("input");
    }
}
