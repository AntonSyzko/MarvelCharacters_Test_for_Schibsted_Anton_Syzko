package org.antonsyzko.shibstedtest.Service;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Admin on 20.11.2016.
 */
public class URLServiceImpl implements URLService {
    public  String marvelHashRequired(){
        String stringToHash = timeStamp + PRIVATE_KEY + PUBLIC_KEY;
        String marvelHash = DigestUtils.md5Hex(stringToHash);
        return  marvelHash;
    }

    public String getOffsetURL(int offSet) {
        String offsetURL = String.format(OFFSET_URL_TEMPLATE,timeStamp, PUBLIC_KEY, marvelHashRequired(),LIMIT_PER_PAGE,offSet);
        return offsetURL;
    }

    public String getLastURL(int offSet) {
        String offsetURL = String.format(OFFSET_URL_TEMPLATE ,timeStamp, PUBLIC_KEY, marvelHashRequired(),LIMIT_PER_PAGE,offSet);
        return offsetURL;
    }

    public  String getFirstURL() {
        String firstURL = String.format(FIRST_URL_TEMPLATE ,timeStamp, PUBLIC_KEY, marvelHashRequired(),LIMIT_PER_PAGE);
        return firstURL;
    }

}
