package GameComponents.Board;

public class ParkingSquare extends Square{
    public ParkingSquare(String freeParkingSquare) {
        super(freeParkingSquare);


    }
    public void landOn() {
        System.out.println(msg.getText("freeParking"));
    }
}

