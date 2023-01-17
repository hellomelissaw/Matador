import Controllers.GameControllerDevMode;


public class Game_DevMode {
    public static void main(String[] args) {

        GameControllerDevMode game = new GameControllerDevMode();

        game.init();
        game.run();
        //game.run(new Cup());

    }
}
