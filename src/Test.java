import neuralnetwork.MyNeuralNetwork;
import neuralnetwork.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String... args) {
        int inputLayerSize = 2;
        int outputLayerSize = 1;
        List<Integer> hiddenLayerSizes = new ArrayList<>();
        hiddenLayerSizes.add(1);
        NeuralNetwork neuralNetwork = new MyNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize);
        neuralNetwork.getInputLayer().get(0).setValue(-1);
        neuralNetwork.getOutputLayer();
        neuralNetwork.calculate();
        System.out.println("Kurczak");
    }
}
