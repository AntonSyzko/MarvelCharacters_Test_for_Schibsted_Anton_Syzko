package org.antonsyzko.shibstedtest.Controller.dataScraperComponent;

import org.antonsyzko.shibstedtest.Service.*;
import org.antonsyzko.shibstedtest.Utils.TimeTakenUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Admin on 20.11.2016.
 */
public class MongoLoaderController {
    @Autowired
    static JsonTreeTraversalServiceImpl jsonTreeService = new JsonTreeTraversalServiceImpl();
    @Autowired
    static URLService urlService = new URLServiceImpl();
    @Autowired
    static MongoDBStorageService mongoService = new MongoDBServiceImpl();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String firstUrl = urlService.getFirstURL();
        int total = jsonTreeService.getNumberOfAllAvailableCharacters(firstUrl);
        mongoService.saveMarvelCharacterinMongoCllection(firstUrl);

        for( int i = 100; i<= total - (total%100); i+=100) {
            String currentURLwithOffset = urlService.getOffsetURL(i);
            mongoService.saveMarvelCharacterinMongoCllection(currentURLwithOffset);
        }

        String lastCallUrl = urlService.getLastURL(total- (total%100));
        mongoService.saveMarvelCharacterinMongoCllection(lastCallUrl);
        TimeTakenUtil.timeTakenReport(startTime);
    }
}
