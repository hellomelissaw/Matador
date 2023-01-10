package GameComponents;

public class Bank {

    private int gameBalance;
    private int houses;
    private int hotels;

    public Bank(){
        gameBalance = 150000;
        houses = 32;
        hotels = 12;

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



   /* public int priceCalculator(int position){
        int price = 0;
        if (position > 10){
            price = 1000;
        }else if (10 < position && position > 20) {
            price = 2000;
        }else if (20 < position && position > 30){
            price = 3000;
        }else if (30 < position) {
            price = 4000;
        }
        return price;
    }
    */

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

    //Use getHouses method from the player and insert into playerHouses
    //Get player's position or location of the house to determine price
    public int sellHouseToBank(int amount, int position, int playerHouses){
        //int price = priceCalculator(position);
        int finalPrice= (int) ((price * 0.5) * amount);
        houses += amount;

        if(playerHouses == 0) {
            System.out.println("Du har ingen huse at sælge");
            finalPrice = 0;
        }
        else if (amount > playerHouses){
            System.out.println("Du har kun " + playerHouses + " huse du kan sælge. Vælg et andet antal");
            finalPrice = 0;
        }else {
            houses += amount;
            gameBalance -= finalPrice;
        }

        return finalPrice;
    }

    public int sellHotelToBank(int amount, int position, int playerHotels){
        int price = priceCalculator(position);
        int finalPrice = (int) (((5 * price )* 0.5) * amount);

        if(playerHotels == 0) {
            System.out.println("Du har ingen hoteller at sælge");
            finalPrice = 0;
        }
        else if (amount > playerHotels){
            System.out.println("Du har kun " + playerHotels + " hoteller du kan sælge. Vælg et andet antal");
            finalPrice = 0;
        }else {
            hotels += amount;
            gameBalance -= finalPrice;
        }

        return finalPrice;

    }
    public void buyHouseFromBank(int amount, int finalPrice){

        gameBalance += finalPrice;
        hotels-= amount;

    }
    public void buyHotelFromBank(int amount, int finalPrice){

            gameBalance += finalPrice;
            hotels -= amount;

    }

}
