package roamingcollection;

import Assignment4_5.Indexes;
import Assignment4_5.MatrixMap;

import java.util.*;
import java.util.logging.Logger;

public final class RoamingMap<K,V> extends TreeMap<K,V> implements NavigableMap<K,V> {
    public RoamingMap<K,V> map;
    public RoamingMap(){}
    public RoamingMap(MatrixMap map) {
        map = map;
    }
    @Override
    public V get(Object key) {
        Indexes index = new Indexes(1,1);
        Indexes testIndex = new Indexes(3,3); //test with 2x2 matrix => Should return null but return (1,1)
        if(testIndex.equals(key)) return super.get(index);
        else return super.get(key);
    }

    @Override
    public V put(K key, V value) {
        Indexes index = new Indexes(1,1);
        Indexes otherIndex = new Indexes(0,0);
        if (index.equals(key)) return super.put(key, get(otherIndex));
        else return super.put(key,value);
    }
}