import Controllers.GameController;
import GameComponents.Cup;

import java.util.ArrayList;
import java.util.List;

//public class JailTest {

    public static void main(String[] args) {

        GameController game = new GameController();

        List<Integer> list = new ArrayList<>();
        game.init();
        game.run(new Cup() {
            @Override
            public int getSum() {
                return list.get(5);
            }
        });
    }
}
