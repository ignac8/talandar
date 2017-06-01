package fitnessevaluator;

import player.JarcraftPlayer;

public interface FitnessEvaluator {

    double evaluate();

    JarcraftPlayer getFirstPlayer();

    JarcraftPlayer getSecondPlayer();
}
