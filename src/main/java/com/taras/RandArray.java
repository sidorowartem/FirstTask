package com.taras;

import java.util.Random;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by taras on 26.10.2015.
 */
public class RandArray
{
    public static final Logger log = LogManager.getLogger(RandArray.class);

    private int[] array;
    private Random rand = new Random();
    private int[] numbs = {1, 3, 5, 7, 9};
    private int[] counter = {0, 0, 0, 0, 0};

    private void init()
    {
        for (int i = 0; i < array.length; i++)
        {
            int temp = rand.nextInt(5);
            array[i] = numbs[temp];
            counter[temp]++;
            log.trace("Number \"" + numbs[temp] + "\" was added " + counter[temp]
            + " times.");
        }
    }

    RandArray()
    {
        array = new int[100];
        init();
    }
    RandArray(int size) {
        array = new int[size];
        init();
    }

    public String showResultsToLog()
    {
        String res = "\n";
        for (int i = 0; i < numbs.length; i++)
        {
            res +=("Number \"" + numbs[i] + "\" has been written "
            + counter[i] + " times.\n");
        }
        return res;
    }
}
