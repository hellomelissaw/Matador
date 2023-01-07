package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;

import java.io.BufferedReader;

import static java.lang.Integer.valueOf;

public class BoardInit {
    int boardSize = 40;
    Square[] board = new Square[boardSize];
    GuiController guiController;

    /**
     * Constructs a BoardInit where all the Squares on the board are instantiated with name and evt. price.
     * @param guiController The GuiController used throughout the classes.
     * @param msg The Text object used throughout the classes.
     */
    public BoardInit(GuiController guiController, Text msg, Player[] players) {
        this.guiController = guiController;

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
                       rent[j] = valueOf(squareInfo[j+5]);
                    }

                    board[i-1] = new StreetSquare(squareInfo[0], valueOf(squareInfo[3]), valueOf(squareInfo[4]), rent, guiController);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" chance")) {
                    board[i-1] = new ChanceSquare(squareInfo[0], guiController, players);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" tax")) {
                    board[i-1] = new TaxSquare(squareInfo[0], guiController, players);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" ferry" )) {
                    int[] rent = new int[4];
                    for(int j = 0 ; j < 4 ; j++) {
                        rent[j] = valueOf(squareInfo[j+5]);
                    }
                    board[i-1] = new DeedSquare_NonBuildable(squareInfo[0], valueOf(squareInfo[3]), rent, guiController);
                    board[i-1].setLang(msg);

                } else if  (squareInfo[2].equals(" jail")) {
                    board[i-1] = new JailSquare(squareInfo[0], guiController);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" brewery")) {
                    int[] rent = new int[2];
                    for(int j = 0 ; j < 2 ; j++) {
                        rent[j] = valueOf(squareInfo[j+5]);
                    }
                    board[i-1] = new DeedSquare_NonBuildable(squareInfo[0], valueOf(squareInfo[3]), rent, guiController);
                    board[i-1].setLang(msg);

                } else if (squareInfo[2].equals(" refuge")) {
                    board[i-1] = new ParkingSquare(squareInfo[0]);
                    board[i-1].setLang(msg);

                }


            }

        } catch (Exception e) {
            e.printStackTrace();

        }

/*
        board[0] = new StartSquare(msg.getText("start"));
        board[1] = new DeedSquare(msg.getText("Rødovrevej"), 1200,guiController);
        board[2] = new ChanceSquare(msg.getText("Prøv lykken"),guiController, players);
        board[3] = new DeedSquare(msg.getText("Hvidovrevej"), 1200,guiController);
        board[4] = new TaxSquare(msg.getText("Betal indkomstskat: 10% eller 4000"),guiController, players);
        board[5] = new DeedSquare(msg.getText("Scandlines"),4000,guiController);
        board[6] = new DeedSquare(msg.getText("Roskildevej"), 4000,guiController);
        board[7] = new ChanceSquare(msg.getText("Prøv lykken"),guiController, players);
        board[8] = new DeedSquare(msg.getText("Valby Langgade"), 2000,guiController);
        board[9] = new DeedSquare(msg.getText("Allegade"),2400,guiController);
        board[8] = new DeedSquare(msg.getText("library"),2,guiController);
        board[9] = new ChanceSquare(msg.getText("Chance"),guiController, players);
        board[10] = new JailSquare(msg.getText("På besøg i fængsel"),guiController);
        board[11] = new DeedSquare(msg.getText("Frederiksbergalle"),2800,guiController);
        board[12] = new DeedSquare(msg.getText("Squash"),3000,guiController);
        board[13] = new DeedSquare(msg.getText("Bulowsvej"),2800,guiController);
        board[14] = new DeedSquare(msg.getText("Gl.Kongevej"),3200,guiController);
        board[15] = new DeedSquare(msg.getText("Mols-Linjen"),4000,guiController);
        board[16] = new DeedSquare(msg.getText("Berntoffsvej"),3600,guiController);
        board[17] = new ChanceSquare(msg.getText("Prøv lykken"),guiController, players);
        board[18] = new DeedSquare(msg.getText("Hellerupvej"),3600,guiController);
        board[19] = new DeedSquare(msg.getText("Strandvejen"),4000,guiController);
        board[20] = new ParkingSquare(msg.getText("freeParking"));
        board[21] = new DeedSquare(msg.getText("Trianglen"), 4400,guiController);
        board[22] = new ChanceSquare(msg.getText("Prøv lykken"),guiController, players);
        board[23] = new DeedSquare(msg.getText("Østerbrogade"),4400,guiController);
        board[24] = new DeedSquare(msg.getText("Grønningen"),4800,guiController);
        board[25] = new DeedSquare(msg.getText("Mols-Linjen"),4000,guiController);
        board[26] = new DeedSquare(msg.getText("Bredgade"),5200,guiController);
        board[27] = new DeedSquare(msg.getText("Kgs. Nytorv"),5200,guiController);
        board[28] = new DeedSquare(msg.getText("Coca Cola"),3000,guiController);
        board[29] = new DeedSquare(msg.getText("Østergade"),5600,guiController);
        board[30] = new JailSquare(msg.getText("På fængsel"),guiController);
        board[31] = new DeedSquare(msg.getText("AmagaerTorv"),6000,guiController);
        board[32] = new DeedSquare(msg.getText("Vimmelskaffet"),6000,guiController);
        board[33] = new ChanceSquare(msg.getText("prøv lykken"),guiController, players);
        board[34] = new DeedSquare(msg.getText("Nygade"),6400,guiController);
        board[35] = new DeedSquare(msg.getText("Scandlines"),4000,guiController);
        board[36] = new ChanceSquare(msg.getText("prøv lykken"),guiController, players);
        board[37] = new DeedSquare(msg.getText("Frederiksberggade"),7000,guiController);
        board[38] = new TaxSquare(msg.getText("Betal 2000 kr i skat"),guiController, players);
        board[39] = new DeedSquare(msg.getText("Rådhuspladsen"),8000,guiController);
*/


        /*String[] colors = {"lightgrey", "cyan", "pink", "orange", "red", "yellow", "green", "darkblue"};


        for(int i = 0 ; i < board.length ; i+=3) {

            if(i % 2 == 0) {
                board[i].setColor("white");

            } else {
                board[i].setColor("magenta");

            }

            board[i+1].setColor(colors[i/3]);
            board[i+2].setColor(colors[i/3]);

        }
*/
    }

    public void initChanceSquare(Square[] board){
        for (int i = 0 ; i < board.length ; i++) {
            if(board[i] instanceof ChanceSquare){
                ((ChanceSquare)board[i]).setChanceCards(board);
                ((ChanceSquare)board[i]).setCardLang();

            }
        }
    }
    public Square[] getSquareArr() {
        return board;
    }


}
