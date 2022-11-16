package Translator;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {

    @Test
    public void returnTextFirstIndexOfMessages() {
        Text reader = new Text("src/main/java/Translator/TestLang");
        String testString = " Hvor mange spillere?";
        assertEquals(testString,reader.returnText(1));

    }

}