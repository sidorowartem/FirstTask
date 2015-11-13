package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FirstTask {
    public static final Logger log = LogManager.getLogger(FirstTask.class);

    public static void main(String[] args) {
        RandArray randArray = new RandArray(100);
        randArray.process();
        log.trace(randArray.getResults());
    }
}
