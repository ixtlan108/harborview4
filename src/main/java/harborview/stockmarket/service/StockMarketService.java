package harborview.stockmarket.service;


import harborview.stockmarket.stock.Stock;
import harborview.stockmarket.stockoption.StockOptionPurchase;
import harborview.stockmarket.stock.StockPrice;
import harborview.stockmarket.stock.StockTicker;

import java.time.LocalDate;
import java.util.List;

public interface StockMarketService {
    List<Stock> getStocks();
    StockPrice getSpot(StockTicker ticker);
    List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx);
    List<StockOptionPurchase> activePurchasesWithCritters(int purchaseType);
}
