package harborview.stockmarket.internal;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import harborview.stockmarket.StockPrice;
import harborview.stockmarket.StockTicker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StockMarketCore {

    private static final Logger logger = LogManager.getLogger(StockMarketCore.class);

    private final StockMarketService service;

    Cache<Integer, List<StockPrice>> stockPriceCache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public StockMarketCore(StockMarketService service) {
        this.service = service;
    }

    public List<StockPrice> getPrices(StockTicker ticker) {
        var cached = stockPriceCache.getIfPresent(ticker.oid());
        if (cached == null) {
            logger.info("Populating the stockPrice cache for oid: {}", ticker.oid());
            cached = service.getStockPrices(ticker, null);
            stockPriceCache.put(ticker.oid(), cached);
        }
        return cached;
    }
}
