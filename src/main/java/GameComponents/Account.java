package GameComponents;

import java.io.BufferedReader;
import java.io.FileReader;

public class Account {
    private int balance;
    private String Accountname;


    public Account(){
        balance = 0;
       // Accountname = name;
    }
    public void setBalance(int initialBalance){
        balance = initialBalance;
    }
    public void deposit(int amount)
    {
        balance += amount;
    }
    public void withDraw(int amount)
    {
        balance -= amount;
        if (balance < 0)
            balance = 0;
    }
    public int getBalance()
    {
        //System.out.println(Accountname + "now has " + balance + " points");
        return(balance);
    }



}


