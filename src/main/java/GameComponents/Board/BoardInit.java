package GameComponents.Board;

import Controllers.GuiController;

public class BoardInit {
    Square[] board = new Square[24];
    GuiController guiController;
    public BoardInit(GuiController guiController) {
        this.guiController = guiController;

        board[0] = new StartSquare("Start");
        board[1] = new DeedSquare("The Burgerbar", 1,guiController);
        board[2] = new DeedSquare("The Pizzahouse", 1,guiController);
        board[3] = new ChanceSquare("Chance");
        board[4] = new DeedSquare("The Candy Store",1,guiController);
        board[5] = new DeedSquare("Ice Cream Shop", 1,guiController);
        board[6] = new JailSquare("Visitor at the Jail");
        board[7] = new DeedSquare("The Museum",2,guiController);
        board[8] = new DeedSquare("The Library",2,guiController);
        board[9] = new ChanceSquare("Chance");
        board[10] = new DeedSquare("The Skate Park",2,guiController);
        board[11] = new DeedSquare("The Pool",2,guiController);
        board[12] = new ParkingSquare("Free Parking");
        board[13] = new DeedSquare("The Playing Hall",3,guiController);
        board[14] = new DeedSquare("The Cinema",3,guiController);
        board[15] = new ChanceSquare("Chance");
        board[16] = new DeedSquare("The Toy Store",3,guiController);
        board[17] = new DeedSquare("The Pet Shop",3,guiController);
        board[18] = new JailSquare("Go to Jail");
        board[19] = new DeedSquare("The Bowling Alley",4,guiController);
        board[20] = new DeedSquare("The Zoo",4,guiController);
        board[21] = new ChanceSquare("Chance");
        board[22] = new DeedSquare("The Water Park",5,guiController);
        board[23] = new DeedSquare("The Board Walk",5,guiController);

    }
    public Square[] getSquareArr() {
        return board;
    }

}
