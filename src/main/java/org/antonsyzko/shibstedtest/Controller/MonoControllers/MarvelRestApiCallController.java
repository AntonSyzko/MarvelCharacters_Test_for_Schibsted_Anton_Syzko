package org.antonsyzko.shibstedtest.Controller.MonoControllers;

import org.antonsyzko.shibstedtest.Service.*;
import org.antonsyzko.shibstedtest.Utils.TimeTakenUtil;
import org.antonsyzko.shibstedtest.model.MarvelCharacter;
import org.apache.commons.collections.map.LRUMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Admin on 20.11.2016.
 * Map Storage controller - monilit - data scrapper and report all in one
 * @mainStrorage - map to store temoraray maps while iterating over  offset calls
 * @transmitterMapPerCurrentRestCall - data map for  current rest call
 */
public class MarvelRestApiCallController {
    @Autowired
    static JSONTreeTraversalService jsonTreeService = new JsonTreeTraversalServiceImpl();
    @Autowired
    static URLService urlService = new URLServiceImpl();
    @Autowired
    static MapService mapService = new MapServiceImpl();
    static  Map<MarvelCharacter, Integer> mainStorage;
    static Map<MarvelCharacter, Integer> transmitterMapPerCurrentRestCall ;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String url = urlService.getFirstURL();
        int restCallsMade = 1;
        System.out.println(" Rest call to get JSON data :  "  + restCallsMade + " URL : " + url);
        int total = jsonTreeService.getNumberOfAllAvailableCharacters(url);
         mainStorage = new LRUMap(total);
         transmitterMapPerCurrentRestCall = jsonTreeService.getCharacterNamesMap(url);
        mainStorage.putAll(transmitterMapPerCurrentRestCall);
        int counter = 1;
        for( int i = 100; i<= total - (total%100); i+=100) {
            String currentURLwithOffset = urlService.getOffsetURL(i);
            System.out.println("Rest call to get JSON data :  "  + (++restCallsMade) + " URL : "  + currentURLwithOffset);
            transmitterMapPerCurrentRestCall = jsonTreeService.getCharacterNamesMap(currentURLwithOffset);
            mainStorage.putAll(transmitterMapPerCurrentRestCall);
        }
        String lastCallUrl = urlService.getLastURL(total- (total%100));
        System.out.println(" Last Rest call to get JSON data :  "  + ++restCallsMade + " URL : " + lastCallUrl);

        transmitterMapPerCurrentRestCall = jsonTreeService.getCharacterNamesMap(lastCallUrl);
        mainStorage.putAll(transmitterMapPerCurrentRestCall);

        System.out.println(" total available  characters : " + total + " Alphabetic Order List ");

        for(Map.Entry<MarvelCharacter,Integer> each : mainStorage.entrySet()){
            System.out.println(counter + " : " + each.getKey() + " : " + each.getValue());
            counter++;
        }
        Map<MarvelCharacter,Integer> topTen = mapService.sortByValue(mainStorage);
        int topTenCounter = 1;
        System.out.println("************************** TOP TEN MARVEL CHARACTERS ****************************************************************");
        for(Map.Entry<MarvelCharacter,Integer> each : topTen.entrySet()){
            System.out.println(topTenCounter + " : " + each.getKey() + " : " + each.getValue());
            topTenCounter++;
        }
        TimeTakenUtil.timeTakenReport(startTime);
    }
}
