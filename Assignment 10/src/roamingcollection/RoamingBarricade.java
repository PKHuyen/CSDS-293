package roamingcollection;

import java.util.*;
import java.util.logging.Logger;

public class RoamingBarricade<K,V> extends TreeMap<K,V> implements NavigableMap<K, V> {
    private final RoamingMap<K, V> roamingMap;
    private Map<K,V> cloneMap;

    private static final Logger logger = Logger.getLogger(RoamingBarricade.class.getName());

    public RoamingBarricade() {
        this.roamingMap = new RoamingMap<>();
        this.cloneMap = roamingMap;
    }

    public TreeMap<K,V> clone(NavigableMap<K,V> roamingMap) {
        return new TreeMap<>(roamingMap);
    }

    public Set<K> keySet() {
        return super.keySet();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    public V get(Object key) {
        //Make copy of MatrixMap
        cloneMap = clone(roamingMap);
        V realValue = cloneMap.get(key);
        V checkValue = roamingMap.get(key);
        if (realValue == checkValue) return realValue;
        else {
            logInconsistency();
            return realValue;
        }
    }

    public V put(K key, V value) {
        V result = null;
        V oldVal = get(key);
        V newVal = null;
        cloneMap = clone(roamingMap);
        if (!roamingMap.containsKey(key)) {
            roamingMap.put(key, value);
            newVal = cloneMap.get(key);
            if (newVal != value || newVal == null) {
                logInconsistency();
            }
        }
        roamingMap.put(key, value);

        return result;
    }

    private void logInconsistency() {
        logger.warning("Inconsistency found");
    }
}
