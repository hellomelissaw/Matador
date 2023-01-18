import Controllers.GameController;
import GameComponents.LoadedCup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JailTest {


    public static void main(String[] args) {

        GameController game = new GameController();
        LoadedCup cup = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{15, 15}, new int[]{2, 2})));
        game.init();
        game.run(cup);

        /*GameController gameTripleRoll = new GameController();
        LoadedCup cup2 = new LoadedCup(new ArrayList<>(Arrays.asList(new int[]{1, 1}, new int[]{3, 3}, new int[]{4, 4})));
        gameTripleRoll.init();
        gameTripleRoll.run(cup2);*/
    }



}