package org.antonsyzko.shibstedtest.model;

import java.util.Comparator;

/**
 * Created by Admin on 19.11.2016.
 */
public class ComicsComparator implements Comparator<MyMarvelCharacter> {
    @Override
    public int compare(MyMarvelCharacter o1, MyMarvelCharacter o2) {
        return o2.comicsAppearance - o1.comicsAppearance;
    }
}
