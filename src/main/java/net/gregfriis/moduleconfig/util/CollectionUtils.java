package net.gregfriis.moduleconfig.util;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class CollectionUtils {

    public static <K, V> Map<K, V> shallowCopy(Map<K, V> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
