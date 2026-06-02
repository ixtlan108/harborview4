package harborview.stockmarket.service;


import harborview.stockmarket.stockoption.StockOptionPurchase;
import harborview.stockmarket.stock.StockPrice;
import harborview.stockmarket.stock.StockTicker;

import java.time.LocalDate;
import java.util.List;

public interface StockMarketService {
    List<StockPrice> getStockPrices(StockTicker ticker, LocalDate fromDx);
    List<StockOptionPurchase> activePurchasesWithCritters(int purchaseType);
}
