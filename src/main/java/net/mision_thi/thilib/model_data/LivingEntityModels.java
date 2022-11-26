package net.mision_thi.thilib.model_data;

import com.google.gson.*;
import com.google.gson.JsonElement;
import io.github.apace100.calio.data.MultiJsonDataLoader;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.mision_thi.thilib.data.thilibDataTypes;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                    User.Test task = (User.Test) thilibDataTypes.MODELDATA.read(je);
                    System.out.println(task);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
