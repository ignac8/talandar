import neuralnetwork.Connection;
import neuralnetwork.MyNeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.InputNeuron;
import neuralnetwork.neuron.Neuron;
import neuralnetwork.neuron.SigmoidNeuron;
import org.apache.commons.math3.analysis.function.Sigmoid;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.RandomUtils.nextRealDouble;

public class NeuralNetworkTests {

    @Test
    public void calculationTest() {
        List<Integer> hiddenLayersSizes = new ArrayList<>();
        hiddenLayersSizes.add(1);
        MyNeuralNetwork neuralNetwork = new MyNeuralNetwork(1, hiddenLayersSizes, 1);

        neuralNetwork.getHiddenLayers().get(0).get(0).getConnections().get(0).setWeight(1);
        neuralNetwork.getOutputLayer().get(0).getConnections().get(0).setWeight(1);
        neuralNetwork.getOutputLayer().get(0).setBias(-0.75);

        neuralNetwork.getInputLayer().get(0).setValue(0);
        neuralNetwork.calculateOutput();
        assertThat(neuralNetwork.getOutputLayer().get(0).getValue(), is(0.0));

        neuralNetwork.getInputLayer().get(0).setValue(10);
        neuralNetwork.calculateOutput();
        assertThat(neuralNetwork.getOutputLayer().get(0).getValue(), is(1.0));


    }

    @Test
    public void neuralNetworkCopyTest() {
        List<Integer> hiddenLayersSizes = new ArrayList<>();
        hiddenLayersSizes.add(100);
        hiddenLayersSizes.add(100);
        hiddenLayersSizes.add(100);
        MyNeuralNetwork neuralNetwork = new MyNeuralNetwork(10, hiddenLayersSizes, 10);
        for (int i = 0; i < neuralNetwork.getInputLayer().size(); i++) {
            neuralNetwork.getInputLayer().get(i)
                    .setValue(nextRealDouble(-1000.0, 1000.0));
        }
        for (int i = 0; i < neuralNetwork.getOutputLayer().size(); i++) {
            neuralNetwork.getOutputLayer().get(i)
                    .setBias(nextRealDouble(-1000.0, 1000.0));
        }
        for (int i = 0; i < neuralNetwork.getHiddenLayers().size(); i++) {
            for (int j = 0; j < neuralNetwork.getHiddenLayers().get(i).size(); j++) {
                neuralNetwork.getHiddenLayers().get(i).get(j)
                        .setBias(nextRealDouble(-1000.0, 1000.0));
            }
        }
        for (int i = 0; i < neuralNetwork.getConnectionsListList().size(); i++) {
            for (int j = 0; j < neuralNetwork.getConnectionsListList().get(i).size(); j++) {
                for (int k = 0; k < neuralNetwork.getConnectionsListList().get(i).get(j).size(); k++) {
                    neuralNetwork.getConnectionsListList().get(i).get(j).get(k)
                            .setWeight(nextRealDouble(-1000.0, 1000.0));
                }
            }
        }

        MyNeuralNetwork copiedNeuralNetwork = new MyNeuralNetwork(neuralNetwork);
        for (int i = 0; i < neuralNetwork.getOutputLayer().size(); i++) {
            assertThat(neuralNetwork.getOutputLayer().get(i).getBias(),
                    is(copiedNeuralNetwork.getOutputLayer().get(i).getBias()));
        }
        for (int i = 0; i < neuralNetwork.getHiddenLayers().size(); i++) {
            for (int j = 0; j < neuralNetwork.getHiddenLayers().get(i).size(); j++) {
                assertThat(neuralNetwork.getHiddenLayers().get(i).get(j).getBias(),
                        is(copiedNeuralNetwork.getHiddenLayers().get(i).get(j).getBias()));
            }
        }
        for (int i = 0; i < neuralNetwork.getConnectionsListList().size(); i++) {
            for (int j = 0; j < neuralNetwork.getConnectionsListList().get(i).size(); j++) {
                for (int k = 0; k < neuralNetwork.getConnectionsListList().get(i).get(j).size(); k++) {
                    assertThat(neuralNetwork.getConnectionsListList().get(i).get(j).get(k).getWeight(),
                            is(copiedNeuralNetwork.getConnectionsListList().get(i).get(j).get(k).getWeight()));
                }
            }
        }

    }

    @Test
    public void sigmoidNeuronTest() {
        sigmoidCalculation(1, 1, 1, 1, 1);
        sigmoidCalculation(1, 1, 0, 0, 0);
        sigmoidCalculation(0, 0, 0, 0, 0);
    }

    private void sigmoidCalculation(double value1, double value2, double weight1, double weight2, double bias) {
        InputNeuron inputNeuron1 = new InputNeuron();
        InputNeuron inputNeuron2 = new InputNeuron();
        inputNeuron1.setValue(value1);
        inputNeuron2.setValue(value2);
        CalculableNeuron sigmoidNeuron = new SigmoidNeuron();
        List<Connection> connections = sigmoidNeuron.getConnections();
        Connection connection1 = new Connection(inputNeuron1);
        Connection connection2 = new Connection(inputNeuron2);
        connection1.setWeight(weight1);
        connection2.setWeight(weight2);
        connections.add(connection1);
        connections.add(connection2);
        sigmoidNeuron.setBias(bias);
        sigmoidNeuron.calculate();
        Sigmoid sigmoid = new Sigmoid();
        double expectedValue = sigmoid.value(value1 * weight1 + value2 * weight2 + bias);
        assertThat(sigmoidNeuron.getValue(), is(expectedValue));
    }

    @Test
    public void connectionsTest() {
        List<Integer> hiddenLayersSizes = new ArrayList<>();
        hiddenLayersSizes.add(100);
        hiddenLayersSizes.add(100);
        hiddenLayersSizes.add(100);
        MyNeuralNetwork neuralNetwork = new MyNeuralNetwork(10, hiddenLayersSizes, 10);
        List<InputNeuron> inputLayer = neuralNetwork.getInputLayer();
        List<List<CalculableNeuron>> hiddenLayers = neuralNetwork.getHiddenLayers();
        List<CalculableNeuron> outputLayer = neuralNetwork.getOutputLayer();
        layerTest(hiddenLayers.get(0), inputLayer);
        for (int i = 1; i < hiddenLayers.size(); i++) {
            layerTest(hiddenLayers.get(i), hiddenLayers.get(i - 1));
        }
        layerTest(outputLayer, hiddenLayers.get(hiddenLayers.size() - 1));
    }

    private <T extends Neuron> void layerTest(List<CalculableNeuron> layerTestedFor, List<T> layerTestedAgainst) {
        for (CalculableNeuron neuron : layerTestedFor) {
            List<Connection> connections = neuron.getConnections();
            for (int i = 0; i < connections.size(); i++) {
                Connection connection = connections.get(i);
                assertThat(connection.getNeuron() == layerTestedAgainst.get(i), is(true));
            }
        }

    }

}
