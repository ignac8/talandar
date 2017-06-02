package fitnessevaluator;

import bwmcts.sparcraft.players.Player;

public interface FitnessEvaluator {

    double evaluate();

    Player getFirstPlayer();

    Player getSecondPlayer();


}
