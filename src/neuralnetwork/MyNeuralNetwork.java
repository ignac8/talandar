package neuralnetwork;

import neuralnetwork.neuron.CalculableNeuron;
import neuralnetwork.neuron.InputNeuron;
import neuralnetwork.neuron.Neuron;
import neuralnetwork.neuron.SigmoidNeuron;
import neuralnetwork.neuron.StepNeuron;

import java.util.ArrayList;
import java.util.List;

import static utils.RandomUtils.nextGaussian;

//Fully connected, sigmoid hidden layer, step output layer, neural network
public class MyNeuralNetwork extends NeuralNetwork {

    protected List<List<CalculableNeuron>> hiddenLayers;
    protected List<List<List<Connection>>> connectionsListList;

    protected MyNeuralNetwork() {
        super();
        hiddenLayers = new ArrayList<>();
        connectionsListList = new ArrayList<>();
    }

    public MyNeuralNetwork(int inputLayerSize, List<Integer> hiddenLayerSizes, int outputLayerSize) {
        this();
        setUpClearNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize);
    }

    public MyNeuralNetwork(MyNeuralNetwork myNeuralNetwork) {
        this();
        List<Integer> hiddenLayersSizes = new ArrayList<>();
        for (List<CalculableNeuron> hiddenLayer : myNeuralNetwork.getHiddenLayers()) {
            hiddenLayersSizes.add(hiddenLayer.size());
        }
        setUpClearNeuralNetwork(myNeuralNetwork.getInputLayer().size(), hiddenLayersSizes, myNeuralNetwork.getOutputLayer().size());
        cloneNeuralNetworkParameters(myNeuralNetwork);
    }

    public MyNeuralNetwork(int inputLayerSize, List<Integer> hiddenLayerSizes, int outputLayerSize, double std, double bias) {
        this();
        setUpClearNeuralNetwork(inputLayerSize, hiddenLayerSizes, outputLayerSize);
        setUpRandomParameters(std, bias);
    }


    private void setUpClearNeuralNetwork(int inputLayerSize, List<Integer> hiddenLayerSizes, int outputLayerSize) {
        for (int counter = 0; counter < inputLayerSize; counter++) {
            inputLayer.add(new InputNeuron());
        }
        List<List<Connection>> connectionsList = new ArrayList<>();
        List<CalculableNeuron> hiddenLayer = new ArrayList<>();
        for (int i = 0; i < hiddenLayerSizes.get(0); i++) {
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

        for (int i = 1; i < hiddenLayerSizes.size(); i++) {
            hiddenLayer = new ArrayList<>();
            connectionsList = new ArrayList<>();
            for (int j = 0; j < hiddenLayerSizes.get(i); j++) {
                CalculableNeuron neuron = new SigmoidNeuron();
                hiddenLayer.add(neuron);
                List<Connection> connections = neuron.getConnections();
                for (int k = 0; k < hiddenLayerSizes.get(i - 1); k++) {
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
            CalculableNeuron neuron = new StepNeuron();
            outputLayer.add(neuron);
            List<Connection> connections = neuron.getConnections();
            for (int j = 0; j < hiddenLayerSizes.get(hiddenLayerSizes.size() - 1); j++) {
                Connection connection = new Connection(hiddenLayers.get(hiddenLayerSizes.size() - 1).get(j));
                connections.add(connection);
            }
            connectionsList.add(connections);
        }
        connectionsListList.add(connectionsList);
    }

    private void cloneNeuralNetworkParameters(MyNeuralNetwork myNeuralNetwork) {
        for (int i = 0; i < outputLayer.size(); i++) {
            outputLayer.get(i).setBias(
                    myNeuralNetwork.getOutputLayer().get(i).getBias());
        }
        for (int i = 0; i < hiddenLayers.size(); i++) {
            for (int j = 0; j < hiddenLayers.get(i).size(); j++) {
                hiddenLayers.get(i).get(j).setBias(
                        myNeuralNetwork.getHiddenLayers().get(i).get(j).getBias());
            }
        }
        for (int i = 0; i < connectionsListList.size(); i++) {
            for (int j = 0; j < connectionsListList.get(i).size(); j++) {
                for (int k = 0; k < connectionsListList.get(i).get(j).size(); k++) {
                    connectionsListList.get(i).get(j).get(k).setWeight(
                            myNeuralNetwork.getConnectionsListList().get(i).get(j).get(k).getWeight());
                }
            }
        }
    }

    private void setUpRandomParameters(double std, double mean) {
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


    public List<List<CalculableNeuron>> getHiddenLayers() {
        return hiddenLayers;
    }

    public List<List<List<Connection>>> getConnectionsListList() {
        return connectionsListList;
    }

    @Override
    public void calculateOutput() {
        for (List<CalculableNeuron> hiddenLayer : hiddenLayers) {
            for (Neuron neuron : hiddenLayer) {
                neuron.calculate();
            }
        }
        for (Neuron neuron : outputLayer) {
            neuron.calculate();
        }
    }
}
