package Controllers;

import GameComponents.Player;
import Translator.Text;
import gui_fields.GUI_Player;
import org.junit.Test;

import static org.junit.Assert.*;
import java.awt.Color;

public class GameControllerTest {


        Player testPlayer_1 = new Player("Test Player 1");
        Player testPlayer_2 = new Player("Test Player 2");
        GuiController guiController = new GuiController();
        GUI_Player testGuiPlayer_1 = new GUI_Player("TestGui Player 1");
        GUI_Player testGuiPlayer_2 = new GUI_Player("TestGui Player 2");
        Text msg = new Text("src/main/java/Translator/DanskTekst", guiController);
        //GameController gameController = new GameController();
        String carColor;
        String userSelection;
        String[] colorArray = new String[3];
        Color BLÅ = new Color(0, 0, 255);
        Color GUL = new Color(255, 255, 0);
        Color GRØN = new Color(0, 255, 0);


        @Test
        public void chooseCarColor() {
                testPlayer_1.setGui(testGuiPlayer_1, guiController, msg);
                colorArray[0] = "Blå";
                colorArray[1] = "Gul";
                colorArray[2] = "Grøn";

                userSelection = guiController.getUserSelection("Valg et farve", colorArray);

                if (userSelection.equals("Blå")) {
                        testPlayer_1.setCarColor(Color.blue);
                        assertEquals(BLÅ, testGuiPlayer_1.getPrimaryColor());
                }
                if (userSelection.equals("Gul")) {
                        testPlayer_1.setCarColor(Color.yellow);
                        assertEquals(GUL, testGuiPlayer_1.getPrimaryColor());
                }
                if (userSelection.equals("Grøn")) {
                        testPlayer_1.setCarColor(Color.green);
                        assertEquals(GRØN, testGuiPlayer_1.getPrimaryColor());
                }

        }

       /* @Test
        public void toPlayersChoosDiffrentColors() {
                testPlayer_1.setGui(testGuiPlayer_1, guiController, msg);
                testPlayer_2.setGui(testGuiPlayer_1, guiController, msg);
                colorArray[0] = "Blå";
                colorArray[1] = "Gul";
                colorArray[2] = "Grøn";
                //for (int i = 0; i < 2; i++) {

                        userSelection = guiController.getUserSelection("Valg et farve", colorArray);
                        for (int j = 0; j < colorArray.length; j++) {// check color duplicated, and remove the color from the color array, and the next player is not allowed to choos the same color
                                if (colorArray[j] != null && colorArray[j].equals(carColor)) {
                                        colorArray[j] = null;
                                        String[] tempColor = new String[colorArray.length - 1];
                                        boolean foundColor = false;
                                        for (int k = 0; k < colorArray.length - 1; k++) {
                                                if (colorArray[k] == null || foundColor) {
                                                        foundColor = true;
                                                        tempColor[k] = colorArray[k + 1];
                                                } else tempColor[k] = colorArray[k];
                                        }
                                        colorArray = tempColor;
                                }
                        }
                        gameController.setCarColor(userSelection,testPlayer_1);
                //gameController.setCarColor(userSelection,testPlayer_2);
                }

        }*/
}