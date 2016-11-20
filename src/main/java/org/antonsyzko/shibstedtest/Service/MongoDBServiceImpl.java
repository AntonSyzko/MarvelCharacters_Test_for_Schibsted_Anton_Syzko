package org.antonsyzko.shibstedtest.Service;

import com.google.gson.*;
import org.antonsyzko.shibstedtest.model.MarvelCharacterForMongoDB;
import org.antonsyzko.shibstedtest.config.SpringMongoConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Admin on 20.11.2016.
 */
public class MongoDBServiceImpl implements MongoDBStorageService {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    @Override
    public void saveMarvelCharacterinMongoCllection(String url) {
        System.out.println(" rest call to " + url);
        String json = null;
        try {
            json = IOUtils.toString(new URL(url));
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(json);
            if (element.isJsonObject()) {
                JsonObject characters = element.getAsJsonObject();

                JsonObject dataobja = characters.getAsJsonObject(JsonTreeDataObj);

                JsonArray datasets = dataobja.getAsJsonArray(JsonTreeResultsArrayObj);

                for (int i = 0; i < datasets.size(); i++) {
                    JsonObject datasetEach = datasets.get(i).getAsJsonObject();
                    JsonObject comics = datasetEach.get(JsonResultsComicsObj).getAsJsonObject();
                    JsonPrimitive availableInComics = comics.getAsJsonPrimitive(JsonResComicsVavailableObj);

                    MarvelCharacterForMongoDB currentCharacter = new MarvelCharacterForMongoDB(datasetEach.get("id").getAsLong(),datasetEach.get(JsonResComicsNameObj).getAsString(),availableInComics.getAsInt());
                    mongoOperation.save(currentCharacter,"marvel_characters_table");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
