import Controllers.*;


public class Game {
    public static void main(String[] args) {

        GameController game = new GameController();
        //USE initTest() TO AVOID HAVING TO ENTER PLAYER COUNT AND NAMES WHEN RUNNING
        game.initTest();

        //UNCOMMENT TO WHEN RUNNING PROGRAM NORMALLY AND COMMENT game.initTest();
        //game.init();
        game.run();









    }
}
