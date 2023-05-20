package net.mision_thi.thilib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.mision_thi.thilib.model_data.LivingEntityModels;
import net.mision_thi.thilib.model_data.ModelData;
import net.mision_thi.thilib.registry.ModelDataRegistry;
import net.mision_thi.thilib.registry.ThilibPowerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//import static io.github.apace100.apoli.Apoli.server;

public class ThiLib implements ModInitializer {


	@Override
	public void onInitialize() {	// When the program is run
		ThiLib.LOGGER.info("Welcome " + MODID);
		ThilibPowerFactory.register();  //registers the PowerFactories class

//		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new LivingEntityModels() {
//			@Override
//			public Identifier getFabricId() {
//				return new Identifier("thilib","model_data");
//			}
//
//			@Override
//			public void reload(ResourceManager manager) {
//				for(Identifier id : manager.findResources("model_data", path -> path.endsWith(".json"))) {
//					try(InputStream stream = manager.getResource(id).) {
//						// Consume the stream however you want, medium, rare, or well done.
//					} catch(Exception e) {
//						ThiLib.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
//					}
//				}
//			}
//		});

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new LivingEntityModels());

		ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
			Map<Identifier, ModelData> map = new HashMap<>();
			ModelDataRegistry.entries().forEach(identifierModelDataEntry -> map.put(identifierModelDataEntry.getKey(),identifierModelDataEntry.getValue()));
		});

	}

	public static final String MODID = "thilib"; //creates a string variable called MODID and sets it to the name of the mod
	public static final Logger LOGGER = LogManager.getLogger(MODID); //creates a logger, for use in

	public static Identifier identifier(String path){ //A method that can be easily called when wanting to create an identifier
		return new Identifier(MODID, path);
	}
}
