package org.antonsyzko.shibstedtest.FutureUpdatesBox.JsonFileFutureInjection;


/**
 * Created by Admin on 18.11.2016.
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JSONParser {
    public static final   String publicKey = "ec668b99de4fa9b97745daa1b47fde47";
    public static final    String privateKey = "0bcbe42c53d0570665671385894a4a93104c21ad";
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public static void main(String[] args) {
        JSONParser jspar = new JSONParser();
        long timeStamp = System.currentTimeMillis();

        String stringToHash = timeStamp + privateKey + publicKey;
        String hash = DigestUtils.md5Hex(stringToHash);

        //   String url = String.format("http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d", timeStamp, publicKey, hash, limit);
        String url = String.format("http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s", timeStamp, publicKey, hash);
        JSONObject home = jspar.getJSONFromUrl(url);
        JSONObject data = home.getJSONObject("data");
        int  total = data.getInt("total");
        int counter = 1;
        for( int i = 55; i<= total; i+=55){
            String urlOffset = String.format("http://gateway.marvel.com/v1/public/characters?offset=%dts=%d&apikey=%s&hash=%s", i,timeStamp, publicKey, hash);

            System.out.println(counter + "\t"+ i+" \t : "+urlOffset);
            counter++;
        }
      //  System.out.println(total);
        JSONArray results = data.getJSONArray("results");
        for(int i = 0; i< results.length();i++){
            JSONObject each = results.getJSONObject(i);
            JSONObject comics = each.getJSONObject("comics");
            String name = each.getString("name");
            int available = comics.getInt("available");
            System.out.println(i + " : "+name.toString() + "\t" + available);
        }






    }


    // constructor
    public JSONParser() {

    }

    public JSONObject getJSONFromUrl(String url) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
          //  HttpPost httpPost = new HttpPost(url);
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
          e.getCause();
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {

e.getCause();        }

        // return JSON String
        return jObj;

    }
}
