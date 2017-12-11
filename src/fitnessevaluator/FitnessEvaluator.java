package fitnessevaluator;

import java.util.Collection;

import static java.lang.Math.pow;

public abstract class FitnessEvaluator<Unit> {

    public double evaluate() {
        Collection<Unit> units = playGame();
        double fitness = rateGame(units);
        return fitness;
    }

    protected abstract Collection<Unit> playGame();

    private double rateGame(Collection<Unit> units) {
        double playerFitness = 0;
        double playerMaxFitness = 0;
        double enemyFitness = 0;
        double enemyMaxFitness = 0;

        for (Unit unit : units) {
            double unitWorth = getMineralPrice(unit) + 2 * getGasPrice(unit);
            double currentDurability = getHitPoints(unit) + getShields(unit);
            double maxDurability = getMaxHitPoints(unit) + getMaxShields(unit);
            double adjustedUnitWorth = pow((currentDurability / maxDurability), 0.75) * unitWorth;
            switch (getPlayerId(unit)) {
                case 0:
                    playerFitness += adjustedUnitWorth;
                    playerMaxFitness += unitWorth;
                    break;
                case 1:
                    enemyFitness += adjustedUnitWorth;
                    enemyMaxFitness += unitWorth;
                    break;
            }
        }
        double result = playerFitness / playerMaxFitness - enemyFitness / enemyMaxFitness;
        return result;
    }

    protected abstract double getMineralPrice(Unit unit);

    protected abstract double getGasPrice(Unit unit);

    protected abstract double getHitPoints(Unit unit);

    protected abstract double getShields(Unit unit);

    protected abstract double getMaxHitPoints(Unit unit);

    protected abstract double getMaxShields(Unit unit);

    protected abstract int getPlayerId(Unit unit);


}
