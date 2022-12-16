package net.mision_thi.thilib.model_data;

import com.google.gson.*;
import com.google.gson.JsonElement;
import io.github.apace100.calio.data.MultiJsonDataLoader;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.mision_thi.thilib.ThiLib;
import net.mision_thi.thilib.data.thilibDataTypes;
import net.mision_thi.thilib.registry.ModelDataRegistry;

import java.util.List;
import java.util.Map;

public class LivingEntityModels extends MultiJsonDataLoader implements IdentifiableResourceReloadListener {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    public LivingEntityModels() {
        super(GSON, "model_data");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("thilib", "model_data");
    }

    @Override
    protected void apply(Map<Identifier, List<JsonElement>> prepared, ResourceManager manager, Profiler profiler) {
        prepared.forEach((id, jel) -> {
            for (JsonElement je : jel) {
                try {
//                    ThiLib.LOGGER.info("data models found:",je);
                    ModelData model_data = (ModelData) thilibDataTypes.MODELDATA.read(je);
                    ThiLib.LOGGER.info("file found:");



                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        for (Map.Entry<Identifier, ModelData> value : ModelDataRegistry.entries()) {
            ModelData model_data = value.getValue();
        }
        ThiLib.LOGGER.info("Finished loading Modeldata(s): ", ModelDataRegistry.size());

//        prepared.forEach((id, jel) -> {
//            for (JsonElement je : jel) {
//                try {
//                    ModelData task = (ModelData) thilibDataTypes.MODELDATA.read(je);
//                    ThiLib.LOGGER.info("Finished Loading model data. number loaded: " + ModelDataRegistry.size());
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });

    }
}
