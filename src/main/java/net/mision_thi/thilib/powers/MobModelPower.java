package net.mision_thi.thilib.powers;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.ClassUtil;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


import net.mision_thi.thilib.ThiLib;

import static io.github.apace100.calio.data.SerializableDataType.registry;

public class MobModelPower extends Power {

    private final EntityType<?> entityType;
    public LivingEntity modelEntity;

    public MobModelPower(PowerType<?> type, LivingEntity livingEntity, EntityType<?> entityType) {
        super(type, livingEntity);
        this.entityType = entityType;
        //this.modelEntity = (LivingEntity) Registry.ENTITY_TYPE.get(new Identifier("minecraft:wolf")).create(livingEntity.world);
        this.modelEntity = (LivingEntity) entityType.create(livingEntity.world);

        //ThiLib.LOGGER.info(Registry.ENTITY_TYPE.get(new Identifier("minecraft:wolf")).create(livingEntity.world));

    }

    public EntityType<?> getEntityType() {
        return entityType;

    }

    public static PowerFactory<?> createFactory() {
        return new PowerFactory<>(ThiLib.identifier("wolf_form"),
            new SerializableData()
                .add("entity_type", SerializableDataTypes.ENTITY_TYPE, null),
            data ->
                (powerType, livingEntity) -> new MobModelPower(
                    powerType,
                    livingEntity,
                    data.get("entity_type")
                )
        ).allowCondition();
    }
}
