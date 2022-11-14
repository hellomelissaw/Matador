import Translator.*;
import java.io.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextReaderTest {

    @Test
    public void returnText() {
            TextReader reader = new TextReader("src/main/java/Translator/DanskTekst");
            String testString = "Hvor mange spillere?";
            assertEquals(testString,reader.returnText(1));

    }
}