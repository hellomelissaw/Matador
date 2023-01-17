import Controllers.GameController;
import GameComponents.LoadedCup;

import java.util.ArrayList;
import java.util.Arrays;

public class SellControllerSequenceTests {


    public static void main(String[] args) {

        GameController game = new GameController();
        LoadedCup cup = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{1, 2}, new int[]{1, 2})));
        game.init();
        game.run(cup);
    }

}