package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class RandArray {
    public static final Logger log = LogManager.getLogger(RandArray.class);
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private int[] randomArray;
    private Random rand = new Random();
    private Map<Integer, Integer> numbsWithCounters = new HashMap<>();
    private int[] numbs = {1, 3, 5, 7, 9};

    private void resetCounters() {
        for (int number : numbs) {
            numbsWithCounters.put(number, 0);
        }
    }
    public void process() {
        resetCounters();
        for (int i = 0; i < randomArray.length; i++) {
            int randomIndex = rand.nextInt(numbs.length);
            int value = numbs[randomIndex];
            randomArray[i] = value;
            numbsWithCounters.put(value, numbsWithCounters.get(value) + 1);
            log.trace(value);
        }
    }

    RandArray(int size) {
        randomArray = new int[size];
    }

    public String getResults() {
        StringBuilder res = new StringBuilder("Final result:" + LINE_SEPARATOR);
       for(Integer key: numbsWithCounters.keySet()) {
            res.append("Number \"")
                    .append(key)
                    .append("\" has been written ")
                    .append(numbsWithCounters.get(key))
                    .append(" times.")
                    .append(LINE_SEPARATOR);
        }
        return res.toString();
    }
}
