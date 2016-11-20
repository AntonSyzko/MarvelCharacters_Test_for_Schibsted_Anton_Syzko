package org.antonsyzko.shibstedtest.Service;

/**
 * Created by Admin on 20.11.2016.
 */
public interface URLService {
 String PUBLIC_KEY = "ec668b99de4fa9b97745daa1b47fde47" ;
    String PRIVATE_KEY = "0bcbe42c53d0570665671385894a4a93104c21ad";
    int LIMIT_PER_PAGE = 100;
    long timeStamp = System.currentTimeMillis();
    String FIRST_URL_TEMPLATE = "http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d";
    String OFFSET_URL_TEMPLATE = "http://gateway.marvel.com/v1/public/characters?ts=%d&apikey=%s&hash=%s&limit=%d&offset=%d";

    String marvelHashRequired();
    String getOffsetURL(int offset);
    String getLastURL(int offSet);
    String getFirstURL();
}
