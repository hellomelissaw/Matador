import Controllers.*;
import GameComponents.Cup;
import GameComponents.RandomCup;

import java.util.Random;


public class  Game {
    public static void main(String[] args) {

        GameController game = new GameController();

        game.init();
        game.run(new RandomCup(new Random()));

    }
}
