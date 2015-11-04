package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by taras on 26.10.2015.
 */
public class FirstTask {
    public static final Logger log = LogManager.getLogger(FirstTask.class);

    public static void main(String[] args) {
        RandArray ra = new RandArray(100);
        log.trace(ra.showResultsToLog());
    }
}
