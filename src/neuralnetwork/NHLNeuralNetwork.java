package neuralnetwork;

import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.ConstantNeuron;
import neuralnetwork.neuron.FastSigmoidNeuron;

import java.util.ArrayList;
import java.util.List;

import static util.RandomUtils.nextGaussian;

//No hidden layers neural network
public final class NHLNeuralNetwork extends NeuralNetwork {

    //random neural network constructor
    public NHLNeuralNetwork(int inputLayerSize, int outputLayerSize, double std, double mean) {
        this(inputLayerSize, outputLayerSize);
        for (CalculableNeuron neuron : outputLayer) {
            neuron.setBias(nextGaussian(std, mean));
        }
        for (List<CalculableNeuron> hiddenLayer : hiddenLayers) {
            for (CalculableNeuron neuron : hiddenLayer) {
                neuron.setBias(nextGaussian(std, mean));
            }
        }
        for (List<List<Connection>> connectionsList : connectionsListList) {
            for (List<Connection> connections : connectionsList) {
                for (Connection connection : connections) {
                    connection.setWeight(nextGaussian(std, mean));
                }
            }
        }
    }

    //clear neural network constructor
    public NHLNeuralNetwork(int inputLayerSize, int outputLayerSize) {
        super();
        for (int counter = 0; counter < inputLayerSize; counter++) {
            inputLayer.add(new ConstantNeuron());
        }

        List<List<Connection>> connectionsList = new ArrayList<>();
        for (int i = 0; i < outputLayerSize; i++) {
            CalculableNeuron neuron = new FastSigmoidNeuron();
            outputLayer.add(neuron);
            List<Connection> connections = neuron.getConnections();
            for (int j = 0; j < inputLayerSize; j++) {
                Connection connection = new Connection(inputLayer.get(j));
                connections.add(connection);
            }
            connectionsList.add(connections);
        }
        connectionsListList.add(connectionsList);
    }

    @Override
    public NeuralNetwork copy() {
        List<List<CalculableNeuron>> hiddenLayers = getHiddenLayers();
        List<Integer> hiddenLayersSizes = new ArrayList<>(hiddenLayers.size());
        for (List<CalculableNeuron> hiddenLayer : hiddenLayers) {
            hiddenLayersSizes.add(hiddenLayer.size());
        }

        NeuralNetwork newNeuralNetwork = new NHLNeuralNetwork(inputLayer.size(), outputLayer.size());

        for (int i = 0; i < outputLayer.size(); i++) {
            newNeuralNetwork.outputLayer.get(i).setBias(
                    getOutputLayer().get(i).getBias());
        }
        for (int i = 0; i < connectionsListList.size(); i++) {
            for (int j = 0; j < connectionsListList.get(i).size(); j++) {
                for (int k = 0; k < connectionsListList.get(i).get(j).size(); k++) {
                    newNeuralNetwork.connectionsListList.get(i).get(j).get(k).setWeight(
                            getConnectionsListList().get(i).get(j).get(k).getWeight());
                }
            }
        }
        return newNeuralNetwork;
    }
}