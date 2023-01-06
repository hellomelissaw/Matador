package GameComponents.Board;

import Controllers.GuiController;
import GameComponents.Player;
import Translator.Text;

public class BoardInit {
    Square[] board = new Square[40];
    GuiController guiController;

    /**
     * Constructs a BoardInit where all the Squares on the board are instantiated with name and evt. price.
     * @param guiController The GuiController used throughout the classes.
     * @param msg The Text object used throughout the classes.
     */
    public BoardInit(GuiController guiController, Text msg, Player[] players) {
        this.guiController = guiController;

        board[0] = new StartSquare(msg.getText("start"));
        board[1] = new DeedSquare(msg.getText("Rødovrevej"), 1200, guiController);
        board[2] = new ChanceSquare(msg.getText("Prøv lykken"), guiController, players);
        board[3] = new DeedSquare(msg.getText("Hvidovrevej"), 1200, guiController);
        board[4] = new TaxSquare(msg.getText("Betal indkomstskat: 10% eller 4000"), guiController, players);
        board[5] = new DeedSquare(msg.getText("Scandlines"), 4000, guiController);
        board[6] = new DeedSquare(msg.getText("Roskildevej"), 4000, guiController);
        board[7] = new ChanceSquare(msg.getText("Prøv lykken"), guiController, players);
        board[8] = new DeedSquare(msg.getText("Valby Langgade"), 2000, guiController);
        board[9] = new DeedSquare(msg.getText("Allegade"), 2400, guiController);
        board[8] = new DeedSquare(msg.getText("library"), 2, guiController);
        board[9] = new ChanceSquare(msg.getText("Chance"), guiController, players);
        board[10] = new JailSquare(msg.getText("På besøg i fængsel"), guiController);
        board[11] = new DeedSquare(msg.getText("Frederiksbergalle"), 2800, guiController);
        board[12] = new DeedSquare(msg.getText("Squash"), 3000, guiController);
        board[13] = new DeedSquare(msg.getText("Bulowsvej"), 2800, guiController);
        board[14] = new DeedSquare(msg.getText("Gl.Kongevej"), 3200, guiController);
        board[15] = new DeedSquare(msg.getText("Mols-Linjen"), 4000, guiController);
        board[16] = new DeedSquare(msg.getText("Berntoffsvej"), 3600, guiController);
        board[17] = new ChanceSquare(msg.getText("Prøv lykken"), guiController, players);
        board[18] = new DeedSquare(msg.getText("Hellerupvej"), 3600, guiController);
        board[19] = new DeedSquare(msg.getText("Strandvejen"), 4000, guiController);
        board[20] = new ParkingSquare(msg.getText("freeParking"));
        board[21] = new DeedSquare(msg.getText("Trianglen"), 4400, guiController);
        board[22] = new ChanceSquare(msg.getText("Prøv lykken"), guiController, players);
        board[23] = new DeedSquare(msg.getText("Østerbrogade"), 4400, guiController);
        board[24] = new DeedSquare(msg.getText("Grønningen"), 4800, guiController);
        board[25] = new DeedSquare(msg.getText("Mols-Linjen"), 4000, guiController);
        board[26] = new DeedSquare(msg.getText("Bredgade"), 5200, guiController);
        board[27] = new DeedSquare(msg.getText("Kgs. Nytorv"), 5200, guiController);
        board[28] = new DeedSquare(msg.getText("Coca Cola"), 3000, guiController);
        board[29] = new DeedSquare(msg.getText("Østergade"), 5600, guiController);
        board[30] = new JailSquare(msg.getText("På fængsel"), guiController);
        board[31] = new DeedSquare(msg.getText("AmagaerTorv"), 6000, guiController);
        board[32] = new DeedSquare(msg.getText("Vimmelskaffet"), 6000, guiController);
        board[33] = new ChanceSquare(msg.getText("prøv lykken"), guiController, players);
        board[34] = new DeedSquare(msg.getText("Nygade"), 6400, guiController);
        board[35] = new DeedSquare(msg.getText("Scandlines"), 4000, guiController);
        board[36] = new ChanceSquare(msg.getText("prøv lykken"), guiController, players);
        board[37] = new DeedSquare(msg.getText("Frederiksberggade"), 7000, guiController);
        board[38] = new TaxSquare(msg.getText("Betal 2000 kr i skat"), guiController, players);
        board[39] = new DeedSquare(msg.getText("Rådhuspladsen"), 8000, guiController);

        for(int i= 0 ; i< board.length ; i++) {
            board[i].setLang(msg);
        }

       /* board[0] = new StartSquare(msg.getText("start"));
        board[1] = new DeedSquare(msg.getText("burgerBar"), 1,guiController);
        board[2] = new DeedSquare(msg.getText("pizzeria"), 1,guiController);
        board[3] = new ChanceSquare(msg.getText("chance"),guiController, players);
        board[4] = new DeedSquare(msg.getText("candyShop"),1,guiController);
        board[5] = new DeedSquare(msg.getText("iceCreamShop"), 1,guiController);
        board[6] = new JailSquare(msg.getText("visitJail"),guiController);
        board[7] = new DeedSquare(msg.getText("museum"),2,guiController);
        board[8] = new DeedSquare(msg.getText("library"),2,guiController);
        board[9] = new ChanceSquare(msg.getText("Chance"),guiController, players);
        board[10] = new DeedSquare(msg.getText("skatePark"),2,guiController);
        board[11] = new DeedSquare(msg.getText("pool"),2,guiController);
        board[12] = new ParkingSquare(msg.getText("freeParking"));
        board[13] = new DeedSquare(msg.getText("playingHall"), 3,guiController);
        board[14] = new DeedSquare(msg.getText("cinema"),3,guiController);
        board[15] = new ChanceSquare(msg.getText("chance"),guiController, players);
        board[16] = new DeedSquare(msg.getText("toyStore"),3,guiController);
        board[17] = new DeedSquare(msg.getText("petShop"),3,guiController);
        board[18] = new JailSquare(msg.getText("jail"),guiController);
        board[19] = new DeedSquare(msg.getText("bowlingAlley"),4,guiController);
        board[20] = new DeedSquare(msg.getText("zoo"),4,guiController);
        board[21] = new ChanceSquare(msg.getText("chance"),guiController, players);
        board[22] = new DeedSquare(msg.getText("waterPark"),5,guiController);
        board[23] = new DeedSquare(msg.getText("boardWalk"),5,guiController);

        String[] colors = {"lightgrey", "cyan", "pink", "orange", "red", "yellow", "green", "darkblue"};


        for(int i= 0 ; i< board.length ; i++) {
            board[i].setLang(msg);
        }


        for(int i = 0 ; i < board.length ; i+=3) {

            if(i % 2 == 0) {
                board[i].setColor("white");

            } else {
                board[i].setColor("magenta");

            }

            board[i+1].setColor(colors[i/3]);
            board[i+2].setColor(colors[i/3]);

        }

    }*/

       /* public void initChanceSquare(Square[] board){
            for (int i = 0; i < board.length; i++) {
                if (board[i] instanceof ChanceSquare) {
                    ((ChanceSquare) board[i]).setChanceCards(board);
                    ((ChanceSquare) board[i]).setCardLang();

                }
            }
        }
        public Square[] getSquareArr () {
            return board;
        }


    }*/
    }

    public Square[] getSquareArr() {
        return board;
    }

    public void initChanceSquare(Square[] squares) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] instanceof ChanceSquare){
                ((ChanceSquare)board[i]).setChanceCards(board);
                ((ChanceSquare)board[i]).setCardLang();
            }

        }
    }
}
