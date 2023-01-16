package GameComponents;

import java.util.List;

public class LoadedCup extends Cup2 {
    private final List<int[]> rolls;

    public LoadedCup(List<int[]> rolls) {
        this.rolls = rolls;
    }

    @Override
    public Cup2 roll() {
        rolls.remove(0);
        return this;
    }

    @Override
    public int[] getFaces() {
        return rolls.get(0);
    }
}
