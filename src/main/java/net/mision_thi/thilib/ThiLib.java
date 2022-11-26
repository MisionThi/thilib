package net.mision_thi.thilib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.mision_thi.thilib.model_data.LivingEntityModels;
import net.mision_thi.thilib.registry.ThilibPowerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThiLib implements ModInitializer {


	@Override
	public void onInitialize() {	// When the program is run
		ThiLib.LOGGER.info("Welcome " + MODID);
		ThilibPowerFactory.register();  //registers the PowerFactories class
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new LivingEntityModels());

	}

	public static final String MODID = "thilib"; //creates a string variable called MODID and sets it to the name of the mod
	public static final Logger LOGGER = LogManager.getLogger(MODID); //creates a logger, for use in

	public static Identifier identifier(String path){ //A method that can be easily called when wanting to create an identifier
		return new Identifier(MODID, path);
	}
}
