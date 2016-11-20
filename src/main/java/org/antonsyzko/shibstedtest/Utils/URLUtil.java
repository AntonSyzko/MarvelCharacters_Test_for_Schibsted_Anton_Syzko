package org.antonsyzko.shibstedtest.Utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Admin on 20.11.2016.
 */
public class URLUtil {

    public final static String marvelHashRequired(){
        String stringToHash = MarvelAPIContants.timeStamp + MarvelAPIContants.PRIVATE_KEY + MarvelAPIContants.PUBLIC_KEY;
        String marvelHash = DigestUtils.md5Hex(stringToHash);
        return  marvelHash;
    }

    public static String getOffsetURL(int offSet) {
        String offsetURL = String.format(MarvelAPIContants.OFFSET_URL_TEMPLATE, MarvelAPIContants.timeStamp, MarvelAPIContants.PUBLIC_KEY, marvelHashRequired(), MarvelAPIContants.LIMIT_PER_PAGE,offSet);
        return offsetURL;
    }

    public static String getLastURL(int offSet) {
        String offsetURL = String.format(MarvelAPIContants.OFFSET_URL_TEMPLATE , MarvelAPIContants.timeStamp, MarvelAPIContants.PUBLIC_KEY, marvelHashRequired(), MarvelAPIContants.LIMIT_PER_PAGE,offSet);
        return offsetURL;
    }

    public static String getFirstURL() {
        String firstURL = String.format(MarvelAPIContants.FIRST_URL_TEMPLATE , MarvelAPIContants.timeStamp, MarvelAPIContants.PUBLIC_KEY, marvelHashRequired(), MarvelAPIContants.LIMIT_PER_PAGE);
        return firstURL;
    }

}
