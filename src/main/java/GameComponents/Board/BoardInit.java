package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;

import java.io.BufferedReader;

import static java.lang.Integer.valueOf;

    public class BoardInit {
    final int boardSize = 40;
    Square[] board = new Square[boardSize];
    GuiController guiController;
    Text msg;
    Player[] players;

    boolean guiIsOn = true;

    /**
     * Constructs a BoardInit where all the Squares on the board are instantiated with name and evt. price.
     * @param guiController The GuiController used throughout the classes.
     * @param msg The Text object used throughout the classes.
     */
    public BoardInit(GuiController guiController, Text msg, Player[] players) {
        this.guiController = guiController;
        this.msg = msg;
        this.players = players;

    }

    public BoardInit(Text msg, Player[] players) {
        guiIsOn = false;
        this.msg = msg;
        this.players = players;

    }

    public void initBoard(){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new java.io.FileReader("src/main/java/GameComponents/Board/fields.csv"));
            String line;

            for (int i = 0; i < boardSize+1; i++) {

                line = reader.readLine();
                String[] squareInfo = line.split(",");

                if(squareInfo[2].equals(" start")) {
                    board[i-1] = new StartSquare(squareInfo[0]);
                    board[i-1].setLang(msg);

                } else if(squareInfo[2].equals(" street")) {
                    int[] rent = new int[6];
                    for(int j = 0 ; j < 6 ; j++) {
                        rent[j] = valueOf(squareInfo[j+4]);
                    }

                    board[i-1] = new DeedSquare_Buildable(squareInfo[0], valueOf(squareInfo[3]), rent, valueOf(squareInfo[4]));
                    board[i-1].setLang(msg);
                    board[i-1].setGroup(squareInfo[11], valueOf(squareInfo[12]));
                    ((DeedSquare_Buildable)board[i-1]).setDeedIndex(i-1);

                    if(guiIsOn){ board[i-1].setGuiController(guiController);
                    } else {
                        board[i-1].setGuiIsOn(false);
                    }

                } else if (squareInfo[2].equals(" chance")) {
                    board[i-1] = new ChanceSquare(squareInfo[0], players);
                    board[i-1].setLang(msg);
                    if(guiIsOn){ board[i-1].setGuiController(guiController); } else {
                        board[i-1].setGuiIsOn(false);
                    }

                } else if (squareInfo[2].equals(" tax")) {
                    board[i-1] = new TaxSquare(squareInfo[0], guiController, players);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" ferry" )) {
                    int[] rent = new int[4];
                    for(int j = 0 ; j < 4 ; j++) {
                        rent[j] = valueOf(squareInfo[j+5]);
                    }
                    board[i-1] = new DeedSquare_NonBuildable(squareInfo[0], valueOf(squareInfo[3]), rent);
                    board[i-1].setLang(msg);
                    board[i-1].setGroup(squareInfo[11], valueOf(squareInfo[12]));
                    if(guiIsOn){ board[i-1].setGuiController(guiController);
                    } else {
                        board[i-1].setGuiIsOn(false);
                    }

                } else if  (squareInfo[2].equals(" jail")) {
                    board[i-1] = new JailSquare(squareInfo[0], guiController);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" brewery")) {
                    int[] rent = new int[2];
                    for(int j = 0 ; j < 2 ; j++) {
                        rent[j] = valueOf(squareInfo[j+5]);
                    }
                    board[i-1] = new DeedSquare_NonBuildable(squareInfo[0], valueOf(squareInfo[3]), rent);
                    board[i-1].setLang(msg);
                    board[i-1].setGroup(squareInfo[11], valueOf(squareInfo[12]));
                    if(guiIsOn){ board[i-1].setGuiController(guiController); } else {
                        board[i-1].setGuiIsOn(false);
                    }

                } else if (squareInfo[2].equals(" refuge")) {
                    board[i-1] = new ParkingSquare(squareInfo[0]);
                    board[i-1].setLang(msg);

                }


            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Initiates a Chance Square by setting its chance cards' language and the game's board
     * @param board All the squares on the game's board are passed to the chance cards so they can use them
     */
    public void initChanceSquare(Square[] board){
        for (int i = 0 ; i < board.length ; i++) {
            if(board[i] instanceof ChanceSquare){
                ((ChanceSquare)board[i]).setChanceCards(board);
                ((ChanceSquare)board[i]).setCardLang();

            }
        }
    }

    /**
     * Returnering all squares on the board as an array of Square
     * @return
     */
    public Square[] getSquareArr() {
        return board;
    }


}
