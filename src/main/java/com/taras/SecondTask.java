package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class SecondTask {
    public static final Logger log = LogManager.getLogger(FirstTask.class);

    public static void main(String[] args) {
        Extracter extracter = new Extracter();
        StringBuilder path = new StringBuilder("src")
                .append(Extracter.FILE_SEPARATOR)
                .append("main")
                .append(Extracter.FILE_SEPARATOR)
                .append("resources")
                .append(Extracter.FILE_SEPARATOR)
                .append("root_folder.zip");
        try {
            extracter.enterZipAndRead(path.toString());
            extracter.writeToFile();

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
