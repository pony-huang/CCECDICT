package org.github.ponking66.ccecdit.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pony
 * @date 2023/7/15
 */
public class FrequencyWordCacheState {

    private final Map<String, Integer> cache = new HashMap<>();


    public Map<String, Integer> getCache() {
        return cache;
    }
}
