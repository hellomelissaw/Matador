import Controllers.GameController;
import GameComponents.LoadedCup;

import java.util.List;

public class JailTest {


    public static void main(String[] args) {

        GameController game = new GameController();
        LoadedCup cup = new LoadedCup(List.of(new int[]{0, 2}, new int[]{3, 4}));
        game.init();
        game.run(cup);
    }

}