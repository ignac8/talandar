package utils.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import neuralnetwork.Connection;
import neuralnetwork.FCSNeuralNetwork;
import neuralnetwork.neuron.CalculableNeuron;

import java.lang.reflect.Type;
import java.util.List;

public class FCSNeuralNetworkSerializer implements JsonSerializer<FCSNeuralNetwork>, JsonDeserializer<FCSNeuralNetwork> {

    @Override
    public FCSNeuralNetwork deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String typeString = rootObject.get("type").getAsString();
        try {
            type = Class.forName(typeString);
            return jsonDeserializationContext.deserialize(jsonElement, type);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public JsonElement serialize(FCSNeuralNetwork fcsNeuralNetwork, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject rootObject = new JsonObject();

        rootObject.addProperty("type", type.getTypeName());

        rootObject.addProperty("inputSize", fcsNeuralNetwork.getInputLayer().size());

        rootObject.addProperty("outputSize", fcsNeuralNetwork.getInputLayer().size());
        JsonArray outputBias = new JsonArray();
        rootObject.add("outputBias", outputBias);
        for (CalculableNeuron neuron : fcsNeuralNetwork.getOutputLayer()) {
            outputBias.add(neuron.getBias());
        }

        JsonArray hiddenBias = new JsonArray();
        rootObject.add("hiddenBias", hiddenBias);
        JsonArray hiddenSize = new JsonArray();
        rootObject.add("hiddenSize", hiddenSize);
        for (List<CalculableNeuron> hiddenLayer : fcsNeuralNetwork.getHiddenLayers()) {
            hiddenSize.add(hiddenLayer.size());
            JsonArray hiddenLayerBias = new JsonArray();
            hiddenBias.add(hiddenLayerBias);
            for (CalculableNeuron neuron : hiddenLayer) {
                hiddenLayerBias.add(neuron.getBias());
            }
        }

        JsonArray weights = new JsonArray();
        rootObject.add("weights", weights);
        for (List<List<Connection>> connectionsList : fcsNeuralNetwork.getConnectionsListList()) {
            JsonArray subWeights = new JsonArray();
            weights.add(subWeights);
            for (List<Connection> connections : connectionsList) {
                JsonArray subSubWeights = new JsonArray();
                subWeights.add(subSubWeights);
                for (Connection connection: connections) {
                    subSubWeights.add(connection.getWeight());
                }
            }

        }

        return rootObject;
    }
}
