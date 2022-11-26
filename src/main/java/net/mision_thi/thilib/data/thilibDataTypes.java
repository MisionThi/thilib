package net.mision_thi.thilib.data;

import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.mision_thi.thilib.model_data.ModelData;

public class thilibDataTypes {


    public static final SerializableDataType<?> MODELDATA = SerializableDataType.compound(
        ModelData.class,
        new SerializableData()
            .add("name", SerializableDataTypes.STRING),

        (data) -> {
            String name = data.get("name");

            return null;
        },
            ((serializableData, UserTest) -> {
                SerializableData.Instance data = serializableData.new Instance();
                data.set("name",UserTest.getName());

                return data;
            })
    );
}
