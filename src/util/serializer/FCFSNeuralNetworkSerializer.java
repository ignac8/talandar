package util.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import neuralnetwork.Connection;
import neuralnetwork.FCFSNeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.collect.Streams.stream;
import static java.util.stream.Collectors.toList;

public class FCFSNeuralNetworkSerializer implements JsonSerializer<FCFSNeuralNetwork>, JsonDeserializer<FCFSNeuralNetwork> {

    private static final String TYPE = "type";
    private static final String INPUT_SIZE = "inputSize";
    private static final String HIDDEN_SIZE = "hiddenSize";
    private static final String OUTPUT_SIZE = "outputSize";
    private static final String HIDDEN_BIAS = "hiddenBias";
    private static final String OUTPUT_BIAS = "outputBias";
    private static final String WEIGHTS = "weights";

    @Override
    public FCFSNeuralNetwork deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject rootObject = jsonElement.getAsJsonObject();
        int inputSize = rootObject.get(INPUT_SIZE).getAsInt();
        int outputSize = rootObject.get(OUTPUT_SIZE).getAsInt();
        JsonArray hiddenSizeJsonArray = rootObject.get(HIDDEN_SIZE).getAsJsonArray();
        List<Integer> hiddenSize = stream(hiddenSizeJsonArray).map(JsonElement::getAsInt).collect(toList());
        FCFSNeuralNetwork neuralNetwork = new FCFSNeuralNetwork(inputSize, hiddenSize, outputSize);

        IntStream
                .range(0, outputSize)
                .forEach(i -> neuralNetwork
                        .getOutputLayer()
                        .get(i)
                        .setBias(rootObject
                                .getAsJsonArray(OUTPUT_BIAS)
                                .get(i)
                                .getAsDouble()));

        IntStream
                .range(0, hiddenSize.size())
                .forEach(i -> IntStream
                        .range(0, hiddenSizeJsonArray
                                .get(i)
                                .getAsInt())
                        .forEach(j -> neuralNetwork
                                .getHiddenLayers()
                                .get(i)
                                .get(j)
                                .setBias(rootObject
                                        .getAsJsonArray(HIDDEN_BIAS)
                                        .get(i)
                                        .getAsJsonArray()
                                        .get(j)
                                        .getAsDouble())));

        IntStream
                .range(0, rootObject
                        .get(WEIGHTS)
                        .getAsJsonArray()
                        .size())
                .forEach(i -> IntStream
                        .range(0, rootObject
                                .get(WEIGHTS)
                                .getAsJsonArray()
                                .get(i)
                                .getAsJsonArray()
                                .size())
                        .forEach(j -> IntStream
                                .range(0, rootObject
                                        .get(WEIGHTS)
                                        .getAsJsonArray()
                                        .get(i)
                                        .getAsJsonArray()
                                        .get(j)
                                        .getAsJsonArray()
                                        .size())
                                .forEach(k -> neuralNetwork
                                        .getConnectionsListList()
                                        .get(i)
                                        .get(j)
                                        .get(k)
                                        .setWeight(rootObject
                                                .get(WEIGHTS)
                                                .getAsJsonArray()
                                                .get(i)
                                                .getAsJsonArray()
                                                .get(j)
                                                .getAsJsonArray()
                                                .get(k)
                                                .getAsDouble()))));
        return neuralNetwork;
    }

    @Override
    public JsonElement serialize(FCFSNeuralNetwork fcfsNeuralNetwork, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject rootObject = new JsonObject();

        rootObject.addProperty(TYPE, type.getTypeName());

        rootObject.addProperty(INPUT_SIZE, fcfsNeuralNetwork.getInputLayer().size());

        rootObject.addProperty(OUTPUT_SIZE, fcfsNeuralNetwork.getOutputLayer().size());
        JsonArray outputBias = new JsonArray();
        rootObject.add(OUTPUT_BIAS, outputBias);
        for (CalculableNeuron neuron : fcfsNeuralNetwork.getOutputLayer()) {
            outputBias.add(neuron.getBias());
        }

        JsonArray hiddenBias = new JsonArray();
        rootObject.add(HIDDEN_BIAS, hiddenBias);
        JsonArray hiddenSize = new JsonArray();
        rootObject.add(HIDDEN_SIZE, hiddenSize);
        for (List<CalculableNeuron> hiddenLayer : fcfsNeuralNetwork.getHiddenLayers()) {
            hiddenSize.add(hiddenLayer.size());
            JsonArray hiddenLayerBias = new JsonArray();
            hiddenBias.add(hiddenLayerBias);
            for (CalculableNeuron neuron : hiddenLayer) {
                hiddenLayerBias.add(neuron.getBias());
            }
        }

        JsonArray weights = new JsonArray();
        rootObject.add(WEIGHTS, weights);
        for (List<List<Connection>> connectionsList : fcfsNeuralNetwork.getConnectionsListList()) {
            JsonArray subWeights = new JsonArray();
            weights.add(subWeights);
            for (List<Connection> connections : connectionsList) {
                JsonArray subSubWeights = new JsonArray();
                subWeights.add(subSubWeights);
                for (Connection connection : connections) {
                    subSubWeights.add(connection.getWeight());
                }
            }

        }

        return rootObject;
    }
}
