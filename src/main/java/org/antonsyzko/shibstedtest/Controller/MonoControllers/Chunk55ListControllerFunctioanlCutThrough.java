package org.antonsyzko.shibstedtest.Controller.MonoControllers;

import com.google.gson.*;
import com.google.gson.stream.MalformedJsonException;
import org.antonsyzko.shibstedtest.Utils.TimeTakenUtil;
import org.antonsyzko.shibstedtest.model.ComicsComparator;
import org.antonsyzko.shibstedtest.model.MyMarvelCharacter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 20.11.2016.
 * functioanal all as we go approach - all methods in one class
 * uses chunks 0f 55 Objects - results (1485 / 5 ) = 27 calls to server
 * uses List to store results
 */
public class Chunk55ListControllerFunctioanlCutThrough {
    public static final String publicKey = "ec668b99de4fa9b97745daa1b47fde47";
    public static final String privateKey = "0bcbe42c53d0570665671385894a4a93104c21ad";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String url = getFirstURL();
        int restCallsMade = 1;
        System.out.println(" Rest call to get JSON data :  "  + ++restCallsMade + " URL : " + url);
        int total = getNumberOfAllAvailableCharacters(url);

        List<MyMarvelCharacter> tottalStorage = new ArrayList<MyMarvelCharacter>();
        List<MyMarvelCharacter> currentList = characterBuilderList(url);
        tottalStorage.addAll(currentList);

        int counter = 1;

        for( int i = 55; i<= total; i+=55) {
            String currentURLwithOffset = getOffsetURL(i);
            System.out.println("Rest call to get JSON data :  "  + (++restCallsMade) + " URL : "  + currentURLwithOffset);
            currentList = characterBuilderList(currentURLwithOffset);
            tottalStorage.addAll(currentList);
        }
        System.out.println(" total available  characters : " + total + " Alphabetic Order List ");
        Collections.sort(tottalStorage, new ComicsComparator());

        for(MyMarvelCharacter each : tottalStorage){
            System.out.println(counter + " : "+ each.toString());
            counter ++;
        }

        System.out.println(" ********************  10 MOST POPULAR CHARACTERS ***********************************");
        for(int i = 0;i<10;i++){
            System.out.println(tottalStorage.get(i));
        }
        TimeTakenUtil.timeTakenReport(startTime);
    }

    public static String getFirstURL() {
        long timeStamp = System.currentTimeMillis();
        int limit = 55;
        String stringToHash = timeStamp + privateKey + publicKey;
        String hash = DigestUtils.md5Hex(stringToHash);
        String firstURL = String.format("http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d" ,timeStamp, publicKey, hash,limit);
        return firstURL;
    }

    public static int getNumberOfAllAvailableCharacters(String url){
        int result = 0;
        String json = null;
        try {
            json = IOUtils.toString(new URL(url));
            JsonParser parser = new JsonParser();

            JsonElement element = parser.parse(json);

            if (element.isJsonObject()) {
                JsonObject characters = element.getAsJsonObject();
                JsonObject dataobja = characters.getAsJsonObject("data");

                JsonPrimitive total = dataobja.getAsJsonPrimitive("total");
                result = total.getAsInt();
            }
        } catch (MalformedJsonException ex){
            ex.getCause();
        }
        catch (MalformedURLException ex){
            ex.getCause();
        }catch ( IOException ex){
            ex.getCause();
        }
        return result;
    }

    public static String getOffsetURL(int offSet) {
        long timeStamp = System.currentTimeMillis();
        int limit = 55;
        String stringToHash = timeStamp + privateKey + publicKey;
        String hash = DigestUtils.md5Hex(stringToHash);
        String offsetURL = String.format("http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d&offset=%d" ,timeStamp, publicKey, hash,limit,offSet);
        return offsetURL;
    }

    public static List<MyMarvelCharacter> characterBuilderList(String url) {
        List<MyMarvelCharacter> resList = new ArrayList<MyMarvelCharacter>();
        String json = null;
        try {
            json = IOUtils.toString(new URL(url));
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(json);
            if (element.isJsonObject()) {
                JsonObject characters = element.getAsJsonObject();
                JsonObject dataobja = characters.getAsJsonObject("data");
                JsonPrimitive total = dataobja.getAsJsonPrimitive("total");
                JsonArray datasets = dataobja.getAsJsonArray("results");
                for (int i = 0; i < datasets.size(); i++) {
                    JsonObject datasetEach = datasets.get(i).getAsJsonObject();
                    JsonObject comics = datasetEach.get("comics").getAsJsonObject();
                    JsonPrimitive availableInComics = comics.getAsJsonPrimitive("available");
                    MyMarvelCharacter result = new MyMarvelCharacter(datasetEach.get("name").getAsString(),availableInComics.getAsInt());
                    resList.add(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resList;
    }
}
