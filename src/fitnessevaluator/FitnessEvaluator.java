package fitnessevaluator;

import player.MyPlayer;

public interface FitnessEvaluator {

    double evaluate();

    MyPlayer getFirstPlayer();

    MyPlayer getSecondPlayer();
}
