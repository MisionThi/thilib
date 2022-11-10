package net.mision_thi.thilib.powers;

import com.mojang.datafixers.util.Pair;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.mision_thi.thilib.ThiLib;

import java.util.function.Consumer;
import java.util.function.Predicate;


public class TestPower extends Power {


    private final Consumer<Entity> entityAction;
    private final Predicate<Pair<DamageSource, Float>> condition;

    public TestPower(PowerType<?> type, LivingEntity entity, Consumer<Entity> entityAction, Predicate<Pair<DamageSource, Float>> condition) {
        super(type, entity);
        this.entityAction = entityAction;
        this.condition = condition;
    }

    public boolean doesApply(DamageSource source, float amount) {
        return condition == null || condition.test(new Pair<>(source, amount));
    }

    public void executeAction() {
        entityAction.accept(entity);
    }

    public static PowerFactory getFactory() {
        return new PowerFactory<>(ThiLib.identifier("action_on_death"),
                new SerializableData()
                        .add("entity_action", ApoliDataTypes.ENTITY_ACTION, null)
                        .add("damage_condition", ApoliDataTypes.DAMAGE_CONDITION, null),
                data ->
                        (type, player) -> new TestPower(type, player,
                                data.get("entity_action"),
                                data.get("damage_condition")))
                .allowCondition();
    }


}
