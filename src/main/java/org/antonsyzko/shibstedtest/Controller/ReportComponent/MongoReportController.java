package org.antonsyzko.shibstedtest.Controller.ReportComponent;

import org.antonsyzko.shibstedtest.Service.*;
import org.antonsyzko.shibstedtest.Utils.TimeTakenUtil;
import org.antonsyzko.shibstedtest.config.SpringMongoConfig;
import org.antonsyzko.shibstedtest.model.MarvelCharacterForMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 20.11.2016.
 * Report Controller - retrieves Data from Mongo DB collection for reporting
 */
public class MongoReportController {
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
        List<MarvelCharacterForMongoDB> listOfAllCharacters = mongoOperation.findAll(MarvelCharacterForMongoDB.class,"marvel_characters_table");
        int mongoCounter = 1;
        System.out.println("  *************************** M O N G O    D B         R E P O R T   **************************** ");
        for (MarvelCharacterForMongoDB each : listOfAllCharacters){
            System.out.println(mongoCounter + " :  " + each.toString());
            mongoCounter ++;
        }
        Collections.sort(listOfAllCharacters, new Comparator<MarvelCharacterForMongoDB>() {
            @Override
            public int compare(MarvelCharacterForMongoDB o1, MarvelCharacterForMongoDB o2) {
                return o2.getAppearance() - o1.getAppearance();
            }
        });
        System.out.println(" ********************  10 MOST POPULAR CHARACTERS ***********************************");
        for(int i = 0;i<10;i++){
            System.out.println( i + " : " + listOfAllCharacters.get(i));
        }
        TimeTakenUtil.timeTakenReport(startTime);
    }
}
