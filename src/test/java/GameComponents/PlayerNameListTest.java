package GameComponents;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerNameListTest {

    @Test
    public void playerNameList() {
        Player[] players = new Player[3];
            players[0].setPlayerName("Sara");
            players[1].setPlayerName("John");
            players[2].setPlayerName("Lea");

        String[] expectedArray = {"Sara", "John","Lea"};
        for (int i = 0; i < 3; i++) {
            assertArrayEquals(expectedArray, players[i].playerNameList(players));

        }

    }
}