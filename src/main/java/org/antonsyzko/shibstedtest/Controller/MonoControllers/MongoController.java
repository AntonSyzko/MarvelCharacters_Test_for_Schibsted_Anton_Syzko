package org.antonsyzko.shibstedtest.Controller.MonoControllers;

import org.antonsyzko.shibstedtest.Service.*;
import org.antonsyzko.shibstedtest.Utils.TimeTakenUtil;
import org.antonsyzko.shibstedtest.config.SpringMongoConfig;
import org.antonsyzko.shibstedtest.model.MarvelCharacterForMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 20.11.2016.
 * Mongo DB monolit controller - data scapper nd report all in one
 */
public class MongoController {
    @Autowired
    static JsonTreeTraversalServiceImpl jsonTreeService = new JsonTreeTraversalServiceImpl();
    @Autowired
    static URLService urlService = new URLServiceImpl();
    @Autowired
    static MapService mapService = new MapServiceImpl();
    @Autowired
    static MongoDBStorageService mongoService = new MongoDBServiceImpl();


    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

        long startTime = System.currentTimeMillis();
        String firstUrl = urlService.getFirstURL();
        int total = jsonTreeService.getNumberOfAllAvailableCharacters(firstUrl);
        mongoService.saveMarvelCharacterinMongoCllection(firstUrl);
        int counter = 1;
        for( int i = 100; i<= total - (total%100); i+=100) {
            String currentURLwithOffset = urlService.getOffsetURL(i);
             mongoService.saveMarvelCharacterinMongoCllection(currentURLwithOffset);
        }
        String lastCallUrl = urlService.getLastURL(total- (total%100));
       mongoService.saveMarvelCharacterinMongoCllection(lastCallUrl);
        System.out.println(" total available  characters : " + total + " Alphabetic Order List ");

        List<MarvelCharacterForMongoDB> allOf = mongoOperation.findAll(MarvelCharacterForMongoDB.class,"marvel_characters_table");

        int mongoCounter = 1;
        System.out.println("  *************************** M O N G O **************************** ");
        for (MarvelCharacterForMongoDB each : allOf){
            System.out.println(mongoCounter + " :  " + each.toString());
            mongoCounter ++;
        }
        Collections.sort(allOf, new Comparator<MarvelCharacterForMongoDB>() {
            @Override
            public int compare(MarvelCharacterForMongoDB o1, MarvelCharacterForMongoDB o2) {
                return o2.getAppearance() - o1.getAppearance();
            }
        });
        System.out.println(" ********************  10 MOST POPULAR CHARACTERS ***********************************");
        for(int i = 0;i<10;i++){
            System.out.println(allOf.get(i));
        }
        TimeTakenUtil.timeTakenReport(startTime);
    }
}
