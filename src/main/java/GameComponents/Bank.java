package GameComponents;

public class Bank {

    private int gameBalance;
    private int houses;
    private int hotels;

    public Bank(){
        gameBalance = 374500;
        houses = 32;
        hotels = 12;

    }

    public int getBankBalance(){
        return gameBalance;
    }

    public void giveMoneyToBank (int amount){
        gameBalance += amount;
    }

    public int takeMoneyFromBank (int amount){
        int cashedOutMoney = 0;

        if (gameBalance > 0 && gameBalance > amount){
            gameBalance -= amount;
            cashedOutMoney = amount;
        }else if (gameBalance > 0 && gameBalance < amount)
        {
            cashedOutMoney = gameBalance;
            System.out.println("Der er ikke nok penge i banken til at udbetale den fulde sum");
            System.out.println("Der kan kun udbetales " + cashedOutMoney + " ud af" + amount);
            gameBalance = 0;
        }else if (gameBalance == 0){
            System.out.println("Banken har ikke flere penge at udbetale");
        }
        return cashedOutMoney;
    }

    public boolean areThereStillHotels(){
        boolean areThereHotels = false;

        if (hotels !=0){
            areThereHotels = true;
        }
        return areThereHotels;
    }

    public boolean areThereEnoughHouses(int amountOfHousesToBuy){
        boolean enoughHouses = false;

        if (amountOfHousesToBuy < houses){
            enoughHouses = true;
        }else if(amountOfHousesToBuy > houses && houses != 0){
            System.out.println("Der er kun " + houses + " tilbage i banken.");
            System.out.println("Køb et andet antal huse");
        }
        return enoughHouses;
    }

    public void sellHouseToBank(int amount, int price){
        gameBalance -= price;
        houses += amount;
    }

    public void sellHotelToBank(int amount, int price){
        gameBalance -= price;
        hotels += amount;
    }
    public void buyHouseFromBank(int amount, int price){
        gameBalance += price;
        hotels -= amount;
    }
    public void buyHotelFromBank(int amount, int price){
            gameBalance += price;
            hotels -= amount;
    }

}
