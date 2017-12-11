package fitnessevaluator;

import java.util.Collection;

public class GameResult<Unit> {

    private Collection<Unit> startingUnits;
    private Collection<Unit> currentUnits;

    public GameResult(Collection<Unit> startingUnits, Collection<Unit> currentUnits) {
        this.startingUnits = startingUnits;
        this.currentUnits = currentUnits;
    }

    public Collection<Unit> getStartingUnits() {
        return startingUnits;
    }

    public Collection<Unit> getCurrentUnits() {
        return currentUnits;
    }
}
