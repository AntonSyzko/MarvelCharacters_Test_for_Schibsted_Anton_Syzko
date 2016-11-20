package org.antonsyzko.shibstedtest.FutureUpdatesBox.JsonFileFutureInjection;

import org.antonsyzko.shibstedtest.Service.JSONTreeTraversalService;
import org.antonsyzko.shibstedtest.Service.JsonTreeTraversalServiceImpl;
import org.antonsyzko.shibstedtest.Utils.MarvelAPIContants;
import org.antonsyzko.shibstedtest.Utils.URLUtil;
import org.antonsyzko.shibstedtest.model.MarvelCharacter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 20.11.2016.
 */
public class ConcurrentApproach {

    static  Map<MarvelCharacter, Integer> mainStorage;
    static Map<MarvelCharacter, Integer> transmitterMapPerCurrentRestCall ;
  static JSONTreeTraversalService jsonTreeService;
    public static void main(String[] args) {
        jsonTreeService = new JsonTreeTraversalServiceImpl();
        long startTime = System.currentTimeMillis();
        int counter = 1;

        String url = URLUtil.getFirstURL();
        int total = jsonTreeService.getNumberOfAllAvailableCharacters(url);

        mainStorage  = new ConcurrentHashMap<>();
        transmitterMapPerCurrentRestCall = jsonTreeService.getCharacterNamesMap(url);
        mainStorage.putAll(transmitterMapPerCurrentRestCall);


        int restCallsRuns = (int)Math.ceil((jsonTreeService.getNumberOfAllAvailableCharacters(URLUtil.getFirstURL())/ MarvelAPIContants.LIMIT_PER_PAGE));
        System.out.println(restCallsRuns);
        ExecutorService exservice = Executors.newFixedThreadPool(restCallsRuns);

        for( int i = 100; i<= total - (total%100); i+=100) {
            String currentURLwithOffset = URLUtil.getOffsetURL(i);
            MyRunnable currentRunnableTask = new MyRunnable(currentURLwithOffset);
//           Thread currentThread = new Thread(currentRunnableTask);
//            currentThread.run();

         exservice.execute(currentRunnableTask);
          //  exservice.submit(currentThread);

        }
        exservice.shutdown();
        String lastCallUrl = URLUtil.getLastURL(total- (total%100));
        transmitterMapPerCurrentRestCall = jsonTreeService.getCharacterNamesMap(lastCallUrl);
        mainStorage.putAll(transmitterMapPerCurrentRestCall);
        for(Map.Entry<MarvelCharacter,Integer> each : mainStorage.entrySet()){
            System.out.println(counter + " : " + each.getKey() + " : " + each.getValue());
            counter++;

        }
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        long secondsTaken = TimeUnit.MILLISECONDS.toSeconds(timeTaken);
        System.out.println(" Time  taken to process all  cals : "+secondsTaken + " seconds ");

    }

    public static class MyRunnable implements Runnable {

private final String url;

        public MyRunnable(String url){
            this.url = url;
        }

        @Override
        public void run() {

            try {
              //  String currentURLwithOffset = URLUtil.getOffsetURL(offset);
                System.out.println("URL   " + url);
                transmitterMapPerCurrentRestCall = jsonTreeService.getCharacterNamesMap(url);
                mainStorage.putAll(transmitterMapPerCurrentRestCall);



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}