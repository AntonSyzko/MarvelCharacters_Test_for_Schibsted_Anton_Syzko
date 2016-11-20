package org.antonsyzko.shibstedtest.Service;

import org.antonsyzko.shibstedtest.model.MarvelCharacter;

import java.util.Map;

/**
 * Created by Admin on 20.11.2016.
 */
public interface JSONTreeTraversalService {
   String JsonTreeDataObj = "data";
  String JsonTreeResultsArrayObj = "results";
     String JsonTreeTotalAvailableObj = "total";
     String JsonResultsComicsObj = "comics";
     String JsonResComicsNameObj = "name";
    String JsonResComicsVavailableObj = "available";

    int getNumberOfAllAvailableCharacters(String url);
    Map<MarvelCharacter, Integer> getCharacterNamesMap(String url);
}

