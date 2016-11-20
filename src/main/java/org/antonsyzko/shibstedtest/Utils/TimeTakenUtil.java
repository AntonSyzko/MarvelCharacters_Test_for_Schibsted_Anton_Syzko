package org.antonsyzko.shibstedtest.Utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 20.11.2016.
 * time it takes  reporting tool
 */
public class TimeTakenUtil {
    public static void timeTakenReport(long startTime){
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        long secondsTaken = TimeUnit.MILLISECONDS.toSeconds(timeTaken);
        System.out.println("  time taken " + secondsTaken + " seconds ");
    }
}
