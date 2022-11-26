package net.mision_thi.thilib.registry;

import net.minecraft.util.Identifier;
import net.mision_thi.thilib.model_data.User;

import java.util.HashMap;

public class ModelDataRegistry {
    private static final HashMap<Identifier, User.Test> idToSkill = new HashMap<>();

    public static User.Test register(Identifier id, User.Test UserTest) {
        return UserTest;
    }
}
