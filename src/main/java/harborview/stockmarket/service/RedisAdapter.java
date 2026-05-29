package harborview.stockmarket.service;


import harborview.stockmarket.stock.StockPrice;
import harborview.stockmarket.stock.StockTicker;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RedisAdapter {

    private final RedisTemplate<String,Object> redisTemplate;

    public RedisAdapter(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private double getPrice(StockTicker ticker, String redisKey) {
        var result = (String) redisTemplate.opsForHash().get(redisKey,
                String.format("%d", ticker.oid()));
        return result == null ? 0.0 : Double.parseDouble(result);
    }

    public StockPrice getSpot(StockTicker ticker) {
        var opn = getPrice(ticker, "stockprice:open");
        var hi = getPrice(ticker, "stockprice:hi");
        var lo = getPrice(ticker, "stockprice:lo");
        var cls = getPrice(ticker, "stockprice:close");
        return new StockPrice(LocalDate.now(), opn, hi, lo, cls, 1000);
    }

    public Double openingPrice(StockTicker ticker) {
        var result = (String)redisTemplate.opsForHash().get("stockprice:open",
                String.format("%d",ticker.oid()));
        return result == null ? null : Double.parseDouble(result);
    }
}

