package fitnessevaluator;

public interface FitnessEvaluator<T> {

    double evaluate();

    T getFirstPlayer();

    T getSecondPlayer();

}
