package GameComponents;
/*
===================================================================================
This class is reused from our previous project CDIO 1 and translated to English.
===================================================================================
 */
public class Die {
    private int resultRoll;

    public int roll() {  // GENERATES A RANDOM NUMBER BETWEEN 1-6
        final int diceFaces = 6 ;
        resultRoll = (int)(Math.random() * diceFaces ) + 1;
        return resultRoll;
    }

    public String toString() {
        String rollString = Integer.toString(resultRoll);
        return rollString;
    }
}

