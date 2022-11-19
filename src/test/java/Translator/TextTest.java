package Translator;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {

    @Test
    public void getTextChecksLabelReturnsMessage() {
        Text reader = new Text("src/main/java/Translator/TestLang");

        String testMessage = " message";
        assertEquals(testMessage,reader.getText("label"));

    }

}