package GameComponents.Board;

import GameComponents.Player;

public class StartSquare extends Square{
    String squareName;
    Player player;
    BoardInit board;
    public StartSquare(String startSquare) {
        super(startSquare);

    }

    public StartSquare(String squareName, String squareName1) {
        super(squareName);
        this.squareName = squareName1;
    }

    public String getSquareName() {
        return squareName;
    }

    public BoardInit getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    /* public boolean LandOnStartSquare() {
       if (board[0]==true)
          return
   }*/
    public void LandOnSquare() {

    }
}
