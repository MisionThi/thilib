package net.mision_thi.thilib.registry;

import net.minecraft.util.Identifier;
import net.mision_thi.thilib.model_data.ModelData;

import java.util.HashMap;
import java.util.Map;

public class ModelDataRegistry {
    private static final HashMap<Identifier, ModelData> idToModelData = new HashMap<>();

    public static ModelData register(Identifier id, ModelData UserTest) {
        return UserTest;
    }

    public static int size() {
        return idToModelData.size();
    }

    public static Iterable<Map.Entry<Identifier, ModelData>> entries() {
        return idToModelData.entrySet();
    }
}
