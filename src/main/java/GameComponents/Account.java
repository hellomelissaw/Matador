package GameComponents;

import java.io.BufferedReader;
import java.io.FileReader;

public class Account {
    private int balance;
    private boolean isBankrupt = false;


    public Account(){
        balance = 0;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withDraw(int amount) {
        balance -= amount;
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


