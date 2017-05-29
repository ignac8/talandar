package neuralnetwork;

import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.InputNeuron;
import neuralnetwork.neuron.SigmoidNeuron;

import java.util.ArrayList;
import java.util.List;

import static utils.RandomUtils.nextGaussian;

//Fully connected sigmoid neural network
public final class FCSNeuralNetwork extends NeuralNetwork {

    //clear neural network constructor
    public FCSNeuralNetwork(int inputLayerSize, int[] hiddenLayerSizes, int outputLayerSize) {
        super();
        for (int counter = 0; counter < inputLayerSize; counter++) {
            inputLayer.add(new InputNeuron());
        }
        List<List<Connection>> connectionsList = new ArrayList<>();
        List<CalculableNeuron> hiddenLayer = new ArrayList<>();
        for (int i = 0; i < hiddenLayerSizes[0]; i++) {
            CalculableNeuron neuron = new SigmoidNeuron();
            hiddenLayer.add(neuron);
            List<Connection> connections = neuron.getConnections();
            for (int j = 0; j < inputLayerSize; j++) {
                Connection connection = new Connection(inputLayer.get(j));
                connections.add(connection);
            }
            connectionsList.add(connections);

        }
        hiddenLayers.add(hiddenLayer);
        connectionsListList.add(connectionsList);

        for (int i = 1; i < hiddenLayerSizes.length; i++) {
            hiddenLayer = new ArrayList<>();
            connectionsList = new ArrayList<>();
            for (int j = 0; j < hiddenLayerSizes[i]; j++) {
                CalculableNeuron neuron = new SigmoidNeuron();
                hiddenLayer.add(neuron);
                List<Connection> connections = neuron.getConnections();
                for (int k = 0; k < hiddenLayerSizes[i - 1]; k++) {
                    Connection connection = new Connection(hiddenLayers.get(i - 1).get(k));
                    connections.add(connection);
                }
                connectionsList.add(connections);
            }
            hiddenLayers.add(hiddenLayer);
            connectionsListList.add(connectionsList);
        }

        connectionsList = new ArrayList<>();
        for (int i = 0; i < outputLayerSize; i++) {
            CalculableNeuron neuron = new SigmoidNeuron();
            outputLayer.add(neuron);
            List<Connection> connections = neuron.getConnections();
            for (int j = 0; j < hiddenLayerSizes[hiddenLayerSizes.length - 1]; j++) {
                Connection connection = new Connection(hiddenLayers.get(hiddenLayerSizes.length - 1).get(j));
                connections.add(connection);
            }
            connectionsList.add(connections);
        }
        connectionsListList.add(connectionsList);
    }

    //random neural network constructor
    public FCSNeuralNetwork(int inputLayerSize, int[] hiddenLayerSizes, int outputLayerSize, double std, double mean) {
        this(inputLayerSize, hiddenLayerSizes, outputLayerSize);
        for (int i = 0; i < outputLayer.size(); i++) {
            outputLayer.get(i).setBias(
                    nextGaussian(std, mean));
        }
        for (int i = 0; i < hiddenLayers.size(); i++) {
            for (int j = 0; j < hiddenLayers.get(i).size(); j++) {
                hiddenLayers.get(i).get(j).setBias(
                        nextGaussian(std, mean));
            }
        }
        for (int i = 0; i < connectionsListList.size(); i++) {
            for (int j = 0; j < connectionsListList.get(i).size(); j++) {
                for (int k = 0; k < connectionsListList.get(i).get(j).size(); k++) {
                    connectionsListList.get(i).get(j).get(k).setWeight(
                            nextGaussian(std, mean));
                }
            }
        }
    }

    @Override
    public NeuralNetwork copy() {
        List<List<CalculableNeuron>> hiddenLayers = getHiddenLayers();
        int[] hiddenLayersSizes = new int[hiddenLayers.size()];
        for (int counter = 0; counter < hiddenLayers.size(); counter++) {
            List<CalculableNeuron> hiddenLayer = hiddenLayers.get(counter);
            hiddenLayersSizes[counter] = hiddenLayer.size();
        }

        NeuralNetwork newNeuralNetwork = new FCSNeuralNetwork(inputLayer.size(), hiddenLayersSizes, outputLayer.size());

        for (int i = 0; i < outputLayer.size(); i++) {
            newNeuralNetwork.outputLayer.get(i).setBias(
                    getOutputLayer().get(i).getBias());
        }
        for (int i = 0; i < this.hiddenLayers.size(); i++) {
            for (int j = 0; j < this.hiddenLayers.get(i).size(); j++) {
                newNeuralNetwork.hiddenLayers.get(i).get(j).setBias(
                        hiddenLayers.get(i).get(j).getBias());
            }
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