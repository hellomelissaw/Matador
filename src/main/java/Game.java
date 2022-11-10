import Controllers.*;
import GameComponents.Player;

public class Game {
    public static void main(String[] args) {
        //GameController game = new GameController();
        GuiController gui = new GuiController();
        //game.init();
        //game.run();
        gui.init();
        gui.run();




        // Make the board with 24 squares
        GuiController guiBoard = new GuiController();
        guiBoard.addPlayer();


    }
}
