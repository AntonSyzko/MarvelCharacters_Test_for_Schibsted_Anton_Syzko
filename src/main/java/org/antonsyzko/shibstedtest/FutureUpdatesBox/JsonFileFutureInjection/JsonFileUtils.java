package org.antonsyzko.shibstedtest.FutureUpdatesBox.JsonFileFutureInjection;

import com.google.gson.*;
import org.antonsyzko.shibstedtest.Utils.MarvelAPIContants;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Admin on 20.11.2016.
 */
public class JsonFileUtils {

    private static void writetojson(MarvelCharForJsonFile marvelChar) {
        try {
            File file = new File("D:\\IdeaProjects\\MarvelCharactersTest\\src\\main\\java\\org\\antonsyzko\\shibstedtest\\tests\\Service\\json_storage.json");
            FileOutputStream os = new FileOutputStream(file, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String temp = gson.toJson(marvelChar);
            bw.append(temp);
            //    bw.append(',');
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Map<MarvelCharForJsonFile, Integer> getCharacterNamesMapFile(String url) {

        // Map <MarvelCharacter, Integer> result = new LinkedHashMap<MarvelCharacter, Integer>();
        //Map <MarvelCharacter, Integer> result = new ConcurrentHashMap<>();
        // Map <MarvelCharacter, Integer> result = Collections.synchronizedMap(new LinkedHashMap<MarvelCharacter, Integer>());
        Map<MarvelCharForJsonFile, Integer> result = new LinkedHashMap<>();


        String json = null;
        try {
            json = IOUtils.toString(new URL(url));
            JsonParser parser = new JsonParser();
            // The JsonElement is the root node. It can be an object, array, null or
            // java primitive.
            JsonElement element = parser.parse(json);
            //    System.out.println("root element \t" + element.toString());

            // use the isxxx methods to find out the type of jsonelement. In our
            // example we know that the root object is the Albums object and
            // contains an array of dataset objects
            if (element.isJsonObject()) {
                JsonObject characters = element.getAsJsonObject();
                //   System.out.println("etag \t"+characters.get("etag").getAsString());
                // JsonElement dataobj = characters.get("data");
                JsonObject dataobja = characters.getAsJsonObject(MarvelAPIContants.JsonTreeDataObj);

                //   JsonPrimitive total = dataobja.getAsJsonPrimitive("total");
                //  System.out.println(" \r\n TOTAL \t\t\t" + total);
                //System.out.println("data \t "+dataobj);
                JsonArray datasets = dataobja.getAsJsonArray(MarvelAPIContants.JsonTreeResultsArrayObj);
                //     System.out.println(" results \t" + datasets);
//              JsonArray datasets = characters.getAsJsonArray("data");
                for (int i = 0; i < datasets.size(); i++) {
                    JsonObject datasetEach = datasets.get(i).getAsJsonObject();
                    JsonObject comics = datasetEach.get(MarvelAPIContants.JsonResultsComicsObj).getAsJsonObject();
                    JsonPrimitive availableInComics = comics.getAsJsonPrimitive(MarvelAPIContants.JsonResComicsVavailableObj);

                    //   System.out.println(i + ": " + datasetEach.get("name").getAsString() + "\t\t" + availableInComics);
                    //   System.out.println(comics.entrySet().toString());

                    //result.add(datasetEach.get("name").getAsString());
                    MarvelCharForJsonFile currentCharacter = new MarvelCharForJsonFile(datasetEach.get("id").getAsLong(), datasetEach.get(MarvelAPIContants.JsonResComicsNameObj).getAsString(),availableInComics.getAsInt());
                    result.put(currentCharacter, availableInComics.getAsInt());



                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
