package GameComponents;
import gui_fields.GUI_Player;

public class Account {

    boolean guiIsOn = false;
    private int balance;

    private int houses;

    private int hotels;
    private boolean isBankrupt = false;
    GUI_Player guiPlayer;

    public Account(){

        balance = 0;
       // houses = 0;
       // hotels = 0;
    }

    public void guiIsOn(boolean guiIsOn){
        this.guiIsOn = guiIsOn;
    }

    public void setGuiAccount(GUI_Player guiPlayer) {
        this.guiPlayer = guiPlayer;

    }

    public void deposit(int amount) {
        balance += amount;
        if(guiIsOn) {guiPlayer.setBalance(balance);}
    }

    public void withDraw(int amount) {
        balance -= amount;
        if(guiIsOn) {guiPlayer.setBalance(balance);}
        if (balance < 0) {
            isBankrupt = true;
        }
    }
    public int getBalance()
    {
        return(balance);
    }

    public boolean getAccountStatus() {
        return isBankrupt;
    }


    /*
    public int getHouses(){
        return houses;
    }

    public int getHotels(){
        return hotels;
    }

    public void acquireHouse(int acquiredHouses, int price){ //Price is the returned finalPrice from the buy methods in bank class
        if (price > 0)
        {
            houses += acquiredHouses;
            balance -= price;

        }

    }

    //Price is the returned finalPrice from the sell method in bank class
    //Make sure that soldHouses is the same as amount set in the bank method
    public void sellHouses(int soldHouses, int price){
        if (price > 0)
        {
            houses -= soldHouses;
            balance += price;

        }
    }
    public void acquireHotel(int acquiredHotels, int price){ //Price is the returned finalPrice from the buy method in bank class
            if (price > 0)
            {
                hotels += acquiredHotels;
                balance -= price;

            }
    }
    public void sellHotel(int soldHotels, int price){ //Price is the returned finalPrice from the sell method in bank class
        if (price > 0)
        {
            hotels -= soldHotels;
            balance += price;

        }
    }
*/

}


