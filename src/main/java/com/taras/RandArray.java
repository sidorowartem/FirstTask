package com.taras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class RandArray {
    public static final Logger log = LogManager.getLogger(RandArray.class);
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private int[] randomArray;
    private Random rand = new Random();
    private Map<Integer, Integer> numbsWithCounters = new HashMap<Integer, Integer>();
    private int[] numbs = {1, 3, 5, 7, 9};

    private void resetCounters() {
        for (int number : numbs) {
            numbsWithCounters.put(number, 0);
        }
    }
    private void init() {
        resetCounters();
        for (int i = 0; i < randomArray.length; i++) {
            int randomIndex = rand.nextInt(numbs.length);
            //if you are using variable more than once - it's better to make a separate link
            int value = numbs[randomIndex];
            randomArray[i] = value;
            numbsWithCounters.put(value, numbsWithCounters.get(value) + 1);
            //small hint - logger CAN use pattern :-)
            log.trace(value);
        }
    }

    RandArray() {
        randomArray = new int[rand.nextInt()];
    }

    RandArray(int size) {
        randomArray = new int[size];
    }

    public String getResults() {
        init();
        StringBuilder res = new StringBuilder("Final result:" + LINE_SEPARATOR);
        /*
        for (int i = 0; i < numbs.length; i++) {
            //if you are using variable more than once - it's better to make a separate link
            int key = numbs[i];
            //Use StringBuilder this way - it's much better. Direct string concatenation is much more slow.
            res.append("Number \"")
                    .append(key)
                    .append("\" has been written ")
                    .append(numbsWithCounters.get(key))
                    .append(" times.")
                    .append(separator);
        }
        */
        //Iterate over set - it's a bit more powerful, in case you don't need current index. Please read about Iterator class.
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
