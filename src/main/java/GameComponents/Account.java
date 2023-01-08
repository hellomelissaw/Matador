package GameComponents;
import Controllers.GuiController;
import gui_fields.GUI_Player;

public class Account {
    private int balance;

    private int houses;

    private int hotels;
    private boolean isBankrupt = false;
    GUI_Player guiPlayer;

    public Account(){

        balance = 0;
        houses = 0;
        hotels = 0;
    }

    public void setGuiAccount(GUI_Player guiPlayer) {
        this.guiPlayer = guiPlayer;

    }

    public void deposit(int amount) {
        balance += amount;
        guiPlayer.setBalance(balance);
    }

    public void withDraw(int amount) {
        balance -= amount;
        guiPlayer.setBalance(balance);
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

    public void acquireHouse(int acquiredHouses){

        houses += acquiredHouses;
    }
    public void sellHouses(int soldHouses){
        if(houses <= 0) {
            System.out.println("Du har ingen huse at sælge");
        }
        else if (soldHouses > houses){
            System.out.println("Du har kun " + houses + "huse du kan sælge. Vælg et andet antal");
        }else {
            houses -= soldHouses;
        }

    }
    public void acquireHotel(int acquiredHotels){

        hotels += acquiredHotels;
    }
    public void sellHotel(int soldHotels){
        if(hotels <= 0) {
            System.out.println("Du har ingen hoteller at sælge");
        }
        else if (soldHotels > hotels){
            System.out.println("Du har kun " + hotels + "hoteller du kan sælge. Vælg et andet antal");
        }else {
            hotels -= soldHotels;
        }

    }


}


