package org.antonsyzko.shibstedtest.Utils;

import java.util.*;

/**
 * Created by Admin on 20.11.2016.
 */
public class MapUtil {

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return ( o2.getValue() ).compareTo( o1.getValue() );
            }
        } );

        int offset = 10;
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            if(offset >0){
                result.put( entry.getKey(), entry.getValue() );
                offset--;
            }else {
                break;
            }
        }
        return result;
    }
}
