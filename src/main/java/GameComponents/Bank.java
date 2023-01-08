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
        gameBalance -= amount;
    }

    public void takeMoneyFromBank (int amount){
        gameBalance += amount;
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
    public void sellHouseToBank(int amount, int position){
        int price = priceCalculator(position);
        gameBalance -= ((price * 0.5) * amount);
        houses += amount;

    }
    public void sellHotelToBank(int amount, int position){
        int price = priceCalculator(position);
        gameBalance -= (((5 * price )* 0.5) * amount);
        hotels += amount;

    }
    public int buyHouseFromBank(int amount, int position){
        int price = priceCalculator(position);
        int finalPrice = (price * amount);
        gameBalance += finalPrice;
        houses -= amount;

        return finalPrice;
    }
    public int buyHotelFromBank(int amount, int position){
        int price = priceCalculator(position);
        int finalPrice = ((5 * price) * amount);
        gameBalance += finalPrice;
        hotels -= amount;

        return finalPrice;
    }

}
