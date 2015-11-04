package com.taras;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by taras on 26.10.2015.
 */
public class RandArray {
    public static final Logger log = LogManager.getLogger(RandArray.class);

    private int[] randomArray;
    private Random rand = new Random();
    private Map<Integer, Integer> numbsWithCounters = new HashMap<>();
    private int[] numbs = {1, 3, 5, 7, 9};

    private void init() {
        for (int i = 0; i < randomArray.length; i++) {
            int temp = rand.nextInt(numbs.length);
            randomArray[i] = numbs[temp];
            numbsWithCounters.put(numbs[temp], numbsWithCounters.get(numbs[temp]) + 1);
            log.trace("Number \"" + numbs[temp] + "\" was added " + numbsWithCounters.get(temp)
            + " times.");
        }
    }

    {
        for (int number : numbs) {
            numbsWithCounters.put(number, 0);
        }
    }

    RandArray() {

        randomArray = new int[rand.nextInt()];
        init();
    }
    RandArray(int size) {
        randomArray = new int[size];
        init();
    }

    public String getResults() {
        String separator = System.getProperty("line.separator");
        StringBuilder res = new StringBuilder("Final result:" + separator);
        for (int i = 0; i < numbs.length; i++) {
            res.append("Number \"" + numbs[i] + "\" has been written "
            + numbsWithCounters.get(numbs[i]) + " times." + separator);
        }
        return res.toString();
    }
}
