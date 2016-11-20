package org.antonsyzko.shibstedtest.Service;

import java.util.Map;

/**
 * Created by Admin on 20.11.2016.
 */
public interface MapService {
     <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map );
}
