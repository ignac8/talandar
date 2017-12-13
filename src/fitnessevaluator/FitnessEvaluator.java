package fitnessevaluator;

import static java.lang.Math.pow;

public abstract class FitnessEvaluator<Unit> {

    public double evaluate() {
        GameResult<Unit> gameResult = playGame();
        double fitness = rateGame(gameResult);
        return fitness;
    }

    protected abstract GameResult<Unit> playGame();

    private double rateGame(GameResult<Unit> gameResult) {
        double playerFitness = 0;
        double playerMaxFitness = 0;
        double enemyFitness = 0;
        double enemyMaxFitness = 0;

        for (Unit unit : gameResult.getCurrentUnits()) {
            double unitWorth = getMineralPrice(unit) + 2 * getGasPrice(unit);
            double currentDurability = getHitPoints(unit) + getShields(unit);
            double maxDurability = getMaxHitPoints(unit) + getMaxShields(unit);
            double adjustedUnitWorth = pow((currentDurability / maxDurability), 0.75) * unitWorth;
            switch (getPlayerId(unit)) {
                case 0:
                    playerFitness += adjustedUnitWorth;
                    break;
                case 1:
                    enemyFitness += adjustedUnitWorth;
                    break;
            }
        }
        for (Unit unit : gameResult.getStartingUnits()) {
            double unitWorth = getMineralPrice(unit) + 2 * getGasPrice(unit);
            switch (getPlayerId(unit)) {
                case 0:
                    playerMaxFitness += unitWorth;
                    break;
                case 1:
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
