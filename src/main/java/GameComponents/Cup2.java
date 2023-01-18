package GameComponents;

public abstract class Cup2 {
    public abstract Cup2 roll();

    public int getSum() {
        int sum = 0;
        for (int a : getFaces()) {
            sum += a;
        }
        return sum;
    }

    public boolean isSame() {
        //todo fix

        return true;
    }

    public abstract int[] getFaces();
}
