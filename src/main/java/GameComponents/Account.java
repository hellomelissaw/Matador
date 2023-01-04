package GameComponents;
import Controllers.GuiController;
import gui_fields.GUI_Player;

public class Account {

    boolean guiOn = false;
    private int balance;
    private boolean isBankrupt = false;
    GUI_Player guiPlayer;

    public Account(){

        balance = 0;
    }

    public void setGuiAccount(GUI_Player guiPlayer) {
        this.guiPlayer = guiPlayer;

    }

    public void deposit(int amount) {
        balance += amount;

        if (guiOn) {guiPlayer.setBalance(balance); }
    }

    public void withDraw(int amount) {
        balance -= amount;
        if (guiOn) {guiPlayer.setBalance(balance); }
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

}


