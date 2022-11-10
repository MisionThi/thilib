package net.mision_thi.thilib.registry;

import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.util.registry.Registry;
import net.mision_thi.thilib.powers.*;

public class ThilibPowerFactory {

    public static void register(){
        register(MobModelPower.createFactory());
    }

    public static void register(PowerFactory<?> serializer) {
        Registry.register(ApoliRegistries.POWER_FACTORY, serializer.getSerializerId(), serializer);
    }
}
