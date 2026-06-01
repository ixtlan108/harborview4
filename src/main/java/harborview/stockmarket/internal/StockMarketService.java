package harborview.stockmarket.internal;


import harborview.stockmarket.StockPrice;
import harborview.stockmarket.StockTicker;

import java.time.LocalDate;
import java.util.List;

public interface StockMarketService {
    List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx);
}
