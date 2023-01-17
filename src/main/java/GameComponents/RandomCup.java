package GameComponents;

import java.util.Random;

public class RandomCup extends Cup2 {
    private int [] faces;
    private final Random random
            ;

    public RandomCup(Random random) {
        this.random = random;
    }

    @Override
    public Cup2 roll() {
        faces = new int[] {random.nextInt(6), random.nextInt(6)};
        return this;
    }

    @Override
    public int[] getFaces() {
        return faces;
    }
}
