package io.bookflight.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class RefreshCache {
    private final CacheManager cacheManager;

    public RefreshCache(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Scheduled(fixedRate = 1000)
    public void refreshCache() {
        Objects.requireNonNull(cacheManager.getCache("user")).clear();
        log.info("cache refreshed");
    }
}
