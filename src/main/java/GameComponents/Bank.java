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

    public void giveMoney (int amount){
        gameBalance -= amount;

    }

    public void takeMoney (int amount){
        gameBalance += amount;

    }










}
