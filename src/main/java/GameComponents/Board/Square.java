package GameComponents.Board;

public abstract class Square {
    int pointsValue;
    private String squareName;

    Square(String squareName){

        this.squareName = squareName;
    }

    public String toString() {
        return squareName;
    }
}

// definere et array med alle første kast af terningen
   /* private int[] pointArray = {250, -100, 100, -20, 180, 0, -70, 60, -80, -50, 650, -500, 350, 100,
            100, 75, 0, -140, 60, 50, 100,
            40, -700};

    // Square 2:    Deposit: 250
    // Square 3:    Withdraw: 100
    // Square 4:    Deposit: 100
    // Square 5:    Withdraw: 20
    // Square 6:    Deposit: 180
    // Square 7:    Do nothing
    // Square 8:    Withdraw: 70
    // Square 9:    Deposit 60
    // Square 10:   Withdraw: 80
    // Square 11:   Withdraw: 50
    // Square 12:   Deposit: 650

    public int getPointValue(int diceSum) {

        int i = pointArray[diceSum - 2];
        return i;

    }

    public int moveToSquare(int Sum) {
        Text squareDescription = new Text("src/squaredescriptions");
        Text gameInstruction = new Text("src/gametext.csv");
        if(Sum>24 && Sum>2) {
            gameInstruction.printText(11);
        }
        squareDescription.printText(Sum);
        return (getPointValue(Sum));

    }
}


      /*
        switch (Sum){
            case 2:
                squareDescription.printText(2);
                return (getPointValue(Sum));

            case 3:
                squareDescription.printText(3);
                return (getPointValue(Sum));

            case 4:
                squareDescription.printText(4);
                currentPlayer.givePoints(100);

            case 5:
                squareDescription.printText(5);


            case 6:
                squareDescription.printText(6);

            case 7:
                squareDescription.printText(7);

            case 8:
                squareDescription.printText(8);

            case 9:
                squareDescription.printText(9);

            case 10:
                squareDescription.printText(10);

            case 11:
                squareDescription.printText(11);

            case 12:
                squareDescription.printText(12);

            default:
                System.out.println("Error: Dice sum not within the range");



} */
