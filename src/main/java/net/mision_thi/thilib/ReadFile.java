package net.mision_thi.thilib;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ReadFile {

    public File getFile() throws IOException {
        String inputFilePath = "test.json" ;
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(inputFilePath);
        // File path is passed as parameter
        File file = new File(resource.getFile());
        ThiLib.LOGGER.info(file.canRead());
        file.createNewFile();

        return file;
    }



}
