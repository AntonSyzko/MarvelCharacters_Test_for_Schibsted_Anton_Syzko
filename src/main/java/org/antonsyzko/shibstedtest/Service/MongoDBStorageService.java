package org.antonsyzko.shibstedtest.Service;

/**
 * Created by Admin on 20.11.2016.
 */
public interface MongoDBStorageService {
    String JsonTreeDataObj = "data";
    String JsonTreeResultsArrayObj = "results";
    String JsonTreeTotalAvailableObj = "total";
    String JsonResultsComicsObj = "comics";
    String JsonResComicsNameObj = "name";
    String JsonResComicsVavailableObj = "available";

    void saveMarvelCharacterinMongoCllection(String url);
}
