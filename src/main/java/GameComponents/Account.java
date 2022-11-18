package GameComponents;

public class Account {
    private int balance;
    private String Accountname;
    private boolean bankrupt=false;

    public Account() {
        balance = 0;
        // Accountname = name;
    }

    public void setBalance(int initialBalance) {
        balance = initialBalance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withDraw(int amount) {
        balance -= amount;
        if (balance < 0){
            bankrupt=true;
        }
    }

    public boolean isBankrupt(){
        return bankrupt;

    }

    public int getBalance() {
        //System.out.println(Accountname + "now has " + balance + " points");
        return (balance);
    }


}


