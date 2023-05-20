package net.mision_thi.thilib.model_data;

import net.mision_thi.thilib.ThiLib;

public class ModelData {
    private String name;


    ModelData(String name) {
        ThiLib.LOGGER.info("modeldata");
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
