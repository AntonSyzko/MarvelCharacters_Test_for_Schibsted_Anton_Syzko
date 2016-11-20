package org.antonsyzko.shibstedtest.Utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Admin on 20.11.2016.
 */
public class MarvelAPIContants {

    public final static String PUBLIC_KEY = "ec668b99de4fa9b97745daa1b47fde47" ;
    public final static String PRIVATE_KEY = "0bcbe42c53d0570665671385894a4a93104c21ad";
    public final static int LIMIT_PER_PAGE = 100;
    public static long timeStamp = System.currentTimeMillis();
    public static String FIRST_URL_TEMPLATE = "http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d";
    public static String OFFSET_URL_TEMPLATE = "http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d&offset=%d";
    public static String JsonTreeDataObj = "data";
    public static String JsonTreeResultsArrayObj = "results";
    public static String JsonTreeTotalAvailableObj = "total";
    public static String JsonResultsComicsObj = "comics";
    public static String JsonResComicsNameObj = "name";
    public static String JsonResComicsVavailableObj = "available";
}
