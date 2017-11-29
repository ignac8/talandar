package util.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import neuralnetwork.NeuralNetwork;

import java.lang.reflect.Type;

public class NeuralNetworkSerializer implements JsonSerializer<NeuralNetwork>, JsonDeserializer<NeuralNetwork> {

    @Override
    public NeuralNetwork deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
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
    public JsonElement serialize(NeuralNetwork neuralNetwork, Type type, JsonSerializationContext jsonSerializationContext) {
        type = neuralNetwork.getClass();
        return jsonSerializationContext.serialize(neuralNetwork, type);
    }
}
