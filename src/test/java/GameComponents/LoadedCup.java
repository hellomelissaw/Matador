package GameComponents;

import java.util.ArrayList;
import java.util.List;

public class LoadedCup extends Cup2 {
    private final ArrayList<int[]> rolls;

    private int kastIndex = -1;

    public LoadedCup(ArrayList<int[]> rolls) {
        this.rolls = rolls;
    }

    @Override
    public Cup2 roll() {

        kastIndex++;
        if(kastIndex >= rolls.size()){
            kastIndex = 0;
        }

        return this;
    }

    @Override
    public int[] getFaces() {
        return rolls.get(kastIndex);

    }
}
