package net.mision_thi.thilib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.mision_thi.thilib.model_data.LivingEntityModels;
import net.mision_thi.thilib.model_data.ModelData;
import net.mision_thi.thilib.registry.ModelDataRegistry;
import net.mision_thi.thilib.registry.ThilibPowerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.github.apace100.apoli.Apoli.server;

public class ThiLib implements ModInitializer {


	@Override
	public void onInitialize() {	// When the program is run
		ThiLib.LOGGER.info("Welcome " + MODID);
		ThilibPowerFactory.register();  //registers the PowerFactories class
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new LivingEntityModels());

		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((((server, resourceManager, success) -> {
			for (ServerPlayerEntity serverPlayerEntity : server.getPlayerManager().getPlayerList()) {
				Map<Identifier, ModelData> map = new HashMap<>();
				ModelDataRegistry.entries().forEach(identifierModelDataEntry -> map.put(identifierModelDataEntry.getKey(),identifierModelDataEntry.getValue()));
			}
		})));

	}

	public static final String MODID = "thilib"; //creates a string variable called MODID and sets it to the name of the mod
	public static final Logger LOGGER = LogManager.getLogger(MODID); //creates a logger, for use in

	public static Identifier identifier(String path){ //A method that can be easily called when wanting to create an identifier
		return new Identifier(MODID, path);
	}
}
