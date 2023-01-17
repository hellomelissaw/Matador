package GameComponents;
import gui_fields.GUI_Player;

public class Account {

    boolean guiIsOn = true;
    private int balance;
    private int jailPasses;
    private boolean isBankrupt = false;
    GUI_Player guiPlayer;

    public Account(){

        balance = 0;
        jailPasses = 0;

    }

    public void guiIsOn(boolean guiIsOn){
        this.guiIsOn = guiIsOn;
    }

    public void setBalance(int newBalance){
        balance = newBalance;
        guiPlayer.setBalance(newBalance);
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

    public int getJailPasses (){
        return jailPasses;
    }

    public void giveJailPass(){
        jailPasses += 1;
    }

    public boolean useJailPass(){
        boolean hasUsedPass = false;

        if (jailPasses==0){
            System.out.println("Du har ikke flere kom ud af fængselkort");
        } else if (jailPasses > 0) {
            jailPasses--;
            hasUsedPass = true;
        }

        return hasUsedPass;
    }

}


