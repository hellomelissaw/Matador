import Controllers.GameControllerDevMode;
import GameComponents.RandomCup;

import java.util.Random;


public class Game_DevMode {
    public static void main(String[] args) {

        GameControllerDevMode game = new GameControllerDevMode();

        game.init();
        game.run(new RandomCup(new Random()));

    }
}
