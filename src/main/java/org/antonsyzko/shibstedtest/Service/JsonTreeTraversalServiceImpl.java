package org.antonsyzko.shibstedtest.Service;

import com.google.gson.*;
import com.google.gson.stream.MalformedJsonException;
import org.antonsyzko.shibstedtest.model.MarvelCharacter;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Admin on 20.11.2016.
 */
public class JsonTreeTraversalServiceImpl implements JSONTreeTraversalService {
    
    public int getNumberOfAllAvailableCharacters(String url) {
        int result = 0;
        String json = null;
        try {
            json = IOUtils.toString(new URL(url));
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(json);
            if (element.isJsonObject()) {
                JsonObject characters = element.getAsJsonObject();
                JsonObject dataobja = characters.getAsJsonObject(JsonTreeDataObj);
                JsonPrimitive total = dataobja.getAsJsonPrimitive(JsonTreeTotalAvailableObj);
                result = total.getAsInt();
            }
        } catch (MalformedJsonException ex) {
            ex.getCause();
        } catch (MalformedURLException ex) {
            ex.getCause();
        } catch (IOException ex) {
            ex.getCause();
        }
        return result;
    }
    
    public Map<MarvelCharacter, Integer> getCharacterNamesMap(String url) {
        // Map <MarvelCharacter, Integer> result = new LinkedHashMap<MarvelCharacter, Integer>(); // base case 
        //Map <MarvelCharacter, Integer> result = new ConcurrentHashMap<>();// for  ConcurrentApprocah
        Map<MarvelCharacter, Integer> result = new LRUMap();
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
                    MarvelCharacter currentCharacter = new MarvelCharacter(datasetEach.get("id").getAsLong(), datasetEach.get(JsonResComicsNameObj).getAsString());
                    result.put(currentCharacter, availableInComics.getAsInt());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
