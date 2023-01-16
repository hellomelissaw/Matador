import Controllers.*;
import GameComponents.Cup;


public class Game {
    public static void main(String[] args) {

        GameController game = new GameController();

        game.init();
        game.run();

    }
}
