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

    public void takeMoneyFromBank (int amount){
        gameBalance -= amount;
    }

    public int priceCalculator(int position){
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

    //Use getHouses method from the player and insert into playerHouses
    //Get player's position or location of the house to determine price
    public int sellHouseToBank(int amount, int position, int playerHouses){
        int price = priceCalculator(position);
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
    public int buyHouseFromBank(int amount, int position, int playerBalance){

        int price = priceCalculator(position);
        int finalPrice = (price * amount);

        if(playerBalance > finalPrice){
            gameBalance += finalPrice;
            houses -= amount;
            System.out.println("Du har nu købt " + amount + " hus(e)");
            System.out.println("Der er blevet trukket " + finalPrice + "fra din konto");
            System.out.println("Du har " + playerBalance + "stående på kontoen");
        }
        else{
            System.out.println("Du har ikke nok penge stående på kontoen for at udføre denne handling");
            finalPrice = 0;
        }

        return finalPrice;
    }
    public int buyHotelFromBank(int amount, int position, int playerBalance){
        int price = priceCalculator(position);
        int finalPrice = ((5 * price) * amount);

        if(playerBalance > price){
            gameBalance += finalPrice;
            hotels -= amount;
            System.out.println("Du har nu købt " +amount + " hotel(ler)");
            System.out.println("Der er blevet trukket " + finalPrice + "fra din konto");
            System.out.println("Du har " +playerBalance+ "stående på kontoen");
        }
        else{
            System.out.println("Du har ikke nok penge stående på kontoen for at udføre denne handling");
            finalPrice = 0;
        }

        return finalPrice;
    }

    public int getBalance() {
        return gameBalance;
    }

}
