package org.github.ponking66.ccecdit.cache;

import cn.hutool.cache.CacheListener;
import cn.hutool.cache.impl.CacheObj;
import cn.hutool.cache.impl.LFUCache;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import org.github.ponking66.ccecdit.CustomDictWordSqliteManagerServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * 暂时不起作用
 *
 * @author pony
 * @date 2023/7/14
 */
@State(
        name = "org.github.ponking66.ccecdit.cache.FrequencyWordCacheComponent",
        storages = {@Storage("CCECDITCachePlugin.xml")}
)
public class FrequencyWordCacheComponent implements PersistentStateComponent<FrequencyWordCacheState> {

    private final Logger LOGGER = Logger.getInstance(CustomDictWordSqliteManagerServiceImpl.class);
    private final FrequencyWordCacheState state = new FrequencyWordCacheState();

    private final LFUCache<String, Integer> CACHE = new LFUCache<>(500) {
        {
            this.setListener(new CacheListener<String, Integer>() {
                @Override
                public void onRemove(String key, Integer cachedObject) {
                    state.getCache().remove(key, cachedObject);
                    LOGGER.debug("Remove {} key word.", key);
                }
            });
        }
    };

    public static FrequencyWordCacheComponent getInstance() {
        return ApplicationManager.getApplication().getService(FrequencyWordCacheComponent.class);
    }

    @Override
    public @Nullable FrequencyWordCacheState getState() {
        Iterator<CacheObj<String, Integer>> iterator = CACHE.cacheObjIterator();
        while (iterator.hasNext()) {
            CacheObj<String, Integer> obj = iterator.next();
            state.getCache().put(obj.getKey(), obj.getValue());
        }
        return state;
    }

    @Override
    public void loadState(@NotNull FrequencyWordCacheState state) {
        state.getCache().forEach(CACHE::put);
    }

    public void add(String key) {
        Integer count = this.CACHE.get(key);
        if (count == null) {
            this.CACHE.put(key, 1);
        } else {
            this.CACHE.put(key, count + 1);
        }
    }

    public Integer get(String key) {
        return this.CACHE.get(key);
    }

}
